package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.dto.TreeDto;
import com.yjxxt.ems.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(){
        return moduleService.queryAllModules();
    }

    /**
     * 展示授权界面
     * @return
     */
    @RequestMapping("findModules")
    @ResponseBody
    public List<TreeDto> queryAllModules(Integer roleId) {
        return moduleService.queryAllModules02(roleId);
    }

}
