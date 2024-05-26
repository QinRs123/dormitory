package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.MasterMapper;
import com.atguigu.cloud.pojo.Master;
import com.atguigu.cloud.service.MasterService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterServiceImpl  implements MasterService {

    @Resource
    private MasterMapper mapper;
    @Override
    public int add(Master master) {
        return mapper.add(master);
    }

    @Override
    public List<Master> getAll() {
        return mapper.getAll();
    }

    @Override
    public int delete(String id) {
        return  mapper.delete(id);
    }

    @Override
    public int update(Master master) {
        return  mapper.update(master);
    }

    @Override
    public List<Master> getBySome(Master master) {
        return mapper.getBySome(master);
    }
}
