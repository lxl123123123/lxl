package com.itheima.controller.utils;


import lombok.Data;

@Data
public class R {

    private Boolean flag;
    private Object data;
    private String msg;

    public R() {
    }

    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, Object date) {
        this.flag = flag;
        this.data = date;
    }

    public R(Boolean flag, String message) {
        this.flag = flag;
        this.msg = message;
    }

    public R(String message) {
        this.msg = message;
    }
}
