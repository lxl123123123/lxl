package com.kuang.test;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        String url="jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "abc123";
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //处理sql的对象
        Statement statement = connection.createStatement();
        //编写sql
        String sql = "select * from users";
//        PreparedStatement pre = connection.prepareStatement(sql);
        //执行sql,执行查询sql，返回一个ResultSet结果集
        ResultSet rs = statement.executeQuery(sql);
//        ResultSet rs = pre.executeQuery();
        while (rs.next()){
            System.out.println("id="+rs.getObject("id"));
            System.out.println("name="+rs.getObject("name"));
            System.out.println("password="+rs.getObject("password"));
            System.out.println("email="+rs.getObject("email"));
            System.out.println("birthday="+rs.getObject("birthday"));
        }
        //关闭连接，释放资源(一定要做)
        rs.close();
        statement.close();
        connection.close();
    }
}
