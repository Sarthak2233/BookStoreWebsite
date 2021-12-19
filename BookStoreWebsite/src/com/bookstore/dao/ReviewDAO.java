package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	@Override
	public Review create(Review review) {
		// TODO Auto-generated method stub
		review.setReviewTime(new Date());
		return super.create(review);
	}

	@Override
	public Review update(Review updatedReview) {
		// TODO Auto-generated method stub
		return super.update(updatedReview);
	}

	@Override
	public Review get(Object reviewId) {
		// TODO Auto-generated method stub
		return super.find(Review.class, reviewId);
	}

	@Override
	public void delete(Object reviewId) {
		// TODO Auto-generated method stub
		 super.delete(Review.class, reviewId);
	}

	@Override
	public List listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Review.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Reveiw.countAll");
	}
	
	public long countBYBookId(int bookId) {
		return super.countWithNamedQuery("Review.countByBook","bookId",bookId);
		}
	
	public long countBYCustomerId(int customerId) {
		return super.countWithNamedQuery("Review.countByCustomer","customerId",customerId);
		}
	
	public Review findByCustomerAndBook(Integer customerId, Integer bookId) {
		Map<String,Object> parameter=new HashMap<String, Object>();
		parameter.put("customerId",customerId);
		parameter.put("bookId", bookId);
		List<Review> result=super.findWithNamedQuery("Review.findByCustomerANDBook",parameter);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
}
