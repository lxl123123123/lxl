package com.itheima.service.impl;

import com.itheima.dao.UserMapper;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {

    private InputStream resourceAsStream = null;
    private SqlSessionFactory sqlSessionFactory = null;
    private SqlSession sqlSession = null;
    private UserMapper mapper = null;

    public List<User> find() throws IOException {
        resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
        mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.findAll();
        return all;
    }

    public void add(User user) throws IOException {
        mapper.insert(user);
    }

    public void del(int id) {
        mapper.delete(id);
    }

    public void up(User user) {
        mapper.update(user);
    }
}
