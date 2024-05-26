package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Master;

import java.util.List;

public interface MasterService {
    int add(Master master);

    List<Master> getAll();

    int delete(String id);

    int update(Master master);

    List<Master> getBySome(Master master);
}
