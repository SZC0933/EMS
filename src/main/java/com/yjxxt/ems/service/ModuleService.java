package com.yjxxt.ems.service;

import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.Module;
import com.yjxxt.ems.dto.TreeDto;
import com.yjxxt.ems.mapper.ModuleMapper;
import com.yjxxt.ems.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 查询所有module
     * @return
     */
    public List<TreeDto> queryAllModules(){
        return moduleMapper.queryAllModules();
    }

    public List<TreeDto> queryAllModules02(Integer roleId) {
        // 获取所有资源信息
        List<TreeDto> tlist = moduleMapper.queryAllModules();
        // 获取当前角色拥有的资源信息
        List<Integer> roleHasModules = permissionMapper.selectModuleByRoleId(roleId);
        // 遍历
        for (TreeDto treeDto : tlist) {
            if (roleHasModules.contains(treeDto.getId())) {
                treeDto.setChecked(true);
            }
        }
        return tlist;
    }
}
