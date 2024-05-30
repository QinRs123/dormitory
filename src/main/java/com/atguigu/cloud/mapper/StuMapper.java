package com.atguigu.cloud.mapper;

import com.atguigu.cloud.pojo.Stu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper {
    @Insert("insert  into stu(stno, stname, gender, grad, major, hometown, birthday,isReside) " +
            "values(#{stno},#{stname},#{gender},#{grad},#{major},#{hometown},#{birthday},#{isReside}) ")
    int add(Stu dorm);

    @Select("select * from stu")
    List<Stu> getAll();

    @Delete("delete from stu where stno=#{id}")
    int delete(String id);

    int update(Stu master);

    @Select("select * from stu where stno=#{sid}")
    Stu getById(String sid);
//    @Select(("select stno from stu where isReside=0"))
    List<String> getStu(Stu search);

    @Select("select * from stu where isReside=1")
    List<Stu> getIsReside();

    @Select("select * from stu where isReside=0")
    List<Stu> noReside();
}
