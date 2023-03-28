package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userDao extends BaseMapper<User> {

//    @Select("select * from tbl_user where username=#{username} and password=#{password}")
//    public User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    @Select("select * from tbl_user where phone=#{phone}")
    public User findByPhone(@Param("phone") String phone);

}
