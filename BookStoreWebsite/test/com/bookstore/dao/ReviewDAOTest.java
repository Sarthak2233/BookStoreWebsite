package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest {
	public static ReviewDAO reviewDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	reviewDao=new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review=new Review();
		Book book=new Book();
		book.setBookId(43);
		
		Customer customer=new Customer();
		customer.setCustomerId(19);
		
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline("This book is one of the awesome books that I have ever encountered.");
		review.setRating(5);
		review.setComment("I have just finished reading this book and i would like to say that anyone who is looking for"
				+ "mystery to read then this book is probably for you.");
		Review savedReview=reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId=15;
		Review review=reviewDao.get(reviewId);
		
		review.setHeadline("Awesome Book!!!");
		Review updatedReview=reviewDao.update(review);
		
		assertEquals(review.getHeadline(),updatedReview.getHeadline());
	}

	@Test
	public void testGet() {
		Integer reviewId=15;
		
		assertNotNull(reviewDao.get(reviewId));
	}

	@Test
	public void testDeleteObject() {
		int reviewId=2;
		reviewDao.delete(reviewId);
		
		Review review=reviewDao.get(reviewId);
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview= reviewDao.listAll();
		
		assertTrue(listReview.size()>0);
	}

	@Test
	public void testCount() {
	long totalCount=reviewDao.count();
	System.out.println("Total count  "+totalCount);
	assertTrue(totalCount>0);
	}

	@Test
	public void testFindByBookAndCustomer() {
		Integer customerId=19;
		Integer bookId=43;
		
		Review result=reviewDao.findByCustomerAndBook(customerId, bookId);
		assertNotNull(result);
	}
	
}
