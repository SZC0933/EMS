package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.base.ResultInfo;
import com.yjxxt.ems.bean.User;
import com.yjxxt.ems.exceptions.ParamsException;
import com.yjxxt.ems.model.UserModel;
import com.yjxxt.ems.query.UserQuery;
import com.yjxxt.ems.service.UserService;
import com.yjxxt.ems.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    //引入UserService
    private UserService userService;

    @RequestMapping("index")
    public String toUser(){
        return "user/user";
    }

    //用户登录
    @RequestMapping(value = "login")
    @ResponseBody
    public ResultInfo login(User user){
        //结果信息
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = userService.userLogin(user.getUserName(), user.getUserPwd());

        resultInfo.setResult(userModel);
        return resultInfo;
    }


    //个人信息的展示   前端页面展示
    @RequestMapping(value = "toSettingPage")
    public String toSettingPage(HttpServletRequest req){

        //获取当前登录用户的信息
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //根据用户ID查询用户信息  用来显示在个人信息
        User user = userService.selectByPrimaryKey(userId);
        System.out.println(user);
        //存储
        req.setAttribute("user",user);
        return "/user/setting";
    }

    //修改个人信息
    @RequestMapping(value = "setting")
    @ResponseBody
    public ResultInfo sayUpdate(User user){
        //实例化 ResultInfo
        ResultInfo resultInfo = new ResultInfo();
        //修改个人信息
        userService.updateByPrimaryKeySelective(user);
        //返回目标数据对象
        return resultInfo;
    }


    //修改密码   前端页面展示  只做了页面转发
    @RequestMapping(value = "toPasswordPage")
    public String updatePwd(){
        return "/user/password";
    }


    //修改密码  后端实现
    @RequestMapping(value = "updatePwd")
    @ResponseBody
    public ResultInfo  updatePwd(HttpServletRequest req,String oldPassword,String newPassword,String confirmPwd){
        //实例化ResultInfo
        ResultInfo resultInfo = new ResultInfo();
        //获取Cookie里的userID   通过请求  获取id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //调用service进行业务处理
        userService.updateUserPassword(userId,oldPassword,newPassword,confirmPwd);
        //返回ResultInfo对象
        return resultInfo;
    }


    /*SZJ*/
    /**
     * 多条件查询用户数据
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryUserByParams(UserQuery userQuery) {
        System.out.println();
        return userService.queryUserByParams(userQuery);

    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveUser(User user){
        userService.saveUser(user);
        return success("用户添加成功!");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user){
        userService.updateUser(user);
        return success("用户修改成功!");
    }

    @RequestMapping("addOrUpdatePage")
    public String addOrUpdatePage(Integer id, Model model){
        if (id!=null){
            User user=userService.selectByPrimaryKey(id);
            model.addAttribute("user",user);
        }
        return "user/add_update";
    }

    /**
     * 用户删除
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        userService.deleteUser(ids);
        return success("用户记录删除成功!");
    }


    /*SZC*/
    @RequestMapping("register")
    public String register() {
        return "register/register";
    }

    @RequestMapping("registerUser")
    @ResponseBody
    public ResultInfo registerUser(String userName, String password1, String password2, String icon) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            userService.registerUser(userName, password1, password2, icon);
        } catch (ParamsException p) {
            p.printStackTrace();
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(300);
            resultInfo.setMsg("参数异常了");
        }
        return resultInfo;
    }



}
