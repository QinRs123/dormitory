package com.atguigu.cloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private String id;
    private Integer num;
    private Integer cnum;
    private  Integer did;
    private  Integer type;
}
