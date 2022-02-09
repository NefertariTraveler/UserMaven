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
@WebServlet("/login.do")
public class loginAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String namespace = this.getServletContext().getInitParameter("namespace");
        req.setCharacterEncoding(namespace);
        resp.setContentType("text/html;charset="+namespace);
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        UserService userService = new UserServiceImpl();
        User login = userService.login(userName, pwd);
        if (null != login){
            req.getRequestDispatcher("/pageUser.do").forward(req,resp);
        }else {
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
