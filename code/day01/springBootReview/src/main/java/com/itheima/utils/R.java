package com.itheima.utils;

import lombok.Data;

@Data
public class R {
    private Boolean flag;
    private Object data;
    private String message;

    public R() {
    }

    public R(boolean flag) {
        this.flag = flag;
    }

    public R(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public R(String message) {
        this.message = message;
    }
}
