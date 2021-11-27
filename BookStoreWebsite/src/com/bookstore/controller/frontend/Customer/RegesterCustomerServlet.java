package com.bookstore.controller.frontend.Customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.CustomerServices;

/**
 * Servlet implementation class RegesterCustomerServlet
 */
@WebServlet("/register_customer")
public class RegesterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegesterCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomerServices customerServices=new CustomerServices(request, response);
		customerServices.registerCustomer();
	}

}
