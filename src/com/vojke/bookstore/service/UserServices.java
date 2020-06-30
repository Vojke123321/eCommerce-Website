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

import com.vojke.bookstore.dao.UserDAO;
import com.vojke.bookstore.entity.Users;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		userDAO = new UserDAO();
		this.request=request;
		this.response=response;
	}

	public void listUser()
			throws ServletException, IOException {
		List<Users> users_list = userDAO.listAll();
		request.setAttribute("list_of_users", users_list);
		String page = "user_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(page);
		Dispatcher.forward(request, response);

	}

	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String full_name = request.getParameter("fullname");
		String passowrd = request.getParameter("password");
		
		Users existUser = userDAO.findbyEmail(email);
		if(existUser!=null) {
			request.setAttribute("message","Could not create user.A user with email '"+email+"' already exists");
			RequestDispatcher Dispatcher = request.getRequestDispatcher("message.jsp");
			Dispatcher.forward(request, response);
		}else {
		request.setAttribute("message","NEW USER CREATED SUCESSFULLY BRO!");
		Users user = new Users(email, passowrd, full_name);
		userDAO.create(user);
		listUser();
		}
	}

	public void editUser() throws ServletException, IOException {
		int user_id=Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(user_id);
		request.setAttribute("user",user);
		RequestDispatcher Dispatcher = request.getRequestDispatcher("user_form.jsp");
		Dispatcher.forward(request, response);
		
	}

	public void updateUser() throws ServletException, IOException {
		int user_id=Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String full_name = request.getParameter("fullname");
		String passowrd = request.getParameter("password");
		
		Users userById=userDAO.get(user_id);
		Users userByEmail=userDAO.findbyEmail(email);
		
		if(userByEmail !=null && userByEmail.getUserId() != userById.getUserId()) {
			request.setAttribute("message","COULD NOT UPDATE USER.user with email "+email+"already exists in database my friend");
			RequestDispatcher Dispatcher = request.getRequestDispatcher("message.jsp");
			Dispatcher.forward(request, response);
		}else {
		
		Users user=new Users(user_id,email,passowrd,full_name);
	
		userDAO.update(user);
		request.setAttribute("message","User has been updated successfully");
		listUser();
		}
		
	}

	public void deleteUser() throws ServletException, IOException {
		int user_id=Integer.parseInt(request.getParameter("id"));
		userDAO.delete(user_id);
		request.setAttribute("message","User with id "+user_id+"has been deleted successfully!");
		listUser();
		
	}
	public void login() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		boolean loginResult=userDAO.checkLogin(email, password);
		
		if(loginResult) {
			request.getSession().setAttribute("useremail",email);
			RequestDispatcher Dispatcher = request.getRequestDispatcher("/admin/");
			Dispatcher.forward(request, response);
		}else {
			request.setAttribute("message","Login FAILED! :(");
			RequestDispatcher Dispatcher = request.getRequestDispatcher("login.jsp");
			Dispatcher.forward(request, response);
		}
		
	}
}
