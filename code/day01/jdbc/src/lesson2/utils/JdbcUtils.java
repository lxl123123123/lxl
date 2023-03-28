package lesson2.utils;

import com.mysql.cj.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            InputStream in = JdbcUtils.class.getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnector() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
    public static void release(Connection conn, Statement st, ResultSet rs){
        try {
            if (rs!=null)
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st!=null)
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null)
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
