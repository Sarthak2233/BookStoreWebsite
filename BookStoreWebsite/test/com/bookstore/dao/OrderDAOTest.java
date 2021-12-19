package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {
	private static OrderDAO orderDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDao= new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDao.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order=new BookOrder();
		Customer customer=new Customer();
		customer.setCustomerId(19);
		
		order.setCustomer(customer);
		order.setRecipientName("Sarthak Basnet");
		order.setRecipientPhone("9847386619");
		order.setStatus("Processing");
		order.setShippingAddress("Kathmandu, NewRoad");
		
		Set<OrderDetail> orderDetails=new HashSet<>(0);
		OrderDetail orderDetail=new OrderDetail();
		Book book=new Book(31);
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(400);
		orderDetail.setBookOrder(order);
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		
		BookOrder savedOrder=orderDao.create(order);
		assertNotNull(savedOrder);
	
	}

	@Test
	public void testUpdateBookOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
