package com.netease.server.example.web.controller.login;

import com.netease.server.example.util.GeneralUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
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

    /**
     * cookie and session 练习
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("accountPost");
        String password = req.getParameter("passwordPost");
        PrintWriter pw = resp.getWriter();

        //黄建cookie对象
        Cookie cookieUserName = new Cookie("userName",account);
        //cookie有效期三十分钟
        cookieUserName.setMaxAge(30 * 60);
        //添加至返回
        resp.addCookie(cookieUserName);
        //第二次访问时获取浏览器带过来的cookie对象
        Cookie[] cookies = req.getCookies();
        //检测是否存在两cookie并赋值
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userName".equals(cookie.getName())) {
                    if(!cookie.getValue().equals(account)){
                        System.out.println("当前用户名："+account+"，与cookie存储不一致");
                    }
                    account = cookie.getValue();
                }
            }
        }
        //session
        //第一次登录
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("pwd",password);
        //第二次登录
        httpSession.removeAttribute("pwd");

        pw.print("<html>");
        pw.print("<body>");
        pw.print("<div style='text-align: center;'>");
        if("admin_zzl".equals(account) && "111111".equals(password)){

            pw.print("<p>account:" +  account + "</p>");
            pw.print("<br>");
            pw.print("<p>password:" + password + "</p>");
            pw.print("<br>");
            pw.print("<p style='color:green;'>login success</p>");
        }else{
            pw.print("<p style='color:red;'>login fail</p>");
        }
        pw.print("</div>");
        pw.print("</body>");
        pw.print("</html>");
    }
}
