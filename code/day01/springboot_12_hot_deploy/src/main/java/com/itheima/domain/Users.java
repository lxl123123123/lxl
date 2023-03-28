package com.itheima.domain;
//lombok

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("tbl_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Integer id;
    private String type;
    private String name;
    private String description;

}
