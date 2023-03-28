package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.dao.UserMeDao;
import com.itheima.domain.UserMe;
import com.itheima.service.UserMeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMeServiceImpl extends ServiceImpl<UserMeDao, UserMe> implements UserMeService{

    @Autowired
    private UserMeDao userMeDao;

    public void setUserMeDao(UserMeDao userMeDao) {
        this.userMeDao = userMeDao;
    }

    @Override
    public IPage<UserMe> getPage(int currentPage, int pageSize, UserMe userMe) {

        LambdaQueryWrapper<UserMe> lqw = new LambdaQueryWrapper();
        lqw.like(Strings.isNotEmpty(userMe.getType()),UserMe::getType,userMe.getType());
        lqw.like(Strings.isNotEmpty(userMe.getName()),UserMe::getName,userMe.getName());
        lqw.like(Strings.isNotEmpty(userMe.getDescription()),UserMe::getDescription,userMe.getDescription());

        IPage<UserMe> page = new Page<>(currentPage,pageSize);
        userMeDao.selectPage(page,lqw);
        return page;
    }
}
