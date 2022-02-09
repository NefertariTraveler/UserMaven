package com.javacto.service;

import com.javacto.dao.UserDao;
import com.javacto.dao.UserDaoImpl;
import com.javacto.po.User;
import com.javacto.utils.PageInfo;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * 业务实现类
 * liu
 **/
public class UserServiceImpl implements UserService{
    //业务层需要调用dao层的方法   接口 变量名 = new 实现类();  new出来的对象只给当前类使用 加 private
    private UserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param userName
     * @param pwd
     * @return
     */

    public User login(String userName, String pwd) {
        return userDao.login(userName,pwd);
    }

    /**
     * 添加
     */
    public int addUser(User user){
        //调用方法
        return userDao.addUser(user);
    }

    /**
     *删除
     */
    public int deleteUser(int id){
        return userDao.deleteUser(id);
    }

    /**
     *修改
     */
    public int updateUser(User user){
        return userDao.updateUser(user);
    }

    /**
     *Id查询
     */
    public User findUserById(int id){
        return userDao.findUserById(id);
    }

    /**
     *全部查询
     */
    public List<User> queryAll(){
        return userDao.queryAll();
    }


    public int getTotalCount(User user) {
        return userDao.getTotalCount(user);
    }


    public List<User> pageQueryUser(PageInfo pageInfo, User user) {
        return userDao.pageQueryUser(pageInfo,user);
    }


    public boolean findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }
}
