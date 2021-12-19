package com.bookstore.controller.frontend.ShoppingCart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddBookToCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("book_id"));
		
		Object cartObj=request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart=null;
		
		if(cartObj!=null && cartObj instanceof ShoppingCart) {
			shoppingCart= (ShoppingCart) cartObj;
		}else {
			shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		BookDAO<?> bookDao=new BookDAO<Object>();
		Book book=bookDao.get(bookId);
		
		shoppingCart.addItem(book);
		
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
		
	
	}

}
