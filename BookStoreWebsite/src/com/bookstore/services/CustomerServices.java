package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;

public class CustomerServices {
	private CustomerDAO customerDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	/**
	 * @param customerDao
	 * @param request
	 * @param response
	 */
	public CustomerServices( HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.customerDao=new CustomerDAO();
	}
	
	public void listCustomers(String message) throws ServletException, IOException {
		// TODO Auto-generated method stub
		customerDao=new CustomerDAO();
		List<Customer> listCustomer=customerDao.listAll();
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomer",listCustomer);
		
		String listPage="customer_list.jsp";
		RequestDispatcher requestdispatcher= request.getRequestDispatcher(listPage);
		requestdispatcher.forward(request, response);
	}
	public void listCustomers() throws ServletException, IOException{
		listCustomers(null);
	}
	private void updateCustomerFieldsFromCustomer(Customer customer) {
		String email=request.getParameter("email");
		String fullname=request.getParameter("fullname");
		String address=request.getParameter("address");
		String password=request.getParameter("password");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		String zipCode=request.getParameter("zipCode");
		String phoneNumber=request.getParameter("phoneNumber");
		
		if(password !=null && !password.isEmpty())//These conditions are kept for updating the customer profile.
		{
			String encryptedPassword=HashGenerator.generateMD5(password);
			customer.setPassword(encryptedPassword);
		}
		
		if(email !=null && !email.equals("")) {
		customer.setEmail(email);
		}
		customer.setFullname(fullname);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setZipcode(zipCode);
		customer.setPhone(phoneNumber);
	}
	public void registerCustomer() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=request.getParameter("email");
		Customer existCustomer=customerDao.findByEmail(email);
		String message="";
		
		if(existCustomer != null) {
			 message="Could not register the customer. Customer having email "+email+" already exists.";
			request.setAttribute("message", message);
			String messagePage="frontend/message.jsp";
			RequestDispatcher requestdispatcher=request.getRequestDispatcher(messagePage);
			requestdispatcher.forward(request, response);
		}else {
			
			Customer newCustomer=new Customer();
			updateCustomerFieldsFromCustomer(newCustomer);
			customerDao.create(newCustomer);
			
			message="You have been registered succesfullly.<br/>"
					+ "<a href='login'>Click Here</a> to log in.";
			request.setAttribute("message", message);
			String messagePage="frontend/message.jsp";
			RequestDispatcher requestdispatcher=request.getRequestDispatcher(messagePage);
			requestdispatcher.forward(request, response);
			
		}
	}
	
	public void createCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer=customerDao.findByEmail(email);
		
		if(existCustomer != null) {
			String message="Could not create the customer. Customer having email "+email+" already exists.";
			request.setAttribute("message", message);
			String messagePage="message.jsp";
			RequestDispatcher requestdispatcher=request.getRequestDispatcher(messagePage);
			requestdispatcher.forward(request, response);
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFieldsFromCustomer(newCustomer);
			customerDao.create(newCustomer);
			String message="New Customer has been created succesfullly.";
			listCustomers(message);
		}
	}

	public void editCustomer() throws ServletException,IOException {
		// TODO Auto-generated method stub
		int customerId=Integer.parseInt(request.getParameter("id"));
		String fullname=request.getParameter("fulllname");
		
		Customer customerById=customerDao.get(customerId);
		Customer customer = customerDao.get(customerId);
		if(customerById==null) {
			String message="Couldn't find customer haing CustomerID  "+  customerId;
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			System.out.println(customer.getFullname());
			String editPage="customer_form.jsp";
			customer.setPassword(null);
			request.setAttribute("customer", customer);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
			requestDispatcher.forward(request,response);
		}
	}

	public void updateCustomer() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer customerId=Integer.parseInt(request.getParameter("customerId"));
		String email=request.getParameter("email");
		String message=null;
		Customer customerById=customerDao.get(customerId);
		Customer customerByEmail=customerDao.findByEmail(email);
		
		if(customerByEmail !=null && customerByEmail.getCustomerId()!=customerId) 
		{
			 message="Couldn't update the customer. "+"Customer having the Email "+email+" already exists.";
			request.setAttribute("message",message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else{
			String fullname=request.getParameter("fullname");
			String address=request.getParameter("address");
			String password=request.getParameter("password");
			String city=request.getParameter("city");
			String country=request.getParameter("country");
			String zipCode=request.getParameter("zipCode");
			String phoneNumber=request.getParameter("phoneNumber");
			
			Customer Customer=customerDao.get(customerId);
			Customer.setEmail(email);
			Customer.setFullname(fullname);
			Customer.setAddress(address);
			Customer.setPassword(password);
			Customer.setCity(city);
			Customer.setCountry(country);
			Customer.setZipcode(zipCode);
			Customer.setPhone(phoneNumber);
			
			customerDao.update(Customer);
			message="Customer has been edited successfully.";	
			}
			listCustomers(message);
	}

	public void deleteCustomer() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Customer customerById=customerDao.get(customerId);
		if(customerById==null) {
			String message="Couldn't delete the customer because the customer having customerId "+customerId+" has alreasy been"
					+ "deleted.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
		long count= new ReviewDAO().countBYCustomerId(customerId);
		if(count==0) {
		customerDao.delete(customerId);
		String message="Customer has been deleted succesfully";
		listCustomers(message);
		}else {
			String message="Couldn't delete the customer because the customer having customerId "+customerId+" has posted a review.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}
		}
	}

	public void showlogin() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path="frontend/login.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}

	public void dologin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		Customer customer=customerDao.checklogin(email, password);
		
		if(customer==null) {
			String message="Couldn't login. Plaese check your email and password.";
			request.setAttribute("message", message);
			showlogin();
		}else {
			HttpSession session=request.getSession();
			request.getSession().setAttribute("loggedCustomer", customer);
			Object objRequestedURL=session.getAttribute("redirectURL");
			if(objRequestedURL!=null) {
				String requestedURL=(String) objRequestedURL;
				session.removeAttribute(requestedURL);
				response.sendRedirect(requestedURL);
			}
			else {
			showCustomerProfile();
			}
		}
		
	}
	public void showCustomerProfile() throws ServletException, IOException {
		String profilePage="frontend/customer_profile.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(profilePage);
		requestDispatcher.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String editPage="frontend/edit_profile.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateCustomerProfile() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFieldsFromCustomer(customer);
		customerDao.update(customer);
		showCustomerProfile();
	}

	
}
