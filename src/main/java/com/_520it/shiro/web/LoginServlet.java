package com._520it.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet  extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// /login这个路径,当用户登陆失败的时候才会执行到这里
		System.out.println("用户登陆状态:"+SecurityUtils.getSubject().isAuthenticated());

		//把错误信息显示给用户看
		Object loginFailure = req.getAttribute("shiroLoginFailure");
		//根据不同的错误信息显示不同的提示
		String errorMsg = null;
		if(UnknownAccountException.class.getName().equals(loginFailure)){
			errorMsg = "用户名不存在!";
		}else if(IncorrectCredentialsException.class.getName().equals(loginFailure)){
			errorMsg = "密码不正确!";
		}
		req.setAttribute("errorMsg", errorMsg);
		req.getRequestDispatcher("/login.jsp").forward(req, resp);

//		String username =  req.getParameter("username");
//		String password =  req.getParameter("password");
//		if("zhangsan".equals(username)&&"666".equals(password)){
//			req.setAttribute("userName",username);
//			//登陆成功
//			req.getRequestDispatcher("/main").forward(req, resp);
//		}else{
//			if(username!=null)
//				req.setAttribute("errorMsg", "账号密码有误");
//			req.getRequestDispatcher("/login.jsp").forward(req, resp);
//		}
	}
	
}
