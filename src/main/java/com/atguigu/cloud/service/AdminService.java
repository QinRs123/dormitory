package com.atguigu.cloud.service;

import com.atguigu.cloud.pojo.Admin;
import com.atguigu.cloud.pojo.Result;

import java.util.List;

public interface AdminService {
    List<Admin> test();

    Admin login(String usrname, String password);
}
