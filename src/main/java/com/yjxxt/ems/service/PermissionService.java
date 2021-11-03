package com.yjxxt.ems.service;

import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.Permission;
import com.yjxxt.ems.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission, Integer> {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 根据用户id获取用户所拥有角色的权限码集合
     * @param userId
     * @return
     */
    public List<String> queryUserHasRolesHasPermissions(Integer userId) {
        return permissionMapper.queryUserHasRolesHasPermissions(userId);
    }
}
