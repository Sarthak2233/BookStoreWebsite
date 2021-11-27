package com.bookstore.controller.frontend.Customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class ShowCustomerRegisterForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ShowCustomerRegisterForm() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path="frontend/register_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
		
	}

}
