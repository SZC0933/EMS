package com.yjxxt.ems.controller;

import com.yjxxt.ems.base.BaseController;
import com.yjxxt.ems.base.ResultInfo;
import com.yjxxt.ems.bean.Role;
import com.yjxxt.ems.query.RoleQuery;
import com.yjxxt.ems.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @RequestMapping("index")
    public String toUser(){
        return "role/role";
    }

    /*多条件查询*/
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> toRole(RoleQuery roleQuery){

       return roleService.selectRole(roleQuery);
    }

    /*添加或修改*/
    @RequestMapping("addOrUpdatePage")
    public String addOrUpdatePage(Integer id, Model model){
        if(id!=null){
          Role role= roleService.selectByPrimaryKey(id);
          model.addAttribute("role",role );
        }
        return "role/add_update";
    }

    /*添加角色*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Role role){
        ResultInfo resultInfo=new ResultInfo();
        roleService.insertRole(role);
        resultInfo.setCode(200);
        resultInfo.setMsg("添加成功哟~😙");
        return resultInfo;
    }

    /*编辑角色*/
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updata(Role role){
        ResultInfo resultInfo=new ResultInfo();
        roleService.updateRole(role);
        resultInfo.setCode(200);
        resultInfo.setMsg("编辑成功哟~😙");
        return resultInfo;
    }

    /**
     * 删除角色信息
     * @param role
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Role role) {
        roleService.deleteRole(role);
        return success("删除成功");
    }

    /**
     * 展示授权页面
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("toRoleGrantPage")
    public String toRoleGrantPage(Integer roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "role/grant";
    }


//    /*查询资源*/
//    @RequestMapping("findModules")
//    @ResponseBody
//    public List<TreeDto> findeAll(Integer roleId){
//        System.out.println(roleId);
//        System.out.println("findModules____________");
//        return moduleService.findAll(roleId);
//    }

    /**
     * 查询所有角色名
     * @return
     */
    @RequestMapping("queryAllRole")
    @ResponseBody
    public List<Map<String, Object>> selectAllRole(Integer userId) {
        return roleService.seleceAllRole(userId);
    }

    /**
     * 给角色添加权限
     * @param mids
     * @param roleId
     * @return
     */
    @RequestMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer[] mids,Integer roleId){
        roleService.addGrant(mids,roleId);
        return success("权限添加成功");
    }
}

