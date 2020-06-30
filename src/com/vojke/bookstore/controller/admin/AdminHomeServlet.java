package com.vojke.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.dao.BookDAO;
import com.vojke.bookstore.dao.CustomerDAO;
import com.vojke.bookstore.dao.OrderDAO;
import com.vojke.bookstore.dao.ReviewDAO;
import com.vojke.bookstore.dao.UserDAO;
import com.vojke.bookstore.entity.BookOrder;
import com.vojke.bookstore.entity.Review;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerDAO customerDAO=new CustomerDAO();
		BookDAO bookDAO=new BookDAO();
		UserDAO userDao=new UserDAO(); 
		ReviewDAO reviewDAO=new ReviewDAO();
		OrderDAO orderDAO=new OrderDAO();
		List<BookOrder> listMostRecentSales = orderDAO.listMostRecentSales();
		List<Review> mostRecentReviews = reviewDAO.listMostRecent();
		
		
		long totalUsers=userDao.count();
		long totalBooks=bookDAO.count();
		long totalCustomers=customerDAO.count(); 
		long totalReviews=reviewDAO.count();
		long totalORders=orderDAO.count();
		
		
		request.setAttribute("listMostRecentSales", listMostRecentSales);
		request.setAttribute("mostRecentReviews",mostRecentReviews);
		request.setAttribute("totalUsers",totalUsers);
		request.setAttribute("totalBooks",totalBooks);
		request.setAttribute("totalReviews",totalReviews);
		request.setAttribute("totalORders",totalORders);
		request.setAttribute("totalCustomers",totalCustomers);
		
		
		String page = "index.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(page);
		Dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
