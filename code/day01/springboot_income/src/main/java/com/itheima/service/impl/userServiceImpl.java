package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.controller.utils.CodeUtils;
import com.itheima.dao.userDao;
import com.itheima.domain.User;
import com.itheima.service.userService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl extends ServiceImpl<userDao, User> implements userService {

    @Autowired
    private userDao userDao;

    @Autowired
    private CodeUtils codeUtils;

    public void setCodeUtils(CodeUtils codeUtils) {
        this.codeUtils = codeUtils;
    }

    public void setUserDao(com.itheima.dao.userDao userDao) {
        this.userDao = userDao;
    }

//    @Override
//    public User login(String username, String password) {
//        User user = null;
//
//        try {
//            user = userDao.findByUsernameAndPassword(username, password);
//            return user;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @Override
    public User selectByPhone(String phone) {
        User user = userDao.findByPhone(phone);
        return user;
    }

    @Override
    public User getState(String phone) {
        User user = userDao.findByPhone(phone);
        return user;
    }

//    @Override
//    public IPage<User> getPage(int currentPage, int pageSize, User user) {
//        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
//        lqw.like(Strings.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
//        lqw.like(Strings.isNotEmpty(user.getNumber()),User::getNumber,user.getNumber());
//        lqw.like(Strings.isNotEmpty(user.getPhone()),User::getPhone,user.getPhone());
//
//        IPage<User> page = new Page<>(currentPage,pageSize);
//        userDao.selectPage(page,lqw);
//        return page;
//    }

    @Override
    @CachePut(value = "loginCode",key ="#phone")
    public String sendCodeToSMS(String phone) {
        String code = codeUtils.generator(phone);
        return code;
    }

    @Override
    public boolean checkCode(User user) {
        //取出内存中的验证码与传过来的验证码比对，如果相同，返回true
        String code = user.getCode();  //此时用户输入的
        //get方法通过他传进来的手机号从缓存中取出原本的验证码
        String cacheCode = codeUtils.get(user.getPhone());
        return code.equals(cacheCode);
    }

}
