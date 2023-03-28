package com.itheima.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_book")
public class UserMe {

    private Integer id;
    private String type;
    private String name;
    private String description;

}
