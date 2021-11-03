package com.yjxxt.ems.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.ems.base.BaseService;
import com.yjxxt.ems.bean.Community;
import com.yjxxt.ems.mapper.CommunityMapper;
import com.yjxxt.ems.query.CommunityQuery;
import com.yjxxt.ems.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService extends BaseService<Community,Integer> {

    @Resource
    private CommunityMapper communityMapper;

    /**
     * 多条件分页查询
     * @param query
     * @return
     */
    public Map<String,Object> queryComByParams(CommunityQuery query){
        Map<String,Object> map=new HashMap<>();
        //初始化分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        //开始分页
        PageInfo<Community> pageInfo=new PageInfo<>(communityMapper.selectByParams(query));
        //准备数据
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }


    /*WXY*/
    //查询所有角色信息
    public List<Map<String, Object>> findRoles(Integer userId) {
        return communityMapper.selectRoles(userId);
    }

}
