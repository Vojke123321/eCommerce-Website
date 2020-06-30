package com.vojke.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vojke.bookstore.entity.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer> {

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}

	@Override
	public Customer update(Customer customer) {
		return super.update(customer);
	}

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}
	public Customer findByEmail(String email) {
		 List<Customer> result = super.findWithNamedQueryAndParamater("Customer.findByEmail","email",email);
		 if(!result.isEmpty()) {
			 return result.get(0);
		 }else {
			 return null;
		 }
			 
		 
	}
	
	public Customer checkLogin(String email,String password) {
		Map<String,Object> parameters=new HashMap<String, Object>();
		parameters.put("email", email);
		parameters.put("pass",password);
		List<Customer> result = super.findWithNamedQueryAndParamater("Customer.checkLogin", parameters);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	
	
	
	

}
