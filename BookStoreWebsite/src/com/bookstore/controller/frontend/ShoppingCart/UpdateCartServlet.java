package com.bookstore.controller.frontend.ShoppingCart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UpdateCartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] arrayBookIds=request.getParameterValues("bookId");
		String[] arrayQuantities=new String[arrayBookIds.length];
		
		for(int i=1;i<= arrayQuantities.length; i++) {
			String aQuantities=request.getParameter("quantity"+i);
			arrayQuantities[i-1]=aQuantities;
		}
		
		int[] bookIds=Arrays.stream(arrayBookIds).mapToInt(Integer::parseInt).toArray();//Lambda expression
		int[] quantities=Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
		
		ShoppingCart shoppingCart=(ShoppingCart) request.getSession().getAttribute("cart");
		shoppingCart.updateCart(bookIds, quantities);
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
