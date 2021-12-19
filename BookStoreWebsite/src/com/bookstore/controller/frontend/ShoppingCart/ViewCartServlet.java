package com.bookstore.controller.frontend.ShoppingCart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

/**
 * Servlet implementation class ViewCartServlet
 */
@WebServlet("/view_cart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ViewCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object cartObject=request.getSession().getAttribute("cart");
		
		if(cartObject==null) {
			ShoppingCart shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		
		String page="frontend/shopping_cart.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
