package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.base.ResultInfo;
import com.yjxxt.ems.bean.Confirmed;
import com.yjxxt.ems.query.ConfirmedQuery;
import com.yjxxt.ems.service.ConfirmedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "confirmed")
@Controller
public class ConfirmedController extends BaseController {

    @Autowired
    //引入ConfirmedService
    private ConfirmedService confirmedService;


    //确诊页面的显示  做了一个跳转的功能
    @RequestMapping(value = "index")
    public String index(){
        return "confirmed/confirmed";
    }



    //分页查询  页面显示
    @RequestMapping(value = "list")
    @ResponseBody
    // @RequiredPermission(code = "60")    后端角色认证
    ////角色的条件查询以及 分页
    public Map<String,Object> list(ConfirmedQuery confirmedQuery){

        return confirmedService.findRoleByParam(confirmedQuery);
    }


    //  添加或者修改  只做页面跳转
    @RequestMapping(value = "addOrUpdatePage")
    public String addOrUpdatePage(Integer id,Model model){
        System.out.println(id + " 是否带有id");
        if (id!=null){
            Confirmed user = confirmedService.selectByPrimaryKey(id);
            model.addAttribute("user",user);
        }
        return "confirmed/add_update";
    }

    //用户的添加
    @RequestMapping(value = "save")
    @ResponseBody        //json格式输出
    public ResultInfo save(Confirmed user){
        //修改信息
        confirmedService.addUser(user);
        //返回目标数据对象
        return success("添加成功");
    }

    //用户的修改
    @RequestMapping(value = "update")
    @ResponseBody        //json格式输出
    public ResultInfo update(Confirmed user){
        //修改信息
        confirmedService.changeUser(user);
        //返回目标数据对象
        return success("修改成功");
    }


    //用户的批量删除
    @RequestMapping(value = "dels")
    @ResponseBody        //json格式输出
    public ResultInfo dels(Integer[] ids){
        //调用方法
        confirmedService.deleteUserByIds(ids);
        //返回目标数据对象
        return success("添加成功");
    }



    @RequestMapping(value = "findRoles")
    @ResponseBody
    ////查询所有社区名字
    public List<Map<String,Object>> findComs(){
        List<Map<String,Object>> list = confirmedService.queryComs();
        return list;
    }











}
