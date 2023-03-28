package com.kuang.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

//编写基础公共类
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    //读取配置文件
    static {
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }
    //获取数据库连接
    public static Connection getConnection(){
        //加载驱动
        Connection connection = null;
        try {
            //加载驱动
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    //编写查询公共方法
    public static ResultSet execute(Connection connection,PreparedStatement pstm,ResultSet rs, String sql,Object[] params) throws SQLException {
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }
        rs = pstm.executeQuery();
        return rs;
    }
    //编写增删改公共方法
    public static int execute(Connection connection,PreparedStatement pstm, String sql,Object[] params) throws SQLException {
        pstm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            pstm.setObject(i + 1, params[i]);
        }
        int updateRows = pstm.executeUpdate();
        return updateRows;
    }
    //关闭连接
    public static Boolean closeResource(Connection connection,PreparedStatement pstm,ResultSet rs){
        Boolean flag = true;
        if (rs!=null){
            try {
                rs.close();
                rs=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if (pstm!=null){
            try {
                pstm.close();
                pstm=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if (connection!=null){
            try {
                connection.close();
                connection=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }
}
