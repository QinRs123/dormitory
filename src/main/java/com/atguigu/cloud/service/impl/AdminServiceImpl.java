package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.AdminMapper;
import com.atguigu.cloud.pojo.Admin;
import com.atguigu.cloud.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Override
    public List<Admin> test() {
        return adminMapper.test();
    }

    @Override
    public Admin login(String usrname, String password) {
        return adminMapper.login(usrname,password);
    }
}
