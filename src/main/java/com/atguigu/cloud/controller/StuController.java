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
@RequestMapping("/stu")
public class StuController {
    @Resource
    private StuService stuService;

    @Resource
    private ResideService resideService;

    @Resource
    private RoomService roomService;

    @PostMapping("/add")
    public Result add(@RequestBody Stu dorm){
        System.out.println(dorm);
        dorm.setIsReside(0);
        int k=stuService.add(dorm);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("添加失败");
    }

    @GetMapping("/getAll")
    public Result getAll(){
        List<Stu> list =stuService.getAll();
        return Result.success(list);
    }
    @GetMapping("/getIsReside")
    public Result getIsReside(){
        List<Stu> list =stuService.getIsReside();
        return Result.success(list);
    }
    @GetMapping("/getNoReside")
    public Result noReside(){
        List<Stu> list =stuService.noReside();
        return Result.success(list);
    }

    @GetMapping("/delete/{Stno}")
    public Result delete(@PathVariable("Stno") String sid){
        Stu stu = stuService.getById(sid);
        if(stu.getIsReside()==1){
            Reside reside=resideService.getBySid(stu.getStno());
            Room room = roomService.getByIdAndDid(reside.getRid(),reside.getDid());
            room.setCnum(room.getCnum()-1);
            roomService.update(room);
        }
        int k= stuService.delete(sid);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("删除失败");
    }

//
    @PostMapping("/update")
    public Result update(@RequestBody Stu master){
        System.out.println(master);
        int k= stuService.update(master);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("修改失败");
    }

    @PostMapping("/getStu")
    public Result getStu(@RequestBody Stu search){
        System.out.println(search);

        List<String> list=stuService.getStu(search);

        return Result.success(list);
    }

    @PostMapping("/getRoommates")
    public Result getRoommates(@RequestBody Stu search){
        System.out.println(search);
        Reside reside=resideService.getBySid(search.getStno());
        if(reside==null){
            return Result.success(0);
        }else {
            List<Reside> list = resideService.getByRid(reside.getRid());
            return Result.success(list);
        }
    }


}
