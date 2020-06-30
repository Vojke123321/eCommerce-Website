package com.vojke.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.BookOrder;
import com.vojke.bookstore.entity.Customer;
import com.vojke.bookstore.entity.OrderDetail;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {
	
	@Override
	public BookOrder create(BookOrder order) {
		order.setOrderDate(new Date());
		order.setStatus("Processing");
		return super.create(order);
	}

	@Override
	public BookOrder get(Object orderid) {
		return super.find(BookOrder.class, orderid);
	}
	
	public BookOrder get(Integer orderid,Integer customerID) {
		Map<String, Object> parameterss=new HashMap<>();
		parameterss.put("orderId", orderid);
		parameterss.put("customerId", customerID);
		List<BookOrder> result = super.findWithNamedQueryAndParamater("BookOrder.findByIdAndCustomer", parameterss);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	@Override
	public BookOrder update(BookOrder order) {
		// TODO Auto-generated method stub
		return super.update(order);
	}

	@Override
	public void delete(Object id) {
		super.delete(BookOrder.class,id);
	}

	@Override
	public List<BookOrder> listAll() {
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("BookOrder.countAll");
	}
	
	public List<BookOrder> listByCustomer(Integer customer_id){
		return super.findWithNamedQueryAndParamater("BookOrder.findByCustomer","customerId",customer_id);
	}
	
	public List<BookOrder> listMostRecentSales(){
		return super.findWithNamedQuery("BookOrder.findAll", 0, 3);
	}

}
