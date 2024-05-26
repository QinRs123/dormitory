package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Room;

import java.util.List;

public interface RoomService {
    int add(Room dorm);

    List<Room> getAll();

    int delete(String id);

    int update(Room master);

    Room getById(String rid);

    List<Room> getByDid(Integer id);

    List<String> getnotFull(Room search);
}
