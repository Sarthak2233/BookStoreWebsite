package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

 
    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpSession httpSession=httpRequest.getSession(false);
		String loginURI=httpRequest.getContextPath()+"/admin/login";
		
		boolean loggedIn=httpSession!=null && httpSession.getAttribute("useremail")!=null;
		boolean loggedInRequest=httpRequest.getRequestURI().equals(loginURI);
		boolean loggedInPage=httpRequest.getRequestURI().endsWith("login.jsp");
		
		if(loggedIn && (loggedInRequest||loggedInPage)){
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else if(loggedIn||loggedInRequest) {
		chain.doFilter(request, response);
		}else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
