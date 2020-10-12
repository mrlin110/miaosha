package com.mrlin.miaosha.dao;

import com.mrlin.miaosha.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from user where id=#{id}")
    User getById(@Param("id") int id);
}
