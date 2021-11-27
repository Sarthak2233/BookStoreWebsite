package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {
	
	@Override
	public Customer create(Customer customer) {
		// TODO Auto-generated method stub
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}

	@Override
	public Customer get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
		
	}
	
	public Customer findByEmail(String email) {
		List<Customer> result=super.findWithNamedQuery("Customer.findByEmail","email", email);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public Customer checklogin(String email,String password) {
		Map<String,Object> parameter=new HashMap<>();
		String encryptedPassword = HashGenerator.generateMD5(password);
		parameter.put("email", email);
		parameter.put("password", encryptedPassword);
		List<Customer> result=super.findWithNamedQuery("Customer.checkLogin", parameter);
		
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Customer.countAll");
	}



}
