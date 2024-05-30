package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Reside;
import com.atguigu.cloud.pojo.Room;

import java.util.List;

public interface ResideService {
    int add(Reside dorm);

    List<Reside> getAll();

    int delete(Integer id);

    int update(Reside master);

    Reside getBy(Integer id);

    Reside getBySid(String stno);

    List<Reside> getByRid(Integer id);


    List<Reside> getByRidAndRid(Integer id, Integer rid);

    int deleteBySid(String sid);

    List<Reside> getByRidAndDid(Integer id, Integer did);
}
