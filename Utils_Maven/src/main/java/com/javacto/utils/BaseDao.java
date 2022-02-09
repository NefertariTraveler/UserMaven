package com.javacto.utils;

import com.javacto.po.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * liu
 **/
public class BaseDao {
    private static String driverManagerName;
    private static String url;
    private static String userName;
    private static String pwd;

    //类加载之前会执行的代码
    static {
        init();
    }

    //这个方法代码大家都不需要会写，也不用记，会复制就可以，因为以后不会这样写  现在是讲思路
    private static void init() {
        //这个方法只有一个目的，拿到db.properties 的信息 通过键获取值
        ///1.创建Properties
        Properties ps = new Properties();
        //2.拿到文件路径
        String path = "db.properties";
        //3.通过输出流读取db.properties 中的信息 数据
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream(path);
        try {
            //4.把读到到的数据 加载到Properties
            ps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //5.通过键  获取值，值都在  Properties 对象中
        //System.out.println(ps.get("db.driverName"));
        //System.out.println(ps.getProperty("db.url"));

        //赋值  大家必需通过    System.out.println(ps.get("db.driverName")); 输出 有值了再赋值
        driverManagerName=ps.getProperty("db.driverManagerName");
        url=ps.getProperty("db.url");
        userName=ps.getProperty("db.userName");
        pwd=ps.getProperty("db.pwd");
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driverManagerName);
            //2.建立连接  Connection
            conn = DriverManager.getConnection(url,userName,pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //希望所有同学，每次创建新的工程，如果使用BaseDao, 请务必这样测试
    public static void main(String[] args) {
        System.out.println(BaseDao.getConnection());
    }

    public static void closeAll(ResultSet resultSet, PreparedStatement pstm, Connection conn){
        //7.释放资源
        try {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != pstm) {
                pstm.close();
            }
            if (null != conn) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 封装DML
     */
    public static int executeUpdate(String sql,Object sbq[]){
        int num = 0;
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            for (int i = 0; i < sbq.length; i++) {
                pstm.setObject(i+1,sbq[i]);
            }
            num = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(null,pstm,conn);
        }
        return num;
    }

    public static User executeQueryById(String sql,Object sbq[]){
        User user = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            pstm = conn.prepareStatement(sql);
            for (int i = 0; i < sbq.length; i++) {
                pstm.setObject(i+1,sbq[i]);
            }
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("t_id"));
                user.setUserName(resultSet.getString("t_name"));
                user.setPwd(resultSet.getString("t_password"));
                user.setSex(resultSet.getString("t_sex"));
                user.setDate(resultSet.getDate("t_date"));
                user.setAddress(resultSet.getString("t_address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(resultSet,pstm,conn);
        }
        return user;
    }

    public static List<User> executeQueryAll(String sql){
        List<User> list = new ArrayList<User>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            pstm = conn.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("t_id"));
                user.setUserName(resultSet.getString("t_name"));
                user.setPwd(resultSet.getString("t_password"));
                user.setSex(resultSet.getString("t_sex"));
                user.setDate(resultSet.getDate("t_date"));
                user.setAddress(resultSet.getString("t_address"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(resultSet,pstm,conn);
        }
        return list;
    }

    public static User login(String sql,Object sbq[]){
        User user = null;
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            pstm = conn.prepareStatement(sql);
            for (int i = 0; i < sbq.length; i++) {
                pstm.setObject(i+1,sbq[i]);
            }
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("t_id"));
                user.setUserName(resultSet.getString("t_name"));
                user.setPwd(resultSet.getString("t_password"));
                user.setSex(resultSet.getString("t_sex"));
                user.setDate(resultSet.getDate("t_date"));
                user.setAddress(resultSet.getString("t_address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(resultSet,pstm,conn);
        }
        return user;
    }
}
