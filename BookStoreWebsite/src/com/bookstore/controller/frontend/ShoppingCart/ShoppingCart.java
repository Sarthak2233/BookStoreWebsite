package com.bookstore.controller.frontend.ShoppingCart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {

	public ShoppingCart() {
	}
	
	private Map<Book,Integer> cart=new HashMap<>();
	
	public void addItem(Book book) {
		if(cart.containsKey(book)) {
			Integer quantity=cart.get(book)+1;
			cart.put(book, quantity);
		}else {
			cart.put(book, 1);
		}
	}
	
	public void updateCart(int[] bookids,int[] quantities) {
		for(int i=0; i<bookids.length;i++) {
			Book key=new Book(bookids[i]);
			Integer value=quantities[i];
			cart.put(key, value);
		}
	}
	
	public void removeItems(Book book) {
		cart.remove(book);
	}
	
	public int getTotalQuantity() {
		int total=0;
		Iterator<Book> iterator=cart.keySet().iterator();
		while(iterator.hasNext()) {
			Book next=iterator.next();
			int quantity=cart.get(next);
			total+=quantity;
		}
		return total;
	}
	
	public double getTotalAmount(){
		double total=0.0f;
		Iterator<Book> iterator=cart.keySet().iterator();
		while(iterator.hasNext()) {
			Book book=iterator.next();
			int quantity=cart.get(book);
			double subtotal=quantity*book.getPrice();
			total+=subtotal;
		}
		return total;
	}
	
	public void clear() {
		cart.clear();
	}
	
	public int getTotalItems() {
		return cart.size();
	}
	public Map<Book,Integer> getItems(){
		return this.cart;
	}

}
