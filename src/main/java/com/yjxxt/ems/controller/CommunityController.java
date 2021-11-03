package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.query.CommunityQuery;
import com.yjxxt.ems.query.VaccinationQuery;
import com.yjxxt.ems.service.CommunityService;
import com.yjxxt.ems.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("com")
public class CommunityController extends BaseController {
    @Autowired
    CommunityService communityService;

    @Autowired
    VaccinationService vaccinationService;


    /*SZJ*/
    /**
     * 进入社区页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "community/community";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryComByParams(CommunityQuery communityQuery){
        return communityService.queryComByParams(communityQuery);
    }

    /*WXY*/

    @RequestMapping(value = "findRoles")
    @ResponseBody
    //查询所有角色信息
    public List<Map<String,Object>> sayRoles(Integer userId){
        System.out.println("我去调用查询社区方法了  ，我的id为" + userId);
        return communityService.findRoles(userId);
    }




}
