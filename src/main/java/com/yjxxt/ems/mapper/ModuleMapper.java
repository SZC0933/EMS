package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.Module;
import com.yjxxt.ems.dto.TreeDto;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module, Integer> {


    List<TreeDto> queryAllModules();
}