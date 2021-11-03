package com.yjxxt.ems.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.Permission;
import com.yjxxt.ems.bean.Role;
import com.yjxxt.ems.mapper.ModuleMapper;
import com.yjxxt.ems.mapper.PermissionMapper;
import com.yjxxt.ems.mapper.RoleMapper;
import com.yjxxt.ems.query.RoleQuery;
import com.yjxxt.ems.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService extends BaseService<Role,Integer> {


    @Autowired(required = false)
    RoleMapper roleMapper;

    @Autowired(required = false)
    RoleQuery roleQuery;

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /*多条件查询*/
    public Map<String,Object> selectRole(RoleQuery roleQuery){
        //创建map
        Map<String,Object> map =new HashMap<String,Object>();
        //查数据并分页
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getLimit());
        PageInfo<Role> pageInfo=new PageInfo<>(roleMapper.selectByParams(roleQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        return map;
    }

    /*添加角色*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRole(Role role) {
        //审核
        checkRole(role);
        //添加
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        System.out.println("就差一点！！！！");
        AssertUtil.isTrue(insertSelective(role)<1,"添加失败了呢~");
    }
    private void checkRole(Role role) {
        //是否为空
        AssertUtil.isTrue(role==null,"请输入角色信息~");
        //判断是否已经重复
        System.out.println("判断");
        Role role1= roleMapper.selectByName(role.getRoleName());
        System.out.println("判断结束");
        System.out.println(role1!=null);
        AssertUtil.isTrue(role1!=null,"已添加过啦~");
        System.out.println("退出@");
    }
    /*编辑角色*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(role)<1,"编辑失败啦~");
    }

    /**
     * 删除角色信息
     * @param role
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Role role) {
        // 验证
        AssertUtil.isTrue(role.getId()==null || selectByPrimaryKey(role.getId())==null, "待删除角色不存在");
        // 设定默认值
        role.setUpdateDate(new Date());
        // 删除角色绑定的权限资源
        int count = roleMapper.countPermissionByRoleId(role.getId());
        if (count>0) {
            int i = roleMapper.deletePermissionsByRoleId(role.getId());
            AssertUtil.isTrue(i!=count, "角色绑定的权限资源删除失败");
        }
        // 判断是否成功
        AssertUtil.isTrue(roleMapper.updateRoleById(role.getId())<1, "角色删除失败");
    }

    /**
     * 查询所有角色
     * @return
     */
    public List<Map<String, Object>> seleceAllRole(Integer userId) {
        return roleMapper.seleceAllRole(userId);
    }

    /**
     * 给角色添加权限
     * @param mids
     * @param roleId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer[] mids, Integer roleId) {
        // 判断roleId是否存在
        AssertUtil.isTrue(roleId==null || roleMapper.selectByPrimaryKey(roleId)==null, "待授权的角色不存在");
        // 统计当前角色的权限资源数量
        int count = roleMapper.countPermissionByRoleId(roleId);
        if (count>0) {
            // 如果角色存在权限资源，就全部删除
            int num = roleMapper.deletePermissionsByRoleId(roleId);
            AssertUtil.isTrue(count!=num, "资源删除失败");
        }
        List<Permission> plist = new ArrayList<>();
        if (mids!=null && mids.length!=0) {
            // 遍历mids
            for (Integer mid : mids) {
                // 实例化对象
                Permission permission = new Permission();
                // 设置数据
                permission.setRoleId(roleId);
                permission.setModuleId(mid);
                // 权限码
                permission.setAclValue(moduleMapper.selectByPrimaryKey(mid).getOptValue());
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                // 添加到list
                plist.add(permission);
            }
        }
        AssertUtil.isTrue(permissionMapper.insertBatch(plist)!=plist.size(), "角色权限更新失败");
    }
}
