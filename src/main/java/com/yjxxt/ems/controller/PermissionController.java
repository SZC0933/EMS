package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.service.PermissionService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class PermissionController extends BaseController {

    @Resource
    private PermissionService permissionService;
}
