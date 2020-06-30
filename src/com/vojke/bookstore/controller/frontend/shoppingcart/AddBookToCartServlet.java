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
 * Servlet implementation class AddBookToCartServlet
 */
@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddBookToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer bookId=Integer.parseInt(request.getParameter("book_id"));
		Object cartObject=request.getSession().getAttribute("cart");
		ShoppingCart shoppingCart=null;
		if(cartObject !=null && cartObject instanceof ShoppingCart) {
			 shoppingCart=(ShoppingCart) cartObject;
		}else {
			shoppingCart=new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		BookDAO bookDAO=new BookDAO();
		Book book=bookDAO.get(bookId);
		
		shoppingCart.addItem(book);
		String cartPage=request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	} 

}
