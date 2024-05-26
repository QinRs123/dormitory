package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.DormitoryMapper;
import com.atguigu.cloud.pojo.Dormitory;
import com.atguigu.cloud.service.DormitoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Resource
    private DormitoryMapper mapper;

    @Override
    public int add(Dormitory dorm) {
        return mapper.add(dorm);
    }

    @Override
    public List<Dormitory> getAll() {
        return mapper.getAll();
    }

    @Override
    public int delete(Integer id) {
        return mapper.delete(id);
    }

    @Override
    public int update(Dormitory master) {
        return mapper.update(master);
    }
}
