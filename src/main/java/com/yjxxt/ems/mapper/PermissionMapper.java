package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission, Integer> {

    // 获取当前角色的所有资源信息
    List<Integer> selectModuleByRoleId(Integer roleId);

    // 根据用户id获取用户所拥有角色的权限码
    List<String> queryUserHasRolesHasPermissions(Integer userId);
}