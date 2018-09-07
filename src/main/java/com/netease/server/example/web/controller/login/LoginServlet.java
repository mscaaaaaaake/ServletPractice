package com.netease.server.example.web.controller.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: <文件描述>
 * @modified: <文件修改说明>
 * @auther: ZhuoZhiling
 * @date: 2018/9/7 11:36
 * @version: 2.0.0
 */
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        PrintWriter pw = resp.getWriter();
        if("admin_zzl".equals(account) && "111111".equals(password)){
            pw.print("login success");
        }else {
            pw.print("login fail");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("accountPost");
        String password = req.getParameter("passwordPost");
        PrintWriter pw = resp.getWriter();
        if("admin_zzl".equals(account) && "111111".equals(password)){
            pw.print("login success");
        }else {
            pw.print("login fail");
        }
    }
}
