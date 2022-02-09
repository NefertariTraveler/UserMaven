package com.javacto.action;

import com.javacto.po.User;
import com.javacto.service.UserService;
import com.javacto.service.UserServiceImpl;
import com.javacto.utils.PageInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * liu
 **/
@WebServlet("/pageUser.do")
public class PageUserAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String namespace = this.getServletContext().getInitParameter("namespace");
        req.setCharacterEncoding(namespace);
        resp.setContentType("text/html;charset="+namespace);
        String curNo = req.getParameter("pageNo");
        int pageNo = 1;
        if (null != curNo){
            pageNo = Integer.parseInt(curNo);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(8);
        UserService userService = new UserServiceImpl();
        int totalCount = userService.getTotalCount(null);
        pageInfo.setTotalCount(totalCount);
        List<User> list = userService.pageQueryUser(pageInfo, null);
        req.setAttribute("msg",list);
        req.setAttribute("info",pageInfo);
        req.getRequestDispatcher("/pageUser.jsp").forward(req,resp);
    }
}
