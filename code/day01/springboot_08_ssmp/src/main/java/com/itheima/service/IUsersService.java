package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.domain.Users;

public interface IUsersService extends IService<Users> {

    public IPage<Users> getPage(int currentPage,int pageSize,Users users);

}
