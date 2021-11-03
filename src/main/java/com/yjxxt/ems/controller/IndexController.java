package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.bean.User;
import com.yjxxt.ems.service.PermissionService;
import com.yjxxt.ems.service.UserService;
import com.yjxxt.ems.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    UserService userService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest req) {
        // 通过工具类，从cookie中获取userId
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        // 调用对应Service层的方法，通过userId主键查询用户对象
        User user = userService.selectByPrimaryKey(userId);
        // 将用户对象设置到request作用域中
        req.setAttribute("user", user);
        // 获取用户拥有的角色权限码集合
        List<String> permissions = permissionService.queryUserHasRolesHasPermissions(userId);
        // 存到session中
        req.getSession().setAttribute("permissions", permissions);
        // 转到main页面
        return "main";
    }


    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }


    @RequestMapping("register")
    public String register(){
        return "register/register";
    }

}
