package lesson01;

import java.sql.*;

public class jdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.用户信息和url
        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "abc123";

        //3.连接成功，数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);

        //4.执行SQL的对象
        Statement statement = connection.createStatement();

        //5.执行SQL的对象 去 执行SQL  可能存在结果，查看返回结果
//        String sql ="SELECT * FROM `student`";
//        String sql ="insert into `student` (`name`,`age`) values ('龙龙',11)";
//        String sql1 = "delete from `student` where name ='李星锐'";
        String sql2 = "update `student` set id = 50 where name ='李莉'";
//        int i = statement.executeUpdate(sql);
//        int i1 = statement.executeUpdate(sql1);
        int i2 = statement.executeUpdate(sql2);
//        if (i>0){
//            System.out.println("插入成功");
//        }
//        if (i1>0){
//            System.out.println("删除成功");
//        }
        if (i2>0){
            System.out.println("修改成功");
        }
//        ResultSet resultSet = statement.executeQuery(sql);
//        while (resultSet.next()){
//            System.out.println("id"+ resultSet.getInt("id"));
//            System.out.println("name"+ resultSet.getString("name"));
//            System.out.println("age"+ resultSet.getInt("age"));
//        }

        //6.释放连接
//        resultSet.close();
        statement.close();
        connection.close();
    }
}
