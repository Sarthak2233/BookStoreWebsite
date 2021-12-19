package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
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
		Integer bookId=Integer.parseInt(request.getParameter("book_id"));
		BookDAO<Object> bookDao=new BookDAO<Object>();
		Book book=bookDao.get(bookId);
		HttpSession session=request.getSession();
		
		session.setAttribute("book", book);
		
		Customer customer=(Customer) session.getAttribute("loggedCustomer");
		Review existReview=reviewDao.findByCustomerAndBook(customer.getCustomerId(), bookId);
		if(existReview!= null) {
			request.setAttribute("review", existReview);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("frontend/review_info.jsp");
			requestDispatcher.forward(request, response);
		}else {
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("frontend/review_form.jsp");
		requestDispatcher.forward(request, response);
		}
	}

	public void submitReview() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId=Integer.parseInt(request.getParameter("bookId"));
		Integer rating=(int)Integer.parseInt(request.getParameter("rating"));
		String headline=request.getParameter("headline");
		String comment=request.getParameter("comment");
		
		Review review=new Review();
		review.setHeadline(headline);
		review.setComment(comment);
		review.setRating(rating);
		
		Book book=new Book();
		book.setBookId(bookId);
		review.setBook(book);
		
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		review.setCustomer(customer);
		reviewDao.create(review);
		
		String messagePage="frontend/review_done.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}
}
