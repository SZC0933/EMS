package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.Community;
import org.apache.ibatis.annotations.MapKey;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommunityMapper extends BaseMapper<Community,Integer> {

    int addNumByComId(Integer id);

    int subNumByComId(Integer id);

    @MapKey("")
        //    查询所有角色
    List<Map<String, Object>> selectRoles(Integer userId);

    //通过社区表的 com_id    查询到社区表的对应社区名
    void selectaddresComId(Integer comId);

}