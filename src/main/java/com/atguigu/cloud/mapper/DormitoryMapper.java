package com.atguigu.cloud.mapper;

import com.atguigu.cloud.pojo.Dormitory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DormitoryMapper {

    @Insert("insert into dormitory (id,fnum,location,mid)values (null,#{fnum},#{location},#{mid});")
    int add(Dormitory dorm);

    @Select("select * from dormitory")
    List<Dormitory> getAll();

    @Delete("delete from dormitory where id=#{id}")
    int delete(Integer id);

    int update(Dormitory master);
}
