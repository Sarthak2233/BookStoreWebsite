package com.bookstore.controller.frontend.ShoppingCart;

import static org.junit.Assert.*;

import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;

public class ShoppingCartTest {
	private static ShoppingCart cart;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 cart=new ShoppingCart();
		Book book =new Book();
		book.setBookId(32);
		cart.addItem(book);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAddItem() {
		Map<Book, Integer> items= cart.getItems();
		int quantity=items.get(new Book(32));
		
		assertEquals(1,quantity);
	}

	@Test
	public void testRemoveItems() {
		cart.removeItems(new Book(32));
		boolean quantity=cart.getItems().isEmpty();
		
		assertTrue(quantity);
	}
	
	@Test
	public void testRemoveItems1() {
		Book book2=new Book(2);
		cart.addItem(book2);
		
		cart.removeItems(new Book(2));
		Integer quantity=cart.getItems().get(book2);
		
		assertNull(quantity);
	}
	
	@Test
	public void testGetTotalQuantity() {
		assertEquals(1,cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount() {
		ShoppingCart cart1=new ShoppingCart();
		Book book2=new Book();
		book2.setPrice(20);
		cart1.addItem(book2);
		cart1.addItem(book2);
		assertEquals(40.0f,cart1.getTotalAmount(),40.0f);	
	}
	
	@Test
	public void testUpdateCart() {
		ShoppingCart cart=new ShoppingCart();
		
		Book book1=new Book(32);
		Book book2= new Book(39);
		
		cart.addItem(book1);
		cart.addItem(book2);
		
		int[] bookids= {32,39};
		int[] quantities= {3,4};
		
		cart.updateCart(bookids, quantities);
		assertEquals(7, cart.getTotalQuantity());
	}
	
	@Test
	public void testClear() {
		cart.clear();
		
		assertEquals(0,cart.getTotalQuantity());
	}
}
