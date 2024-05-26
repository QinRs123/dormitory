package com.atguigu.cloud.controller;

import com.atguigu.cloud.pojo.Admin;
import com.atguigu.cloud.pojo.Result;
import com.atguigu.cloud.service.AdminService;
import com.atguigu.cloud.utils.JwtUntil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private AdminService adminService;

    @GetMapping("/test")
    public String test(){
        return "success.....";
    }

    @GetMapping("/admin")
    public Result admin(){
        List<Admin> list =adminService.test();
        return Result.success(list);
    }

    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password){
       Admin admin= adminService.login(username,password);
//       int i=1/0;
        if(admin==null){
            return Result.error("用户或密码错误");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("id",admin.getId());
        map.put("username",admin.getUsername());
        map.put("password",admin.getPassword());
        String jwt = JwtUntil.CreateJwt(map);
        return Result.success(jwt);
    }
}
