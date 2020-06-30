package com.vojke.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.dao.BookDAO;
import com.vojke.bookstore.dao.CategoryDAO;
import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.Category;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bookDAO=new BookDAO();
		List<Book> listNewBooks = bookDAO.listnewBooks();
		List<Book> listBestSellingBooks = bookDAO.listBestSelilingBooks();
		List<Book> ListFavoredBooks = bookDAO.listMostFavoredBooks();
		
		request.setAttribute("listNewBooks",listNewBooks);
		request.setAttribute("listBestSellingBooks", listBestSellingBooks);
		request.setAttribute("ListFavoredBooks",ListFavoredBooks);
		
		String page="frontend/index.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(page);
		Dispatcher.forward(request, response);
	
	}

	

}
