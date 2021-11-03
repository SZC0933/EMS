package com.yjxxt.ems.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.ems.bean.Role;
import com.yjxxt.ems.bean.Vaccination;
import com.yjxxt.ems.mapper.UserMapper;
import com.yjxxt.ems.mapper.VaccinationMapper;
import com.yjxxt.ems.query.VaccinationQuery;
import com.yjxxt.ems.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class VaccinationService {

    @Resource
    VaccinationMapper vaccinationMapper;

    /*多条件查询*/
    public Map<String,Object> selectAll(VaccinationQuery vaccinationQuery) {
        //创建map
        Map<String,Object> map =new HashMap<String,Object>();
        //查数据并分页
        PageHelper.startPage(vaccinationQuery.getPage(),vaccinationQuery.getLimit());
        PageInfo<Vaccination> pageInfo=new PageInfo<>(vaccinationMapper.selectByParams(vaccinationQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());

        return map;
    }
    /*通过ID获取对象*/
    public Vaccination selectId(Integer id) {
        return vaccinationMapper.selectById(id);
    }

    /*添加*/
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertVaccination(Vaccination vaccination) {
        //审核
        checkOK(vaccination);
        vaccination.setFirstDate(new Date());
        vaccination.setSecondDate(new Date());
        //插入
        AssertUtil.isTrue(vaccinationMapper.insertSelective(vaccination)<1,"插入失败");
    }

    private void checkOK(Vaccination vaccinatio){
        AssertUtil.isTrue(vaccinatio==null,"请输入添加的角色");
        AssertUtil.isTrue(StringUtils.isBlank(vaccinatio.getTrueName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(vaccinatio.getFirst()),"请填写(是/否)");
        AssertUtil.isTrue(StringUtils.isBlank(vaccinatio.getSecond()),"请填写(是/否)");
    }

    /*删除*/
    public void delete(Integer[] ids) {
        AssertUtil.isTrue(ids==null||ids.length==0,"请选择要删除的用户");
        AssertUtil.isTrue(vaccinationMapper.deleteVa(ids)!=ids.length,"删除失败~~~");
    }

    /*编辑*/
    public void updateVa(Vaccination vaccination) {
        checkOK(vaccination);
        if(vaccination.getFirst()==null||"否".equals(vaccination.getFirst())){
            vaccination.setFirstDate(null);
        }
        if(vaccination.getSecond()==null||"否".equals(vaccination.getSecond())){
            vaccination.setSecondDate(null);
        }
        if("是".equals(vaccination.getFirst())){
            vaccination.setFirstDate(new Date());
        }
        if("是".equals(vaccination.getSecond())){
            vaccination.setSecondDate(new Date());
        }
        AssertUtil.isTrue(vaccinationMapper.updateByPrimaryKeySelective(vaccination)<1,"修改失败~");

    }
}
