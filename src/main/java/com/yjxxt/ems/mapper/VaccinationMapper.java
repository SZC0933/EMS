package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.Vaccination;

public interface VaccinationMapper extends BaseMapper<Vaccination,String> {

    Vaccination selectById(Integer id);

    int deleteVa(Integer[] ids);
}