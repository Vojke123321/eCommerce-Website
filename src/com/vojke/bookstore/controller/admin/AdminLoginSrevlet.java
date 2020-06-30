package com.vojke.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.service.UserServices;

@WebServlet("/admin/login")
public class AdminLoginSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLoginSrevlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserServices userServices=new UserServices(request, response);
		userServices.login();
		
	}

}
