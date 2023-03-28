package com.kuang.dao.user;

import com.kuang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public User getLoginUser(Connection connection,String userCode,String userPassword) throws SQLException;
    public int UpdatePwd(Connection connection,int id,String password) throws SQLException;
    public int getUserCount(Connection connection,String username,int userRole) throws SQLException;
    public List<User> getUserList(Connection connection,String username,int userRole,int currentPageNo,int pageSize) throws SQLException;
}
