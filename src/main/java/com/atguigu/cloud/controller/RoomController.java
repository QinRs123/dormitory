package com.atguigu.cloud.controller;

import com.atguigu.cloud.pojo.Reside;
import com.atguigu.cloud.pojo.Result;
import com.atguigu.cloud.pojo.Room;
import com.atguigu.cloud.pojo.Stu;
import com.atguigu.cloud.service.ResideService;
import com.atguigu.cloud.service.RoomService;
import com.atguigu.cloud.service.StuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    @Resource
    private ResideService resideService;

    @Resource
    private StuService stuService;

    @PostMapping("/add")
    public Result add(@RequestBody Room dorm){
        dorm.setCnum(0);
        int k=roomService.add(dorm);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("添加失败");
    }

    @GetMapping("/getAll")
    public Result getAll(){
        List<Room> list =roomService.getAll();
        return Result.success(list);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id){

        List<Reside> list=resideService.getByRid(id);
        if(list!=null||list.size()!=0){
            for (Reside reside : list) {
                Stu stu = stuService.getById(reside.getSid());
                stu.setIsReside(0);
                stuService.update(stu);
            }
        }
        int k= roomService.delete(id);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("删除失败");
    }
//
    //
    @PostMapping("/update")
    public Result update(@RequestBody Room master){
        int k= roomService.update(master);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("修改失败");
    }

    @PostMapping("/getnotFull")
    public Result getnotFull(@RequestBody Room search){
        List<String> list =roomService.getnotFull(search);
        return Result.success(list);
    }
}
