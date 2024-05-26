package com.atguigu.cloud.mapper;

import com.atguigu.cloud.pojo.Room;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomMapper {
    @Insert("insert into room (id,num,cnum,did,type) values (#{id},#{num},#{cnum},#{did},#{type})")
    int add(Room dorm);


    @Select("select * from room")
    List<Room> getAll();

    @Delete("delete from room where id=#{id}")
    int delete(String id);

    int update(Room master);

    @Select("select * from room where id= #{rid}")
    Room getById(String rid);

    @Select("select * from room where did= #{id}")
    List<Room> getByDid(Integer id);
//    @Select("select id from room where cnum<num and concat('%',)"
    List<String> getnotFull(Room search);
}
