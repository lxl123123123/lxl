package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Users;

import java.util.List;

public interface UsersService {

    public Boolean save(Users users);
    public Boolean delete(Integer id);
    public Boolean update(Users users);
    public Users getById(Integer id);
    public List<Users> getAll();
    public IPage<Users> getPage(int currentPage,int pageSize);

}
