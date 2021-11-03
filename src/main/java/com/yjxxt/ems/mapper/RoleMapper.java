package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role, Integer> {

    /*通过角色名进行查询*/
    Role selectByName(String roleName);

    // 查询所有角色
    @MapKey("")
    List<Map<String, Object>> seleceAllRole(Integer userId);

    // 删除角色
    int updateRoleById(Integer id);

    // 统计角色的权限资源数量
    int countPermissionByRoleId(Integer roleId);

    // 删除角色权限资源
    int deletePermissionsByRoleId(Integer roleId);
}