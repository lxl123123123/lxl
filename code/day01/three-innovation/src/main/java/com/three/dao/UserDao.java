package com.three.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.three.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
