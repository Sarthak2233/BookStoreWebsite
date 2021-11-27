package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustimerDAOTest {
	private static CustomerDAO customerDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer newcustomer= new Customer();
		newcustomer.setFullname("Diwas Basnet");
		newcustomer.setEmail("diwasb@gmail.com");
		newcustomer.setAddress("Newroad,Tebahal");
		newcustomer.setCountry("Nepal");
		newcustomer.setCity("Kathmandu");
		newcustomer.setPassword("amorica");
		newcustomer.setPhone("9841414442");
		newcustomer.setZipcode("+977");
		
		Customer savedCustomer=customerDao.create(newcustomer);
		assertTrue(savedCustomer.getCustomerId()>0);
	}

	@Test
	public void testGet() {
		Integer customerId=15;
		Customer customer=customerDao.get(customerId);
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer customer=new Customer();
		customer=customerDao.get(15);
		customer.setFullname("Diwas Basnet.");
		customer=customerDao.update(customer);
		assertEquals("Diwas Basnet.",customer.getFullname());
	}

	@Test
	public void testDeleteObject() {
		Integer customerId=15;
		customerDao.delete(customerId);
		Customer customer=new Customer();
		customer=customerDao.get(customerId);
		assertNull(customer);
		
	}
	
	@Test
	public void testListAll() {
		List<Customer> list=customerDao.listAll();
		for(Customer c:list) {
			System.out.println(c.getFullname());
		}
		assertTrue(list.size()>0);
		}
	@Test
	public void testFindByEmail() {
		String email="diwasb@gmail.com";
		Customer customer=customerDao.findByEmail(email);
		assertNotNull(customer);
	}
	@Test
	public void count() {
		long total=customerDao.count();
		assertEquals(1,total);
	}
	
	@Test
	public void testChecklogin() {
		String email="mike21@gmail.com";
		String password="mike21";
		Customer customer=customerDao.checklogin(email, password);
		
		assertNotNull(customer);
		
	}
	
}
