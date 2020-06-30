package com.vojke.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.dao.BookDAO;
import com.vojke.bookstore.entity.Book;

/**
 * Servlet implementation class RemoveBookFromCartServlet
 */
@WebServlet("/remove_from_cart")
public class RemoveBookFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveBookFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("book_id"));
		Object cartObject=request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart=(ShoppingCart) cartObject;
		
		shoppingCart.removeItem(new Book(bookId));
	
		
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}


}
