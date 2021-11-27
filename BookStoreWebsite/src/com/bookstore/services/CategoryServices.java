/**
 * 
 */
package com.bookstore.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;
import com.bookstore.entity.Users;


/**
 * @author Sarthak
 *
 */
public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	/**
	 * @param request
	 * @param response
	 * @param categoryDAO 
	 */
	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;;
		categoryDAO = new CategoryDAO();
	}
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
		}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory= categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		if(message!= null) {
			request.setAttribute("message",message);
			}
		String listpage="category_list.jsp";
		RequestDispatcher requestDispatcher=request.getRequestDispatcher(listpage);
		
		requestDispatcher.forward(request, response);
	}
	public void createCategory() throws ServletException, IOException{
		String name=request.getParameter("name");
		Category existCategory=categoryDAO.findByName(name);
		if(existCategory!=null){
			String message="Category "+ name +" already exists.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request,response);
		}else 
		{
		Category newCategory=new Category(name);
		categoryDAO.create(newCategory);
		listCategory("Category created succesfully.");

		}
	}
	
	public void editCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		
		Category categoryById=categoryDAO.get(categoryId);
		Category category=categoryDAO.get(categoryId);
		
		if(categoryById==null) {
			String message="Couldn't find category haing CategoryID  "+  categoryId;
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else 
		{		
		System.out.println(category.getName());
		String edit_page="category_form.jsp";
		request.setAttribute("category", category);
		RequestDispatcher dispatcher=request.getRequestDispatcher(edit_page);
		dispatcher.forward(request,response);
		}
	}

	public void updateCategory() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		String name=request.getParameter("name");
		
		Category categoryById=categoryDAO.get(categoryId);
		Category categoryByName=categoryDAO.findByName(name);
		
		if(categoryByName!=null && categoryById.getCategoryId()!=categoryByName.getCategoryId()) {
			String message="Couldn't update category. "+"Category having name "+name+" and CategoryId  "+categoryId+"  already exists.";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		}else {
			Category category=new Category(categoryId,name);
			categoryDAO.update(category);
			
			String message="Category has been updated successfully";
			listCategory(message);	
		}
		
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("id"));
		Category categoryById=categoryDAO.get(categoryId);
		BookDAO bookDAO=new BookDAO();
		long numberofBook=bookDAO.countByCategory(categoryId);
		
		if(categoryById==null) {
			String message="Couldn't delete category. It has already been deleted";
			request.setAttribute("message", message);
			
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
			}else if(numberofBook>0){
					String message="Couldn't delete category "+ categoryId+"  because it consists of books";
					request.setAttribute("message", message);
					RequestDispatcher requestDispatcher= request.getRequestDispatcher("message.jsp");
					requestDispatcher.forward(request, response);
			}else{
				categoryDAO.delete(categoryId);
				String message="Category having categoryId  "+categoryId+ "has been deleted successfully";
				listCategory(message);
				}
		
	}
}
