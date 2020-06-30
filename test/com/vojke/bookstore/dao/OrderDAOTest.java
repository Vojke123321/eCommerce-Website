package com.vojke.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.BookOrder;
import com.vojke.bookstore.entity.Customer;
import com.vojke.bookstore.entity.OrderDetail;
import com.vojke.bookstore.entity.OrderDetailId;

public class OrderDAOTest {
	private static OrderDAO orderDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO=new OrderDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order=new BookOrder();
		Customer customer=new Customer();
		customer.setCustomerId(14);
		
		order.setCustomer(customer);
		order.setRecipientName("Nam Ha Minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("hajduk petra 91");
		
		Set<OrderDetail> orderDetails=new HashSet<OrderDetail>();
		OrderDetail orderDetail=new OrderDetail();
		
		Book book=new Book(11);
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(60.5f);
		orderDetail.setBookOrder(order);
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetailses(orderDetails);
		
		
		orderDAO.create(order);
		assertTrue(order.getOrderId()>0);
		
		
	}
	@Test
	public void testCreateBookOrder2() {
		BookOrder order=new BookOrder();
		Customer customer=new Customer();
		customer.setCustomerId(9);
		
		order.setCustomer(customer);
		order.setRecipientName("Nam Ha Minh");
		order.setRecipientPhone("123456789");
		order.setShippingAddress("hajduk petra 91");
		
		Set<OrderDetail> orderDetails=new HashSet<OrderDetail>();
		OrderDetail orderDetail1=new OrderDetail();
		OrderDetail orderDetail2=new OrderDetail();
		
		Book book1=new Book(10);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(60.5f);
		orderDetail1.setBookOrder(order);
		orderDetails.add(orderDetail1);
		
		
		Book book2=new Book(6);
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubtotal(40f);
		orderDetail2.setBookOrder(order);
		
		orderDetails.add(orderDetail2);
		
		order.setOrderDetailses(orderDetails);
		
		
		orderDAO.create(order);
		assertTrue(order.getOrderId()>0 && order.getOrderDetailses().size()==2);
		
		
	}

	@Test
	public void testGet() {
		Integer orderId=9;
		BookOrder orderFromDB = orderDAO.get(orderId);
		assertEquals(1, orderFromDB.getOrderDetailses().size());
	}
	@Test
	public void testUpdateBookOrderShippingAdrdress() {
			Integer orderId=9;
			BookOrder order = orderDAO.get(orderId);
			order.setShippingAddress("New Shipping Address");
			orderDAO.update(order);
			
			BookOrder updateOrder=orderDAO.get(orderId);
			
			assertEquals(order.getShippingAddress(), updateOrder.getShippingAddress());
			
	}
	
	@Test
	public void testUpdateBookOrderDetail() {
			Integer orderId=9;
			BookOrder order = orderDAO.get(orderId);
			Iterator<OrderDetail> iterator=order.getOrderDetailses().iterator();	
			
			while(iterator.hasNext()) {
				OrderDetail orderDEtail=iterator.next();
				if(orderDEtail.getBook().getBookId()==2) {
					orderDEtail.setQuantity(3);
					orderDEtail.setSubtotal(180.15f);
					
				}
			}
			orderDAO.update(order);
			
			 iterator=order.getOrderDetailses().iterator();	
			 int exceptedQuantity=3;
			 float exctedSubtotal=180.15f;
			 int actualQauntity=0;
			 float actualSubtotal=0;
			while(iterator.hasNext()) {
				OrderDetail orderDEtail=iterator.next();
				if(orderDEtail.getBook().getBookId()==2) {
					actualQauntity=orderDEtail.getQuantity();
					actualSubtotal=orderDEtail.getSubtotal();
					
				}
			}
			
			
			
			assertEquals(exceptedQuantity,actualQauntity);
			assertEquals(exctedSubtotal,actualSubtotal,0.0f);
	}

	@Test
	public void testDeleteOrder() {
		int orderId=9;
		orderDAO.delete(orderId);
		
		BookOrder order = orderDAO.get(orderId);
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> result = orderDAO.listAll();
		
		for (BookOrder bookOrder : result) {
			System.out.println(bookOrder.getRecipientName());
		}
		
		assertTrue(result.size()>0);
	}

	@Test
	public void testCount() {
		long total_orders = orderDAO.count();
		
		assertEquals(2,total_orders);
	}
	@Test
	public void testListByCustomerNoOrders() {
		Integer customer_id=999;
		List<BookOrder> result = orderDAO.listByCustomer(customer_id);
		assertTrue(result.isEmpty());
	}
	@Test
	public void testListByCustomerHasSomeOrders() {
		Integer customer_id=9;
		List<BookOrder> result = orderDAO.listByCustomer(customer_id);
		assertTrue(result.size()>0);
	}
	@Test
	public void testGetByIdandCustomerNull() {
		Integer customer_id=90;
		Integer order_id=100;
		
		BookOrder bookOrder = orderDAO.get(order_id, customer_id);
		assertNull(bookOrder);
	}
	@Test
	public void testGetByIdandCustomer() {
		Integer customer_id=9;
		Integer order_id=10;
		
		BookOrder bookOrder = orderDAO.get(order_id, customer_id);
		assertNotNull(bookOrder);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<BookOrder> recentOrders=orderDAO.listMostRecentSales();
		
		assertEquals(3,recentOrders.size());
	}
}
