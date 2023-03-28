package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.dao.UsersDao;
import com.itheima.domain.Users;
import com.itheima.service.IUsersService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUsersServiceImpl extends ServiceImpl<UsersDao, Users> implements IUsersService {

    @Autowired
    private UsersDao usersDao;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public IPage<Users> getPage(int currentPage, int pageSize, Users users) {
        LambdaQueryWrapper<Users> lqw = new LambdaQueryWrapper();
        lqw.like(Strings.isNotEmpty(users.getType()),Users::getType,users.getType());
        lqw.like(Strings.isNotEmpty(users.getName()),Users::getName,users.getName());
        lqw.like(Strings.isNotEmpty(users.getDescription()),Users::getDescription,users.getDescription());

        IPage<Users> page = new Page<>(currentPage,pageSize);
        usersDao.selectPage(page,lqw);
        return page;
    }
}
