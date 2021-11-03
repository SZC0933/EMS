package com.yjxxt.ems.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.Community;
import com.yjxxt.ems.bean.Confirmed;
import com.yjxxt.ems.bean.User;
import com.yjxxt.ems.mapper.CommunityMapper;
import com.yjxxt.ems.mapper.ConfirmedMapper;
import com.yjxxt.ems.mapper.UserMapper;
import com.yjxxt.ems.query.ConfirmedQuery;
import com.yjxxt.ems.utils.AssertUtil;
import com.yjxxt.ems.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ConfirmedService extends BaseService<Confirmed,Integer> {

    @Resource
    //引入ConfirmedMapper
    private ConfirmedMapper confirmedMapper;

    @Resource
    //引入user表
    private UserMapper userMapper;

    @Resource
    //引入user表
    private CommunityMapper  communityMapper;

    //角色的条件查询以及 分页
    public Map<String,Object> findRoleByParam(ConfirmedQuery confirmedQuery){
        //实例化对象
        Map<String,Object> map = new HashMap<>();
        //实例化分页单位
        PageHelper.startPage(confirmedQuery.getPage(), confirmedQuery.getLimit());
        //开始分页
        PageInfo<Confirmed> rlist = new PageInfo<>(selectByParams(confirmedQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",rlist.getTotal());
        map.put("data",rlist.getList());
        //返回Map
        return map;
    }


    @Transactional(propagation = Propagation.REQUIRED)           //涉及到事务   就需要此注解
    //用户模块的添加
    public void addUser(Confirmed user) {
        //1、参数校验
        checkConfirmed(user.getTrueName(),user.getState());

        if (user.getComId().equals("浦东区")){
            user.setComId(1);
        }
        if (user.getComId().equals("黄浦区")){
            user.setComId(2);
        }
        if (user.getComId().equals("松江区")){
            user.setComId(3);
        }
        if (user.getComId().equals("徐汇区")){
            user.setComId(4);
        }
        if (user.getComId().equals("虹口区")){
            user.setComId(5);
        }

        //查询user表中是否存在此人  不存在   添加上去  设置默认值
        User temp =  userMapper.selectByPhone(user.getTcPhone());
       //打印试试   手机号查询用户
        if (temp != null){
            //健康状态改成2    如果user表里面已经有了的情况下
            userMapper.updateUserHealthById(temp.getUserPhone());
            //默认值  确诊表中的userId字段
            user.setUserId(temp.getId());

        }else {          //表里没有这个人的时候  添加 这个用户  新建一个user对象
            User u = new User();
            //真实姓名
            u.setTrueName(user.getTrueName());
            //名字
            u.setUserName(user.getTrueName());
            //设置密码  默认值  ：123456
            u.setUserPwd(Md5Util.encode("123456"));

            //设置社区ID
            u.setComId(user.getComId());

            //手机号  唯一
            u.setUserPhone(user.getTcPhone());
            u.setEcPhone(user.getTcPhone());
            u.setHealth("2");
            //创建时间
            u.setCreateDate(new Date());
            //修改时间
            u.setUpdateDate(new Date());

            //添加用户是否成功
            AssertUtil.isTrue(userMapper.insertSelective(u)<1,"插入用户失败");

            //给确诊人员添加其  userId
            Integer userId =  userMapper.selectById(user.getTcPhone());
            user.setUserId(userId);
        }

        //2、默认值设置
        //确诊日期
        user.setCreateDate(new Date());

        //添加是否成功
        AssertUtil.isTrue(insertSelective(user)<1,"添加失败");

        //relaionUserRole(user.getId(),user.getComId());


    }



    @Transactional(propagation = Propagation.REQUIRED)           //涉及到事务   就需要此注解
    //用户模块的修改
    public void changeUser(Confirmed user) {
        //通过id获取用户信息
        Confirmed temp = confirmedMapper.selectByPrimaryKey(user.getId());
        //判断用户信息是否存在
        AssertUtil.isTrue(temp == null,"当前用户不存在");

        //校验参数
        changeConfirmed(user.getTrueName(),user.getTcPhone(),user.getState());


        //修改是否成功  完整版
        //AssertUtil.isTrue(updateByPrimaryKeySelective(user)<1,"修改失败了");


        //修改是否成功  完整版
        AssertUtil.isTrue(confirmedMapper.uBPKS(user)<1,"修改失败了");



    }

    //修改的参数校验
    private void changeConfirmed(String trueName, String tcPhone, Integer state) {
        //1、用户名不能为空
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"姓名不能为空");
        //2、当前状态不能为空
        AssertUtil.isTrue(StringUtils.isBlank(tcPhone),"请输入手机号");
        //3、当前状态不能为空
        AssertUtil.isTrue(state<1 || state>4,"请选择正确的状态码");
    }


    //用户模块的添加的参数校验
    private void checkConfirmed(String trueName, Integer state) {
        //1、用户名不能为空
        AssertUtil.isTrue(StringUtils.isBlank(trueName),"姓名不能为空");
        //2、当前状态不能为空
        AssertUtil.isTrue(state<1 || state>3,"请选择正确的状态码");
    }


    //添加社区时的校验
    private void relaionUserRole(Integer id, Integer comId) {
        //准备集合  存储对象
        List<Community> urlist = new ArrayList<>();

        //userId,roleId
        //判断是否选择了角色信息

        //只能选择一个社区
        AssertUtil.isTrue(comId>1 || comId<1,"只能选择一个社区");

        //通过社区表的 com_id    查询到社区表的对应社区名
        communityMapper.selectaddresComId(comId);
        //添加

    }





    @Transactional(propagation = Propagation.REQUIRED)           //涉及到事务   就需要此注解
    //确诊人员的批量删除
    public void deleteUserByIds(Integer[] ids) {
        //要删除记录不能为空
        AssertUtil.isTrue(ids == null || ids.length==0,"请选择要删除的记录");

        //修改user表的状态码
        for(Integer id: ids){
            Confirmed confirmed = confirmedMapper.selectId(id);
            System.out.println(id+ " -----------------" );
            System.out.println(confirmed.getTrueName());
            AssertUtil.isTrue(userMapper.updateById(confirmed.getUserId())<1,"修改失败");
        }

        //删除确诊表的个人信息记录
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"删除失败");

    }

    //查询所有社区
    public List<Map<String, Object>> queryComs() {
        return confirmedMapper.selectComs();
    }






}
