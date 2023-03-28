package com.itheima.test;

import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    @Test //查询
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }

    @Test //插入
    public void test2() throws IOException {
        //模拟真正开发中web-controller层传来的user对象到service层再到dao层
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作
        sqlSession.insert("userMapper.save",user);
        //mybatis更新操作需要手动提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test //修改
    public void test3() throws IOException {
        //模拟真正开发中web-controller层传来的user对象到service层再到dao层
        User user = new User();
        user.setId(6);
        user.setUsername("lucy");
        user.setPassword("123");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作
        sqlSession.update("userMapper.update",user);
        //mybatis更新操作需要手动提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test //删除
    public void test4() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作
        sqlSession.update("userMapper.delete",6);
        //mybatis更新操作需要手动提交事务,也可以调用openSession(true)重载方法,里面参数设置为true
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

}
