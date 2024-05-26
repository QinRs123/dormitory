package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.ResideMapper;
import com.atguigu.cloud.pojo.Reside;
import com.atguigu.cloud.pojo.Result;
import com.atguigu.cloud.pojo.Room;
import com.atguigu.cloud.pojo.Stu;
import com.atguigu.cloud.service.ResideService;
import com.atguigu.cloud.service.RoomService;
import com.atguigu.cloud.service.StuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResideServiceImpl implements ResideService {

    @Resource
    private ResideMapper resideMapper;

//    @Resource
//    private RoomService roomService;
//    @Resource
//    private StuService stuService;

    @Override
    public int add(Reside dorm) {
        return resideMapper.add(dorm);
    }

    @Override
    public List<Reside> getAll() {
        return resideMapper.getAll();
    }

    @Override
    public int delete(Integer id) {
//        Reside pre= this.getBy(id);
//        Room room = roomService.getById(pre.getRid());
//        if(pre==null||room==null){
//            return 0;
//        }
//        //学生改为入住
//        Stu byId = stuService.getById(pre.getSid());
//        byId.setIsReside(0);
//        stuService.update(byId);
//
//        //宿舍当前人数减一
//        room.setCnum(room.getCnum()-1);
//        roomService.update(room);

        return resideMapper.delete(id);
    }

    @Override
    public int update(Reside master) {
        return resideMapper.update(master);
    }

    @Override
    public Reside getBy(Integer id) {
        return resideMapper.getById(id);
    }

    @Override
    public Reside getBySid(String stno) {
        return resideMapper.getBySid(stno);
    }

    @Override
    public List<Reside> getByRid(String id) {
        return resideMapper.getByRid(id);
    }
}
