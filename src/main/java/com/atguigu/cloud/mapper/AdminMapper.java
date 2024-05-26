package com.atguigu.cloud.mapper;

import com.atguigu.cloud.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin")
    List<Admin> test();

    @Select("select * from admin where username =#{username} and password=#{password}")
    Admin login(String username, String password);
}
