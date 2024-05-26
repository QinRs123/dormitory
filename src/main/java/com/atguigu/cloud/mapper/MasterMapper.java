package com.atguigu.cloud.mapper;

import com.atguigu.cloud.pojo.Master;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MasterMapper {


    int add(Master master);

    @Select("select * from master")
    List<Master> getAll();

    @Delete("delete from master where id= #{id}")
    int delete(String id);

    int update(Master master);

    List<Master> getBySome(Master master);
}
