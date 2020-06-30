package com.vojke.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.dao.BookDAO;
import com.vojke.bookstore.dao.CategoryDAO;
import com.vojke.bookstore.dao.UserDAO;
import com.vojke.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
	}

	public void listCategory() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		String page = "category_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(page);
		Dispatcher.forward(request, response);
	}

	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category existCategory = categoryDAO.findByName(name);
		if (existCategory != null) {
			request.setAttribute("message",
					"Could not create category." + "A category with name " + name + "already exists.");
			RequestDispatcher Dispatcher = request.getRequestDispatcher("message.jsp");
			Dispatcher.forward(request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			request.setAttribute("message", "NEW CATEGORY CREATED SUCESSFULLY BRO!");
			listCategory();
		}

	}

	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		request.setAttribute("category", category);
		RequestDispatcher Dispatcher = request.getRequestDispatcher("category_form.jsp");
		Dispatcher.forward(request, response);
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		Category categoryById = categoryDAO.get(categoryId);
		Category CategoryByName = categoryDAO.findByName(categoryName);

		if (CategoryByName != null && CategoryByName.getCategoryId() != categoryById.getCategoryId()) {
			request.setAttribute("message",
					"Could not update category." + "A category w ith name" + categoryName + " already EXISTS. :((");
			RequestDispatcher Dispatcher = request.getRequestDispatcher("message.jsp");
			Dispatcher.forward(request, response);
		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			request.setAttribute("message", "Category has been updated succesfully BRO!");
			listCategory();

		}

	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		BookDAO bookDAO=new BookDAO();
		
		long numberOfBooks=bookDAO.countByCategory(categoryId);
		if(numberOfBooks>0) {
			request.setAttribute("message", "Could not delete the category(ID:"+categoryId+") because it contains some books...");
		}else {
			categoryDAO.delete(categoryId);
			request.setAttribute("message", "Category with id: "+categoryId+ " has been REMOVED succesfully BRO!");
		}
		listCategory();
		
	}

}
