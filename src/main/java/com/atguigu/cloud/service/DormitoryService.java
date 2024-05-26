package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Dormitory;

import java.util.List;

public interface DormitoryService {
    int add(Dormitory dorm);

    List<Dormitory> getAll();

    int delete(Integer id);

    int update(Dormitory master);
}
