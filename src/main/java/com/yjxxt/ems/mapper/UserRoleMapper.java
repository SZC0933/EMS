package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole, Integer> {

    // 根基userId获取用户角色的数量
    int countUserRoleByUserId(int userId);

    // 删除用户原先的角色
    int deleteUserRoleByUserId(int userId);
}