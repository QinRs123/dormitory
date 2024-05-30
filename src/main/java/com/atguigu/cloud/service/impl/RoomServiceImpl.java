package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.RoomMapper;
import com.atguigu.cloud.pojo.Reside;
import com.atguigu.cloud.pojo.Room;
import com.atguigu.cloud.service.ResideService;
import com.atguigu.cloud.service.RoomService;
import com.atguigu.cloud.service.StuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Resource
    private RoomMapper roomMapper;


    @Override
    public int add(Room dorm) {
        return roomMapper.add(dorm);
    }

    @Override
    public List<Room> getAll() {
        return roomMapper.getAll();
    }

    @Override
    public int delete(Integer id) {

        return roomMapper.delete(id);
    }

    @Override
    public int update(Room master) {
        return roomMapper.update(master);
    }

    @Override
    public Room getById(String rid) {
        return  roomMapper.getById(rid);
    }

    @Override
    public List<Room> getByDid(Integer id) {
        return roomMapper.getByDid(id);
    }

    @Override
    public List<String> getnotFull(Room search) {
        List<String> list=roomMapper.getnotFull(search);
        return list;
    }

    @Override
    public int delete1(Integer id, Integer rid) {
        return roomMapper.delete1(id,rid);
    }

    @Override
    public Room getByIdAndDid(Integer id, Integer did) {
        return  roomMapper.getByDidAndId(id,did);
    }

    @Override
    public List<Room> getByGender(Integer gender) {
        return roomMapper.getByGender(gender);
    }
}
