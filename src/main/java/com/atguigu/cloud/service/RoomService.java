package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Room;

import java.util.List;

public interface RoomService {
    int add(Room dorm);

    List<Room> getAll();

    int delete(Integer id);

    int update(Room master);

    Room getById(String rid);

    List<Room> getByDid(Integer id);

    List<String> getnotFull(Room search);

    int delete1(Integer id, Integer rid);

    Room getByIdAndDid(Integer id, Integer rid);

    List<Room> getByGender(Integer gender);
}
