package com.atguigu.cloud.controller;

import com.atguigu.cloud.pojo.*;
import com.atguigu.cloud.service.DormitoryService;
import com.atguigu.cloud.service.ResideService;
import com.atguigu.cloud.service.RoomService;
import com.atguigu.cloud.service.StuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dorm")
public class DormitoryController {
    @Resource
    private DormitoryService dormitoryService;

    @Resource
    private RoomService roomService;

    @Resource
    private StuService stuService;

    @Resource
    private ResideService resideService;

    @PostMapping("/add")
    public Result add(@RequestBody Dormitory dorm){
        int k=dormitoryService.add(dorm);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("添加失败");
    }

    @GetMapping("/getAll")
    public Result getAll(){
        List<Dormitory> list =dormitoryService.getAll();
        return Result.success(list);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){

        List<Room> roomList=roomService.getByDid(id);
        if(roomList!=null&&roomList.size()!=0){
            for (Room room : roomList) {
                List<Reside> list=resideService.getByRid(room.getId());
                if(list!=null&&list.size()!=0){
                    for (Reside reside : list) {
                        Stu stu = stuService.getById(reside.getSid());
                        stu.setIsReside(0);
                        stuService.update(stu);
                    }
                }
            }
        }
        int k= dormitoryService.delete(id);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("删除失败");
    }
//
    @PostMapping("/update")
    public Result update(@RequestBody Dormitory master){
        System.out.println(master);
        int k= dormitoryService.update(master);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("修改失败");
    }

}
