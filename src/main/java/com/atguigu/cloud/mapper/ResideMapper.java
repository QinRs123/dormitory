package com.atguigu.cloud.mapper;

import com.atguigu.cloud.pojo.Reside;
import com.atguigu.cloud.pojo.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResideMapper {

    @Insert("insert into reside(did,sid,rid) values(#{did},#{sid},#{rid}) ")
    int add(Reside dorm);

    @Select("select * from reside")
    List<Reside> getAll();

    @Delete("delete from reside where did= #{id}")
    int delete(Integer id);

    int update(Reside master);

    @Select("select * from reside where did= #{id}")
    Reside getById(Integer id);

    @Select("select  * from reside where sid=#{stno}")
    Reside getBySid(String stno);

    @Select("select  * from reside where rid=#{id}")
    List<Reside> getByRid(Integer id);

    @Select("select  * from reside where rid=#{id} and did=#{id}")
    List<Reside> getByRidAndRid(Integer id, Integer rid);

    @Delete("delete from reside where sid= #{sid}")
    int deleteBySid(String sid);

    @Select("select  * from reside where rid=#{id} and did=#{did}")
    List<Reside> getByRidAndDid(Integer id, Integer did);
}
