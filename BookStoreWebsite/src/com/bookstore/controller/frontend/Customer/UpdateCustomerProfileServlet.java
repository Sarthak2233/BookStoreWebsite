package com.bookstore.controller.frontend.Customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.services.CustomerServices;

@WebServlet("/update_profile")
public class UpdateCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCustomerProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerServices customerServices=new CustomerServices(request, response);
		customerServices.updateCustomerProfile();
	}

}
