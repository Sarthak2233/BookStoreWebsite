package com.bookstore.controller.admin.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.CustomerServices;

@WebServlet("/admin/list_customer")
public class ListCustomerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ListCustomerservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices  customerServices=new CustomerServices(request, response);
		customerServices.listCustomers();
	}

}
