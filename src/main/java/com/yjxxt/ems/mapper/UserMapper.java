package com.yjxxt.ems.mapper;

import com.yjxxt.ems.base.BaseMapper;
import com.yjxxt.ems.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);


    // 根据name查user
    User selectByName(String userName);

    // 根据phone查user
    User selectByPhone(String phone);

    //根据手机号查询id
    Integer selectById(String tcPhone);

    //修改user表的状态码
    Integer updateById(Integer id);

    //    根据name查询用户
    User selectUserByName(String userName);

    //根据手机号查询用户
    User selectByPhone1(String tcPhone);

    //根据确诊人员的userPhone修改用户健康状态
    void updateUserHealthById(String userPhone);

}