package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.domain.UserMe;

public interface UserMeService extends IService<UserMe>{

    public IPage<UserMe> getPage(int currentPage,int pageSize,UserMe userMe);

}
