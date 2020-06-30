package com.vojke.bookstore.controller.frontend.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class ShowRegisterCustomerFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowRegisterCustomerFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String registerForm = "frontend/customer_registration.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(registerForm);
		Dispatcher.forward(request, response);
	}

}
