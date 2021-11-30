package com.bookstore.controller.frontend;

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


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	
   public static final String[] LoginRequiredURLs= {
		   "/view_profile", "/edit_profile","/update_profile","/write_review"
		   };
    public CustomerLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpSession session=httpRequest.getSession(false);
		String path=httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		if(path.startsWith("/admin/")) {
			chain.doFilter(httpRequest, response);
			return;
		}
		
		boolean loggedIn= session!=null && session.getAttribute("loggedCustomer")!=null;
		
		String requestURL= httpRequest.getRequestURI().toString();
		System.out.println("Path: "+path);
		System.out.println("logggedIn: "+ loggedIn);
		if(!loggedIn && isLoggedInRequired(requestURL)){
			String queryString=httpRequest.getQueryString();
			String redirectURL = requestURL;
			if(queryString!=null) {
				redirectURL=requestURL.concat("?").concat(queryString);
			}
			session.setAttribute("redirectURL", redirectURL);
			
			String loginPage="frontend/login.jsp";
			RequestDispatcher requestDispatcher=httpRequest.getRequestDispatcher(loginPage);
			requestDispatcher.forward(httpRequest, response);
		}else {
		chain.doFilter(request, response);
		}
	}

	private boolean isLoggedInRequired(String requestURL) {
		for(String loggedInURL: LoginRequiredURLs) {
			if(requestURL.contains(loggedInURL)) {
				return true;
			}
		}
		return false;
		}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
