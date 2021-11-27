package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * @param reviewDao
	 * @param request
	 * @param response
	 */
	public ReviewServices( HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.reviewDao=new ReviewDAO();
	}
	
	public void listAllReview() throws ServletException, IOException{
		listAllReview(null);
	}
	
	public void listAllReview(String message) 
			throws ServletException, IOException {
		List<Review> listReview=reviewDao.listAll();
		
		if(message!=null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listReview", listReview);
		
		String listPage="review_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request,response);
	}

	public void editReview() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer reviewId= Integer.parseInt(request.getParameter("id"));
		Review review=reviewDao.get(reviewId);
		if(review==null) {
			String message="Couldn't find the review having reviewId"+reviewId;
			request.setAttribute("message",message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
		request.setAttribute("review",review);
		String editPage="review_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
		}
	}

	public void updateReview() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer reviewId=Integer.parseInt(request.getParameter("reviewId"));
		String headLine=request.getParameter("headline");
		String comment=request.getParameter("comment");
		
		Review review=reviewDao.get(reviewId);
		review.setHeadline(headLine);
		review.setComment(comment);
		
		reviewDao.update(review);
		String message="The review has been updated Successfully.";
		listAllReview(message);
		
	}

	public void deleteReview() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer reviewId=Integer.parseInt("id");
		Review review=reviewDao.get(reviewId);
		
		if(review==null) {
			String message="Couldn't delete the review having reviewId"+reviewId+"because it has already been deleted by anothe user.";
			request.setAttribute("message",message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			reviewDao.delete(reviewId);
			String message="Review has been deleted successfully";
			listAllReview(message);
		}
	}

	public void showReviewForm() throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("frontend/reveiw_form.jsp");
		requestDispatcher.forward(request, response);
	}
}
