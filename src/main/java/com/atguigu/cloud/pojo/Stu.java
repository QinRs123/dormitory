package com.atguigu.cloud.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    private  String stno;
    private String stname;
    private  Integer gender;
    private Integer grad;
    private String major;
    private String hometown;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    private Integer isReside;

}
