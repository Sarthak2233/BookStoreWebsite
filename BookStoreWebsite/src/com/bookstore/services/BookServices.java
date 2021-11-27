package com.bookstore.services;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private EntityManager entityManager;
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * @param entityManager
	 * @param bookDAO
	 * @param request
	 * @param response
	 */
	public BookServices(HttpServletRequest request,HttpServletResponse response) {
		super();
		this.entityManager = entityManager;
		this.bookDAO = bookDAO;
		this.request = request;
		this.response = response;
		bookDAO=new BookDAO();
		categoryDAO=new CategoryDAO();
	}
	public void listBooks() throws ServletException, IOException {
		// TODO Auto-generated method stub
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> listBooks=bookDAO.listAll();
		request.setAttribute("listBooks", listBooks);
		if(message!= null) {
			request.setAttribute("message",message);
			}
		String listPage="book_list.jsp";
		RequestDispatcher requestDispatcher= request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	public void showBooknewForm() throws ServletException, IOException {
		List<Category> listCategory= categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String newPage="book_form.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}
	
	public void readBookFields(Book book) throws ServletException, IOException {
		Integer categoryId= Integer.parseInt(request.getParameter("category"));
		String title= request.getParameter("title");
		String author=request.getParameter("author");
		String isbn=request.getParameter("isbn");
		DateFormat dateFormat= new SimpleDateFormat("MM/dd/yy");
		Date publishDate=null;
		
		try {
		 publishDate=dateFormat.parse(request.getParameter("publishDate"));
		}catch (ParseException ex) {
			ex.printStackTrace();
			throw new ServletException("Error parsing publishDate(fomat is mm/dd/yy)");
		}
		String description=request.getParameter("description");
		float price= Float.parseFloat(request.getParameter("price"));
		
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setDescription(description);
		book.setPublishDate(publishDate);
		Category category=categoryDAO.get(categoryId);
		book.setCategory(category);
		book.setPrice(price);
		
		Part part= request.getPart("bookImage"); // for loading image into database//
		if (part!= null &&  part.getSize()>0) {
			long size= part.getSize();
			byte[] imageBytes=new byte[(int) size];
			
			InputStream inputStream= part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			book.setImage(imageBytes);
		}
	}

	public void createBook() throws ServletException, IOException {
		String title= request.getParameter("title");
		Book existBook= bookDAO.findByTitle(title);
		if(existBook!=null) {
			String message="Book "+ title +" already exists.";
			listBooks(message);
			return ;
		}
        
		Book newBook=new Book();
		readBookFields(newBook);
		Book createdBook=bookDAO.create(newBook);
		if(createdBook.getBookId()>0) {
			String message="A new book created successfully";
			listBooks(message);
		}
	}
	public void editBook() throws ServletException, IOException {
	Integer bookId=Integer.parseInt(request.getParameter("id"));
	String title=request.getParameter("title");
	List<Category> listCategory= categoryDAO.listAll();
	
	Book book=bookDAO.get(bookId);
	if(book==null) {
		String message="Coundn't find book having title" +title+ ".";
		request.setAttribute("message", message);
		
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
		requestDispatcher.forward(request, response);
	}else
	{
	String editPage="book_form.jsp";
	request.setAttribute("listCategory", listCategory);
	request.setAttribute("book", book);
	RequestDispatcher requestDispatcher=request.getRequestDispatcher(editPage);
	requestDispatcher.forward(request, response);
	 	}
	}
	
	public void updateBook() throws ServletException, IOException {
	Integer bookId=Integer.parseInt(request.getParameter("bookId"));
	String title=request.getParameter("title");
	
	Book existBook=bookDAO.get(bookId);
	Book bookFindByTitle=bookDAO.findByTitle(title);
	
		if(bookFindByTitle!=null && !existBook.equals(bookFindByTitle)){
		String message="Couldn't update book because book having title "+ title +" already exists";
		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
		requestDispatcher.forward(request, response);
		}else {
		readBookFields(existBook);
		bookDAO.update(existBook);
		String message="Books has been updated successfully";
		listBooks(message);
		}
	}
	public void deleteBook() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId=Integer.parseInt(request.getParameter("id"));
		Book bookById=bookDAO.get(bookId);
		
		if(bookById==null) {
			String message="The book that you are looking for has already been deleted";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else
		{
		if(bookById.getReviews().isEmpty()) {	
		bookDAO.delete(bookId);
		String message="Book havind BookId "+bookId+" deleted sucesfully";
		listBooks(message);
		}
		else {
			String message="Could't delete the books becauest it contains reviews.";
			request.setAttribute("message",message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}
		}
	}
	public void listBooksByCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Category category=categoryDAO.get(categoryId);
				if (category == null) {
					String message = "Sorry, the category ID " + categoryId + " is not available.";
					request.setAttribute("message", message);
					request.getRequestDispatcher("frontend/message.jsp").forward(request, response);
					return;
				}else {
				List<Book> listBooks=bookDAO.listByCategory(categoryId);
				request.setAttribute("category",category);
				request.setAttribute("listBooks", listBooks);
				String listPage="frontend/books_byCategory.jsp";
				RequestDispatcher requestDispatcher=request.getRequestDispatcher(listPage);
				requestDispatcher.forward(request, response);
		}
	}
	public void viewBookDetails() throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("id"));
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Book book=bookDAO.get(bookId);
		if(book==null) {
			String message="The book that you are looking for has already been deleted";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
		List<Book> listBooks=bookDAO.listByCategory(categoryId);
		request.setAttribute("book", book);
		request.setAttribute("listBooks", listBooks);
		
		String destPage="frontend/viewBookDetails_byCategory.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(destPage);
		requestDispatcher.forward(request, response);
		}
		
	}
	public void search() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword=request.getParameter("keyword");
		List<Book> result=null;
		if(keyword.equals("")) {
			result=bookDAO.listAll();
		}else {
			result=bookDAO.Search(keyword);
		}
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		String resultPage="frontend/search_result.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
	}

}
