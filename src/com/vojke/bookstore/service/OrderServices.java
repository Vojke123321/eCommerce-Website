package com.vojke.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vojke.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.vojke.bookstore.dao.OrderDAO;
import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.BookOrder;
import com.vojke.bookstore.entity.Customer;
import com.vojke.bookstore.entity.OrderDetail;

public class OrderServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDAO orderDAO;
	
	
	public OrderServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		orderDAO=new OrderDAO();
	}


	public void listallOrder() throws ServletException, IOException {
		List<BookOrder> listOrder = orderDAO.listAll();
		request.setAttribute("listOrder",listOrder);
		String listPage = "order_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(listPage);
		Dispatcher.forward(request, response);
	}


	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("id"));
		
		BookOrder order = orderDAO.get(orderId);
		request.setAttribute("order",order);
		
		String detailPAge="order_detail.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(detailPAge);
		Dispatcher.forward(request, response);
	}


	public void showCheckOutForm() throws ServletException, IOException {
		String checkOutPage="frontend/checkout.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(checkOutPage);
		Dispatcher.forward(request, response);
	}


	public void placeOrder() throws ServletException, IOException {
		String recipientName=request.getParameter("recipientName");
		String recipientPhone=request.getParameter("recipientPhone");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String zipCode=request.getParameter("zipCode");
		String country=request.getParameter("country");
		String paymentMethod=request.getParameter("paymentMethod");
		String shippingAddress=address+", "+city+", "+zipCode+", "+country;
		BookOrder order=new BookOrder();
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		order.setCustomer(customer);
		
		ShoppingCart shoppingCart=(ShoppingCart) request.getSession().getAttribute("cart");
		Map<Book,Integer> items=shoppingCart.getItems();
		
		Iterator<Book> iterator=items.keySet().iterator();
		
		Set<OrderDetail> orderDetails=new HashSet<>();
		while(iterator.hasNext()) {
			Book book=iterator.next();
			Integer quantity=items.get(book);
			float subtotal=quantity * book.getPrice();
			
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			
			orderDetails.add(orderDetail);
			
		}
		order.setOrderDetailses(orderDetails);
		order.setTotal(shoppingCart.getTotalAmount());
		orderDAO.create(order);
		
		shoppingCart.clear();
		
		String message="Thank you.Your order has been received.We will deliver your books within a few days.";
		request.setAttribute("message",message);
		RequestDispatcher Dispatcher = request.getRequestDispatcher("frontend/message.jsp");
		Dispatcher.forward(request, response);
	}


	public void listOrderByCustomer() throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		List<BookOrder> listOrders = orderDAO.listByCustomer(customer.getCustomerId());
		request.setAttribute("listOrders",listOrders);
		String historyPage="frontend/order_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(historyPage);
		Dispatcher.forward(request, response);
		
	}


	public void showOrderDetailForCustomer() throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("id"));
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		int customer_id=customer.getCustomerId();
		
		BookOrder order = orderDAO.get(orderId,customer_id);
		request.setAttribute("order",order);
		
		String detailPAge="frontend/order_detail.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(detailPAge);
		Dispatcher.forward(request, response);
	}


	public void showEditORderForm() throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("id"));
		HttpSession session=request.getSession();
		Object isPendingBook = session.getAttribute("newBookPendingToaddtoOrder");
		if(isPendingBook == null) {
			BookOrder order=orderDAO.get(orderId);
		session.setAttribute("order",order);
		}else {
			session.removeAttribute("newBookPendingToaddtoOrder");
		}
		String editPage="order_form.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(editPage);
		Dispatcher.forward(request, response);
		
	}


	public void updateOrder() throws ServletException, IOException {
		HttpSession session=request.getSession();
		BookOrder order=(BookOrder) session.getAttribute("order");
		String recipientName=request.getParameter("recipientName");
		String recipientPhone=request.getParameter("recipientPhone");
		String shippingAddress=request.getParameter("shippingAddress");
		String paymentMethod=request.getParameter("paymentMethod");
		String orderStatus=request.getParameter("orderStatus");
		
		order.setRecipientName(recipientName);
		order.setRecipientPhone(recipientPhone);
		order.setShippingAddress(shippingAddress);
		order.setPaymentMethod(paymentMethod);
		order.setStatus(orderStatus);
		
		String[] arrayBookID=request.getParameterValues("bookId");
		String[] arrayprice=request.getParameterValues("price");
		String[] arrayQuantity=new String[arrayBookID.length];
		
		for (int i = 1; i <=arrayQuantity.length; i++) {
			arrayQuantity[i-1]=request.getParameter("quantity"+i);
		}
		Set<OrderDetail> orderDetails = order.getOrderDetailses();
		orderDetails.clear();
		float totalAmount=0.0f;
		for (int i = 0; i <arrayBookID.length; i++) {
			int bookid=Integer.parseInt(arrayBookID[i]);
			int quantity=Integer.parseInt(arrayQuantity[i]);
			float price=Float.parseFloat(arrayprice[i]);
			
			float subtotal=price * quantity;
			
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setBook(new Book(bookid));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBookOrder(order);
			
			orderDetails.add(orderDetail);
			totalAmount+=subtotal;
		}
		order.setTotal(totalAmount);
		
		orderDAO.update(order);
		
		request.setAttribute("message","The order "+order.getOrderId()+" has been updated successfully");
		listallOrder();
		
	}


	public void deleteOrder() throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("id"));
		orderDAO.delete(orderId);
		request.setAttribute("message","The order "+orderId+" has been deleted. :(");
		listallOrder();
	}
	
	
	
	
	
}
