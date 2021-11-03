package com.yjxxt.ems.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.User;
import com.yjxxt.ems.bean.UserRole;
import com.yjxxt.ems.mapper.CommunityMapper;
import com.yjxxt.ems.mapper.UserMapper;
import com.yjxxt.ems.mapper.UserRoleMapper;
import com.yjxxt.ems.model.UserModel;
import com.yjxxt.ems.query.UserQuery;
import com.yjxxt.ems.utils.AssertUtil;
import com.yjxxt.ems.utils.Md5Util;
import com.yjxxt.ems.utils.PhoneUtil;
import com.yjxxt.ems.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {
    @Resource
    //引入dao层
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private CommunityMapper communityMapper;


    //用户登录
    public UserModel userLogin(String userName,String userPwd){
        //对输入的账号密码进行判断，是否符合格式
        checkUserLoginParam(userName,userPwd);

        //通过对数据库的查询，查看用户是否存在
        User temp =  userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(temp == null,"用户不存在");

        //判断用户的密码是否正确，拿数据库查询到的用户密码和用户输入的用户密码进行equest比较
        checkUserPwd(userPwd,temp.getUserPwd());

        //返回目标对象  对密码进行加密
        return builderUserInfo(temp);
    }




    /**
     * //对输入的账号密码进行判断  是否符合格式
     * @param userName   账号
     * @param userPwd    密码
     */
    //对输入的账号密码进行判断，是否符合格式
    private void checkUserLoginParam(String userName, String userPwd) {
        //用户非空
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");

        //密码非空
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"密码不能为空");
    }



    /**
     * //判断密码是否正确
     * @param userPwd   用户输入的密码
     * @param userPwd1  数据库查出来的密码
     */
    //判断用户的密码是否正确，拿数据库查询到的用户密码和用户输入的用户密码进行equest比较
    private void checkUserPwd(String userPwd, String userPwd1) {
        //对用户输入的密码进行加密
        userPwd = Md5Util.encode(userPwd);

        AssertUtil.isTrue(!(userPwd.equals(userPwd1)),"密码不正确");



    }



    /**
     *
     * @param temp 当前登录对象
     * @return
     */
    //对密码进行加密  返回目标对象
    private UserModel builderUserInfo(User temp) {
        UserModel userModel = new UserModel();
        //为用户密码进行加密
        userModel.setUserIdStr(UserIDBase64.encoderUserID(temp.getId()));
        userModel.setUserName(temp.getUserName());
        userModel.setTrueName(temp.getTrueName());
        return userModel;

    }


    /**
     *
     * @param userId                 当前Cookie存储的用户dId
     * @param oldPassword           旧密码
     * @param newPassword           新密码
     * @param confirmPassword            确认密码
     */
    //修改密码
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPassword) {
        //通过Id获取user对象
        User user = userMapper.selectByPrimaryKey(userId);
        //参数校验 (用户，旧密码，新密码，确认密码)
        checkPasswordParams(user,oldPassword,newPassword,confirmPassword);

        //默认参数设置，把用户输入的新密码  加密 添加进去
        user.setUserPwd(Md5Util.encode(newPassword));

        //执行更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"修改密码失败");
    }




    //修改密码的参数校验
    private void checkPasswordParams(User user, String oldPassword, String newPassword, String confirmPwd) {
        //用户不能为空  （不存在）
        AssertUtil.isTrue(null == user,"用户不存在");
        //原始密码  非空
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空");
        //原始密码是否和数据库查询到的密码一致
        AssertUtil.isTrue(!(Md5Util.encode(oldPassword).equals(user.getUserPwd())),"原始密码不正确");
        //新密码不能为空
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        //新密码和原始密码不能相同
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码不能和原始密码相同");
        //确认密码非空
        AssertUtil.isTrue(StringUtils.isBlank(confirmPwd),"确认密码不能为空");
        //确认密码需要和新密码一致
        AssertUtil.isTrue(!(newPassword.equals(confirmPwd)),"新密码和确认密码不一致");
    }


    /*SZJ*/



    /**
     * 多条件分页查询用户数据
     * @param query
     * @return
     */
    public Map<String, Object> queryUserByParams (UserQuery query) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        System.out.println("执行完毕");
        return map;
    }

    /**
     * 添加用户
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user){
        //参数校验
        checkParams(user.getUserName(),user.getComId(),user.getVc());
        //设置默认参数
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        //执行添加，判断结果
        AssertUtil.isTrue(userMapper.insertSelective(user)==null,"用户添加失败！");
        relaionUserRole(user.getId(),user.getRoleIds());
        AssertUtil.isTrue(communityMapper.addNumByComId(user.getComId())<1, "社区用户添加失败");
    }

    /**
     * 用户更新，修改
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user){
        //1.参数校验
        //通过用户id获取用户对象
        User temp=userMapper.selectByPrimaryKey(user.getId());
        //判断对象是否存在
        AssertUtil.isTrue(temp==null,"待更新记录不存在");
        //验证参数
        checkParams1(user.getUserName(),user.getComId(),user.getVc());
        //2.设置默认参数
        user.setUpdateDate(new Date());
        //3.执行更新，返回结果
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"用户更新失败！");
        relaionUserRole(user.getId(),user.getRoleIds());
    }

    private void checkParams(String userName, Integer comId, Integer vc) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        //验证用户是否存在
        User temp=userMapper.queryUserByUserName(userName);
        AssertUtil.isTrue(temp!=null,"用户名已存在");
        AssertUtil.isTrue(comId==null,"请输入所在社区");
        AssertUtil.isTrue(vc==null,"请选择疫苗接种状况");
    }

    private void checkParams1(String userName, Integer comId, Integer vc) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        //验证用户是否存在
        AssertUtil.isTrue(comId==null,"请输入所在社区");
        AssertUtil.isTrue(vc==null,"请选择疫苗接种状况");
    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer[] ids){
        AssertUtil.isTrue(ids==null||ids.length==0,"请选择您要删除的记录");
        for (int id:ids){
            User user=userMapper.selectByPrimaryKey(id);
            AssertUtil.isTrue(communityMapper.subNumByComId(user.getComId())!=ids.length, "社区用户删除失败");
        }
        AssertUtil.isTrue(deleteBatch(ids) != ids.length,"用户角色删除失败");
    }


    /*SZC*/
    /**
     * 用户注册
     * @param userName
     * @param password1
     * @param password2
     * @param icon
     */
    public void registerUser(String userName, String password1, String password2, String icon) {
        // 参数校验
        checkRegister(userName, password1, password2, icon);
        // 实例化user
        User user = new User();
        //设置默认参数
        user.setUserName(userName);
        user.setUserPwd(Md5Util.encode(password1));
        user.setUserPhone(icon);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        // 执行方法
        AssertUtil.isTrue(userMapper.insertSelective(user)<1, "用户添加失败");
    }

    /**
     * 用户注册的参数校验
     * @param userName
     * @param password1
     * @param password2
     * @param icon
     */
    private void checkRegister(String userName, String password1, String password2, String icon) {
        // 用户名不为空
        AssertUtil.isTrue(StringUtils.isBlank(userName), "请输入用户名");
        // 判断用户名是否存在
        User user1 = userMapper.selectByName(userName);
        AssertUtil.isTrue(user1!=null, "该用户已存在");
        // 判断手机号是否存在
        User user2 = userMapper.selectByPhone(icon);
        AssertUtil.isTrue(user2!=null, "该手机号已注册过账号");
        // 密码不为空
        AssertUtil.isTrue(StringUtils.isBlank(password1), "请输入密码");
        // 确认密码不为空
        AssertUtil.isTrue(StringUtils.isBlank(password2), "请输入确认密码");
        // 密码长度校验
        AssertUtil.isTrue(password1.length()<6 || password1.length()>12, "密码长度为6-12位");
        // 密码和确认密码相等
        AssertUtil.isTrue(!password1.equals(password2), "确认密码与密码不一致");
        // 手机号合法
        AssertUtil.isTrue(!PhoneUtil.isMobile(icon), "请输入正确的手机号");
    }

    /**
     * 删除用户原先的角色，并重新赋予新的角色
     * @param userId
     * @param roleIds
     */
    private void relaionUserRole(int userId, String roleIds) {
        // 通过id获取用户的角色数量
        int count = userRoleMapper.countUserRoleByUserId(userId);
        // count>0  说明用户原先有角色  先删除所有的角色
        if (count>0) {
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(userId)!=count, "用户角色删除失败");
        }
        // 传入的角色信息不为空 添加新的角色
        if (StringUtils.isNoneBlank(roleIds)) {
            // 将传入的roleIds转成字符串数组
            String[] roleStrIds = roleIds.split(",");
            // 用来存放用户的角色信息
            List<UserRole> roleList = new ArrayList<>();
            // 遍历roleIds
            for (String rid : roleStrIds) {
                // 准备对象
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(rid));
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                roleList.add(userRole);
            }
            AssertUtil.isTrue(userRoleMapper.insertBatch(roleList) != roleList.size(), "用户角色分配失败");
        }

    }

}
