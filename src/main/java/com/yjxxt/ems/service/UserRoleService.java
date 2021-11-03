package com.yjxxt.ems.service;

import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.UserRole;
import com.yjxxt.ems.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole, Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;
}
