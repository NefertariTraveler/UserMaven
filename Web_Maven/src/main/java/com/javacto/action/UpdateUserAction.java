package com.javacto.action;

import com.javacto.po.User;
import com.javacto.service.UserService;
import com.javacto.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * liu
 **/
@WebServlet("/updateUser.do")
public class UpdateUserAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String namespace = this.getServletContext().getInitParameter("namespace");
        req.setCharacterEncoding(namespace);
        resp.setContentType("text/html;charset="+namespace);
        String strId = req.getParameter("id");
        int id = 0;
        if (null != strId){
            id = Integer.parseInt(strId);
        }
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        String address = req.getParameter("address");
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPwd(pwd);
        user.setSex(sex);
        user.setAddress(address);
        UserService userService = new UserServiceImpl();
        userService.updateUser(user);
        req.getRequestDispatcher("/pageUser.do").forward(req,resp);
    }
}

