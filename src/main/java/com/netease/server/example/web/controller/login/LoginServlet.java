package com.netease.server.example.web.controller.login;

import com.netease.server.example.util.GeneralUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

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
        //配置文件中全局静态context参数获取
        ServletContext context = this.getServletContext();
        System.out.println("静态context参数"+context.getInitParameter("contextParam1"));
        System.out.println("静态context参数"+context.getInitParameter("contextParam2"));
        //servlet内部参数获取
        ServletConfig servletConfig = this.getServletConfig();
        System.out.println("servlet内部参数"+servletConfig.getInitParameter("data1"));
        System.out.println("servlet内部参数"+servletConfig.getInitParameter("data2"));
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        //动态设置全局参数(在TestServlet内写了获取的方法）
        context.setAttribute("account",account);
        context.setAttribute("password",password);
        //打印出页面数据
        PrintWriter pw = resp.getWriter();
        pw.println("account:"+account);
        pw.println("password:"+password);
        if("admin_zzl".equals(account) && "111111".equals(password)){
            pw.println("login success");
        }else {
            pw.println("login fail");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("accountPost");
        String password = req.getParameter("passwordPost");
        PrintWriter pw = resp.getWriter();
        pw.println("account:"+account);
        pw.println("password:"+password);
        if("admin_zzl".equals(account) && "111111".equals(password)){
            pw.println("login success");
        }else {
            pw.println("login fail");
        }
    }
}
