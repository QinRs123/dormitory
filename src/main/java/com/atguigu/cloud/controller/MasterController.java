package com.atguigu.cloud.controller;

import com.atguigu.cloud.pojo.Master;
import com.atguigu.cloud.pojo.Result;
import com.atguigu.cloud.service.MasterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master")
@Slf4j
public class MasterController {

    @Resource
    private MasterService masterService;

    @PostMapping("/add")
    public Result add(@RequestBody Master master){
        int k=masterService.add(master);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("添加失败");
    }

    @GetMapping("/getAll")
    public Result getAll(){
        List<Master> list =masterService.getAll();
        return Result.success(list);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id){
       int k= masterService.delete(id);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("删除失败");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Master master){
        System.out.println(master);
        int k= masterService.update(master);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("修改失败");
    }

    @PostMapping("/getBySome")
    public Result getBySome(@RequestBody Master master){
        System.out.println(master);
        List<Master> list=masterService.getBySome(master);
        return Result.success(list);
    }
}
