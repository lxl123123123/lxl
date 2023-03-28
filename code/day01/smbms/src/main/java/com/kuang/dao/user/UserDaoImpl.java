package com.kuang.dao.user;

import com.kuang.dao.BaseDao;
import com.kuang.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode,String userPassword) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        //如果数据库不为空，也就是连接上数据库了，那就进行底下的操作
        if (connection!=null){
            //首先编写一条sql命令，作为调用方法的参数传进去，用于从数据库表中查到指定的登录用户，通过表中字段userCode来查
            String sql = "select * from smbms_user where userCode=?&&userPassword=?";
            Object[] params = {userCode,userPassword};
            //调用查询方法，执行sql，返回一个ResultSet对象 要调用方法，则需要传递一些参数进去
                rs = BaseDao.execute(connection, pstm, rs, sql, params);
                //方法调用完了，则执行后来你要进行的具体操作(如遍历输出)，这里采用将拿到的值
                //赋值给user对象，进行user对象的属性初始化，因为一个数据库表就对应java中一个类
                //所以一个表中的对象也就对应着java具体类中的对象，所以这里创建了user类
                //将拿到的要登录的表中的用户对象对应成一个user对象，于是乎给user对象初始化，
                //本来其实只有rs.getInt("id") 这种代码，也就是从resultSet中取出遍历
                //而现在是要将这个值放进user对象中
                //本身其实就是表和类对应，表中的字段也就是类中的属性
                if (rs.next()){
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));
                }
                //调用closeResource方法，关闭资源
                BaseDao.closeResource(null,pstm,rs);
        }
        //最终返回要登录的用户
        return user;
    }
    //修改密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection!=null){
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object params[] = {password,id};
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null,pstm,null);
        }
        return execute;
    }
    //根据用户名或者角色查询用户总数
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;

        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");
            ArrayList<Object> list = new ArrayList<Object>();//存放我们的参数

            if (!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.username like ?");
                list.add("%"+username+"%");
            }
            if (userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            Object[] params = list.toArray();//输出最后完整的SQL语句

            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);

            if (rs.next()){
                count = rs.getInt("count");//从结果集中获取数量
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return count;
    }

    public List<User> getUserList(Connection connection, String userName,int userRole,int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return userList;
    }
}
