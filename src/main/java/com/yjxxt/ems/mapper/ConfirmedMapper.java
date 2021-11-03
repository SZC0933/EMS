package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.Confirmed;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface ConfirmedMapper extends BaseMapper<Confirmed,Integer> {


//    <!--  修改信息  完成版  只修改状态码-->
     Integer uBPKS(Confirmed user);

    //统计当前有多少条要删除的记录
    int countUserRoleNum(Integer userId);

    //删除当前确诊人员的信息
    int deleteUserRoleByUserId(Integer userId);

    //根据Id查询用户
    Confirmed selectId(Integer id);




    @MapKey("")
        //查询所有社区
    List<Map<String, Object>> selectComs();
}