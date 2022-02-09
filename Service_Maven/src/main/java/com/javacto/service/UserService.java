package com.javacto.service;

import com.javacto.po.User;
import com.javacto.utils.PageInfo;

import java.util.List;

/**
 * 业务层接口
 * liu
 **/
public interface UserService {
    /**
     * 登录
     */
    public User login(String userName, String pwd);

    /**
     * 添加
     */
    public int addUser(User user);

    /**
     *删除
     */
    public int deleteUser(int id);

    /**
     *修改
     */
    public int updateUser(User user);

    /**
     *Id查询
     */
    public User findUserById(int id);

    /**
     *全部查询
     */
    public List<User> queryAll();

    /**
     * 查询总条数
     */
    public int getTotalCount(User user);

    /**
     * 分页查询
     */
    public List<User> pageQueryUser(PageInfo pageInfo, User user);

    /**
     * 用户名查询
     */
    public boolean findUserByName(String userName);
}
