package com.vojke.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vojke.bookstore.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDAO=new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDAO.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer=new Customer();
		customer.setEmail("billy.jane@gmail.com");
		customer.setFullname("Jane Billy");
		customer.setCity("London");
		customer.setCountry("United States");
		customer.setAddress("15 Stojsin Petra");	
		customer.setPassword("neznaseprojs");
		customer.setPhoneNumber("0614150001");
		customer.setZipcode("100000");
		Customer savedCustomer= customerDAO.create(customer);
		System.out.println(savedCustomer.getCustomerId());
		assertTrue(savedCustomer.getCustomerId()>0);
	}

	@Test
	public void testGetCustomer() {
		Integer customerId=3;
		Customer customerFromDatabase = customerDAO.get(customerId);
		assertNotNull(customerFromDatabase);
	}
	@Test
	public void TestUpdateCustomer() {
		Integer customerId=3;
		Customer customer = customerDAO.get(customerId);
		customer.setFullname("Tom tom Tom");
		Customer updatedCustomer= customerDAO.update(customer);
		assertEquals("Tom tom Tom",updatedCustomer.getFullname());
	}

	@Test
	public void testDeleteCustomer() {
		Integer customerIdForDelete=3;
		customerDAO.delete(customerIdForDelete);
		Customer deletedCustomer = customerDAO.get(customerIdForDelete);
		assertNull(deletedCustomer);
	}
	@Test
	public void testListAllCustomers() {
		List<Customer> listCustomers = customerDAO.listAll();
		for (Customer customer : listCustomers) {
			System.out.println(customer.getFullname());
		}
		assertFalse(listCustomers.isEmpty());
	}
	@Test
	public void testCountCustomers() {
		long numberOfcustomers=customerDAO.count();
		assertTrue(numberOfcustomers>1);
	}
	@Test
	public void testFindByEmailSuccess() {
		String email="billy.jane@gmail.com";
		Customer customer = customerDAO.findByEmail(email);
		assertNotNull(customer);
		assertEquals("billy.jane@gmail.com",customer.getEmail());
	}
	@Test
	public void testCheckLoginSuccess() {
		String email="tom@gmail.com";
		String password="secret123";
		Customer customer = customerDAO.checkLogin(email, password);
		assertNotNull(customer);
	}
	@Test
	public void testCheckLoginFail() {
		String email="EMAILTHATNOTEXISTS@31gmail.com";
		String password="secret12asd3";
		Customer customer = customerDAO.checkLogin(email, password);
		assertNull(customer);
	}
}
