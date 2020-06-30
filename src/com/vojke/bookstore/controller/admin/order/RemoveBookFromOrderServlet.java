package com.vojke.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vojke.bookstore.entity.BookOrder;
import com.vojke.bookstore.entity.OrderDetail;

/**
 * Servlet implementation class RemoveBookFromOrderServlet
 */
@WebServlet("/admin/remove_book_from_order")
public class RemoveBookFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveBookFromOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		BookOrder order=(BookOrder) session.getAttribute("order");
		
		Set<OrderDetail> orderDetails = order.getOrderDetailses();
		Iterator<OrderDetail> iterator=orderDetails.iterator();
		
		while(iterator.hasNext()) {
			OrderDetail orderDetail=iterator.next();
			if(orderDetail.getBook().getBookId()==bookId) {
				float newTotal=order.getTotal()-orderDetail.getSubtotal();
				order.setTotal(newTotal);
				iterator.remove();
			}
		}
		String editORderFormPage="order_form.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(editORderFormPage);
		Dispatcher.forward(request, response);
	}

}
