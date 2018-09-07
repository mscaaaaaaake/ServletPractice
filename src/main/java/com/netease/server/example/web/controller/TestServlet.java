package com.netease.server.example.web.controller;

import com.netease.server.example.util.GeneralUtil;

import java.io.*;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {



	@Override
	public void init(){
		//获取配置文件中全局参数
		ServletContext context = this.getServletContext();
		System.out.println(context.getInitParameter("contextParam1"));
		System.out.println(context.getInitParameter("contextParam2"));
		System.out.println("init method");
		//获取loginServlet中的动态设置的参数
		System.out.println("helloServlet中："+context.getAttribute("account"));
		System.out.println("helloServlet中："+context.getAttribute("password"));
	}

	@Override
	public void destroy(){
		System.out.println("destroy method");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6119506302329930242L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("service method");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		//获取外部文件方法联系（getResource\getResourceAsStream\getRealPath
		//首先通过getResource获取外部资源
		//path对象应该注意相对web的路径
		URL url = context.getResource("/WEB-INF/classes/log4j.properties");
		//拿到文件路径之后获取文件流，
		InputStream inputStream = url.openStream();
		//通过预写好的工具类获取properties里面的某参数对应的value
		String log4jRootLogger = GeneralUtil.getPropery("log4j.rootLogger",inputStream);
		System.out.println("getResource方法获取的properties文件内的对象log4j.rootLogger的内容为：/n"+log4jRootLogger);
		//通过getResourceAsSteam方法获取外部资源（对比上一步，节省了转换的步骤
		InputStream inputStream1 = context.getResourceAsStream("/WEB-INF/classes/log4j.properties");
		String log4jRootLogger1 = GeneralUtil.getPropery("log4j.rootLogger",inputStream1);
		System.out.println("getResourceAsSteam方法获取的properties文件内的对象log4j.rootLogger的内容为：/n"+log4jRootLogger1);
		//通过getRealPath方法获取外部资源
		String path = context.getRealPath("/WEB-INF/classes/log4j.properties");
		System.out.println(path);
		//通过绝对路径获取file对象
		File file = new File(path);
		InputStream inputStream2 = new FileInputStream(file);
		String log4jRootLogger2 = GeneralUtil.getPropery("log4j.rootLogger",inputStream2);
		System.out.println("getRealPath方法获取的properties文件内的对象log4j.rootLogger的内容为：/n"+log4jRootLogger2);

	}

}
