package com.atguigu.cloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResideShow {
    private Integer id; //
    private  String sid;
    private String stname;
    private Integer rid; //
    private  Integer did;
    private  Integer type;
}
