package com.atguigu.cloud.controller;

import com.atguigu.cloud.pojo.*;
import com.atguigu.cloud.service.ResideService;
import com.atguigu.cloud.service.RoomService;
import com.atguigu.cloud.service.StuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/reside")
public class ResideController {

    @Resource
    private ResideService resideService;

    @Resource
    private StuService stuService;

    @Resource
    private RoomService roomService;



    @PostMapping("/add")
    public Result add(@RequestBody Reside dorm){
        Stu stu=stuService.getById(dorm.getSid());
        Room room= roomService.getById(dorm.getRid());
        if(stu==null||room==null){
            return Result.error("学号不对或者宿舍号不对");
        }
        if(stu.getGender()!=room.getType()){
            return Result.error("学生性别与宿舍类型不匹配");
        }
        if(stu.getIsReside()!=0){
            return Result.error("该学生已经在别的宿舍居住了");
        }
        if(room.getNum()==room.getCnum()){
            return Result.error("宿舍已满人，请选择别的宿舍");
        }
        room.setCnum(room.getCnum()+1);
        int update = roomService.update(room);
        stu.setIsReside(1);
        int update1 = stuService.update(stu);
        dorm.setStname(stu.getStname());

        int k=resideService.add(dorm);
        if(k!=0&&update1!=0&&update!=0){
            return Result.success(k);
        }
        return Result.error("添加失败");
    }

    @GetMapping("/getAll")
    public Result getAll(){
        List<Reside> list =resideService.getAll();
        List<ResideShow> resideShows=new ArrayList<>();
        for (Reside reside : list) {
            Stu stu = stuService.getById(reside.getSid());
            Room room = roomService.getById(reside.getRid());
            ResideShow reShow =new ResideShow();
            reShow.setId(reside.getId());
            reShow.setSid(stu.getStno());
            reShow.setStname(stu.getStname());
            reShow.setRid(room.getId());
            reShow.setType(room.getType());
            reShow.setDid(room.getDid());

            resideShows.add(reShow);
        }
        return Result.success(resideShows);
    }
    @GetMapping("/getByRid/{id}")
    public Result getByRid(@PathVariable("id") String id){
        List<Reside> list =resideService.getByRid(id);
        System.out.println(list);
        List<ResideShow> resideShows=new ArrayList<>();
        for (Reside reside : list) {
            Stu stu = stuService.getById(reside.getSid());
            Room room = roomService.getById(reside.getRid());
            ResideShow reShow =new ResideShow();
            reShow.setId(reside.getId());
            reShow.setSid(stu.getStno());
            reShow.setStname(stu.getStname());
            reShow.setRid(room.getId());
            reShow.setType(room.getType());
            reShow.setDid(room.getDid());
            resideShows.add(reShow);
        }
        System.out.println(resideShows);
        return Result.success(resideShows);
    }

    @GetMapping("/getBoys")
    public Result getBoys(){
        List<Reside> list =resideService.getAll();
        List<ResideShow> resideShows=new ArrayList<>();
        for (Reside reside : list) {

            Stu stu = stuService.getById(reside.getSid());
            Room room = roomService.getById(reside.getRid());
            if(room.getType()!=1){
                continue;
            }
            ResideShow reShow =new ResideShow();
            reShow.setId(reside.getId());
            reShow.setSid(stu.getStno());
            reShow.setStname(stu.getStname());
            reShow.setRid(room.getId());
            reShow.setType(room.getType());
            reShow.setDid(room.getDid());
            resideShows.add(reShow);
        }
        return Result.success(resideShows);
    }

    @GetMapping("/getGirls")
    public Result getGirls(){
        List<Reside> list =resideService.getAll();
        List<ResideShow> resideShows=new ArrayList<>();
        for (Reside reside : list) {

            Stu stu = stuService.getById(reside.getSid());
            Room room = roomService.getById(reside.getRid());
            if(room.getType()!=2){
                continue;
            }
            ResideShow reShow =new ResideShow();
            reShow.setId(reside.getId());
            reShow.setSid(stu.getStno());
            reShow.setStname(stu.getStname());
            reShow.setRid(room.getId());
            reShow.setType(room.getType());
            reShow.setDid(room.getDid());
            resideShows.add(reShow);
        }
        return Result.success(resideShows);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){

        Reside pre= resideService.getBy(id);
        Room room = roomService.getById(pre.getRid());
        if(pre==null||room==null){
            return Result.error("error");
        }
        //学生改为入住
        Stu byId = stuService.getById(pre.getSid());
        byId.setIsReside(0);
        stuService.update(byId);

        //宿舍当前人数减一
        room.setCnum(room.getCnum()-1);
        roomService.update(room);
        int k= resideService.delete(id);
        if(k!=0){
            return Result.success(k);
        }
        return Result.error("删除失败");
    }


    @PostMapping("/update")
    public Result update(@RequestBody Reside master){
        Reside by = resideService.getBy(master.getId());
        Room byId = roomService.getById(by.getRid());
        byId.setCnum(byId.getCnum()-1);
        roomService.update(byId);

        Room byId1 = roomService.getById(master.getRid());
        if(byId1==null){
            return Result.error("宿舍号错误...");
        }


        if(byId1.getNum()==byId1.getCnum()){
            return Result.error("宿舍已满人，请选择别的宿舍");
        }
        byId1.setCnum(byId1.getCnum()+1);
        int update = roomService.update(byId1);

        int k= resideService.update(master);
        if(k!=0 &&update!=0){
            return Result.success(k);
        }
        return Result.error("修改失败");
    }
}
