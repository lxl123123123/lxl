package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.itheima.domain.UserMe;

@Mapper
public interface UserMeDao extends BaseMapper<UserMe> {
}
