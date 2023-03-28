package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UsersDao;
import com.itheima.domain.Users;
import com.itheima.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public Boolean save(Users users) {
        return usersDao.insert(users) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return usersDao.deleteById(id) > 0;
    }

    @Override
    public Boolean update(Users users) {
        return usersDao.updateById(users) > 0;
    }

    @Override
    public Users getById(Integer id) {
        return usersDao.selectById(id);
    }

    @Override
    public List<Users> getAll() {
        return usersDao.selectList(null);
    }

    @Override
    public IPage<Users> getPage(int currentPage, int pageSize) {
        IPage<Users> page = new Page<>(currentPage,pageSize);
        return usersDao.selectPage(page,null);
    }
}
