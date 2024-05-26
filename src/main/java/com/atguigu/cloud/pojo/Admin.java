package com.atguigu.cloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    private Integer id;
    private  String username;
    private String password;
}
