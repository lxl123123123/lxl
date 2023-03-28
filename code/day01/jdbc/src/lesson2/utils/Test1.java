package lesson2.utils;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {
    public static void main(String[] args) {
        Connection connector = null;
        Statement st = null;
        ResultSet resultSet = null;
        try {
            connector = JdbcUtils.getConnector();
            st = connector.createStatement();
            String sql = "select * from student where id = 1";
            resultSet = st.executeQuery(sql);
        while (resultSet.next()){
            System.out.println("name="+resultSet.getString("name"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(connector,st,resultSet);
        }
    }
}
