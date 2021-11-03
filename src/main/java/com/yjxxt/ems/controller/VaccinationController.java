package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.base.ResultInfo;
import com.yjxxt.ems.bean.Vaccination;
import com.yjxxt.ems.query.VaccinationQuery;
import com.yjxxt.ems.service.CommunityService;
import com.yjxxt.ems.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@RequestMapping("community")
@Controller
public class VaccinationController extends BaseController {
    @Autowired
    CommunityService communityService;

    @Autowired
    VaccinationService vaccinationService;

    @RequestMapping("index")
    public String toCommunity(){
        return "community/community";
    }

    @RequestMapping("vaccination")
    public String toVaccination(){
        return "community/vaccination";
    }

    /*多条建查询*/
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> toList(VaccinationQuery vaccinationQuery){
        System.out.println("执行了");
        return vaccinationService.selectAll(vaccinationQuery);
    }

    /*添加或修改页面跳转*/
    @RequestMapping("vaddorupdate")
    public String vaddorupdata(Integer id, Model model){
        if(id!=null){
         Vaccination vaccination= vaccinationService.selectId(id);
         model.addAttribute("vacciantion",vaccination);
        }
        return "community/vaddorupdata";
    }

    /*添加*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Vaccination vaccination){
        ResultInfo result=new ResultInfo();
        System.out.println(vaccination);
        vaccinationService.insertVaccination(vaccination);
        result.setCode(200);
        result.setMsg("添加成功~~");
        return result;
    }

    /*删除*/
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo dels(Integer[] ids){
        ResultInfo result=new ResultInfo();
        System.out.println(ids);
        vaccinationService.delete(ids);
        result.setCode(200);
        result.setMsg("删除成功~~");
        return result;
    }

    /*编辑*/
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Vaccination vaccination){
        ResultInfo result=new ResultInfo();
        System.out.println(vaccination);
        vaccinationService.updateVa(vaccination);
        result.setCode(200);
        result.setMsg("修改成功~~");
        return result;
    }

}
