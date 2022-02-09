package com.javacto.dao;

import com.javacto.po.User;
import com.javacto.utils.BaseDao;
import com.javacto.utils.PageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * dao层实现类
 * liu
 **/
//很多同学忘记这里，有些同学学到后面框架了，还会有这种问题 implements
public class UserDaoImpl implements UserDao{
    /**
     * 添加  删除  修改 每次只是SQL语句不一样， 还有传的参数不一样
     */

    /**
     * 登录
     * @param userName
     * @param pwd
     * @return
     */
    @Override
    public User login(String userName, String pwd) {
        String sql = "SELECT * FROM tuser WHERE t_name = ? AND t_password = ?";
        Object sbq[] = {userName,pwd};
        return BaseDao.login(sql,sbq);
    }

    /**
     * 添加
     */
    public int addUser(User user){
        String sql = "INSERT INTO TUSER(t_name,t_password,t_sex,t_date,t_address,t_state) VALUES(?,?,?,NOW(),?,0)";
        Object sbq[] = {user.getUserName(),user.getPwd(),user.getSex(),user.getAddress()};
        return BaseDao.executeUpdate(sql,sbq);
    }

    /**
     *删除
     */
    public int deleteUser(int id){
        String sql = "DELETE FROM TUSER WHERE t_id = ?";
        Object sbq[] = {id};
        return BaseDao.executeUpdate(sql,sbq);
    }

    /**
     *修改
     */
    public int updateUser(User user){
        String sql = "UPDATE TUSER SET t_name = ?,t_password = ?,t_sex = ?,t_address = ? WHERE t_id = ?";
        Object sbq[] = {user.getUserName(),user.getPwd(),user.getSex(),user.getAddress(),user.getId()};
        return BaseDao.executeUpdate(sql,sbq);
    }

    /**
     *Id查询
     */
    public User findUserById(int id){
        String sql = "SELECT * FROM TUSER WHERE t_id = ?";
        Object sbq[] = {id};
        return BaseDao.executeQueryById(sql,sbq);
    }

    /**
     *全部查询
     */
    public List<User> queryAll(){
        String sql = "SELECT * FROM TUSER";
        return BaseDao.executeQueryAll(sql);
    }

    @Override
    public int getTotalCount(User user) {
        int count = 0;
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT COUNT(*) FROM TUSER";
            pstm = conn.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(resultSet,pstm,conn);
        }
        return count;
    }

    @Override
    public List<User> pageQueryUser(PageInfo pageInfo, User user) {
        List<User> list = new ArrayList<User>();
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM TUSER LIMIT ?,? ";
            pstm = conn.prepareStatement(sql);
            int begin = (pageInfo.getPageNo() - 1) * pageInfo.getPageSize();
            int end = pageInfo.getPageSize();
            pstm.setObject(1,begin);
            pstm.setObject(2,end);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                User user1 = new User();
                user1.setId(resultSet.getInt("t_id"));
                user1.setUserName(resultSet.getString("t_name"));
                user1.setPwd(resultSet.getString("t_password"));
                user1.setSex(resultSet.getString("t_sex"));
                user1.setDate(resultSet.getDate("t_date"));
                user1.setAddress(resultSet.getString("t_address"));
                list.add(user1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(resultSet,pstm,conn);
        }
        return list;
    }

    @Override
    public boolean findUserByName(String userName) {
        boolean b = false;
        Connection conn = BaseDao.getConnection();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM TUSER WHERE t_name = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setObject(1,userName);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeAll(resultSet,pstm,conn);
        }
        return b;
    }
}
