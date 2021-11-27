package com.bookstore.entity;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BookRatingTest {


	@Test
	public void testAverageRating() {
		Book book=new Book();
		
		Set<Review> reviews=new HashSet<>();
		Review review1=new Review();
		review1.setRating(5);
		reviews.add(review1);
		
		
		Review review2=new Review();
		review2.setRating(3);
		reviews.add(review2);
		
		
		Review review3=new Review();
		review3.setRating(1);
		reviews.add(review3);
		
		book.setReviews(reviews);
		
		
		float averageRating=book.getAverageRating();
		System.out.println("The average Rating is: "+averageRating);
		assertEquals(3,averageRating,0.0);
	}
	
	@Test
	public void testGetRatingString() {
		float averageRating=2.5f;
		Book book=new Book();
		String actual=book.getRatingString(averageRating);
		String expected="on,on,half,off,off";
		
		assertEquals(expected,actual);
	}

}
