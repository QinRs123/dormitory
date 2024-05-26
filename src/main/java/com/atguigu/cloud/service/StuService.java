package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Stu;

import java.util.List;

public interface StuService {
    int add(Stu dorm);

    List<Stu> getAll();

    int delete(String id);

    int update(Stu master);

    Stu getById(String sid);

    List<String> getStu(Stu search);
}
