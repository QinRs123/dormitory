package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.StuMapper;
import com.atguigu.cloud.pojo.Stu;
import com.atguigu.cloud.service.StuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Resource
    private StuMapper stuMapper;
    @Override
    public int add(Stu dorm) {
        return stuMapper.add(dorm);
    }

    @Override
    public List<Stu> getAll() {
        return stuMapper.getAll();
    }

    @Override
    public int delete(String id) {
        return stuMapper.delete(id);
    }

    @Override
    public int update(Stu master) {
        return stuMapper.update(master);
    }

    @Override
    public Stu getById(String sid) {
        return stuMapper.getById(sid);
    }

    @Override
    public List<String> getStu(Stu search) {
        List<String> list=stuMapper.getStu(search);
        return list;
    }

    @Override
    public List<Stu> getIsReside() {
        return stuMapper.getIsReside();
    }

    @Override
    public List<Stu> noReside() {
        return stuMapper.noReside();
    }
}
