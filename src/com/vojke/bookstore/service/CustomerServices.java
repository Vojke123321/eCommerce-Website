package com.vojke.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vojke.bookstore.dao.CustomerDAO;
import com.vojke.bookstore.entity.Customer;

public class CustomerServices {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;
	
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		customerDAO=new CustomerDAO();
		
		
	}
	public void listCustomers() throws ServletException, IOException {
		List<Customer> customers = customerDAO.listAll();
		request.setAttribute("customers",customers);
		String listPage = "customer_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(listPage);
		Dispatcher.forward(request, response);
	}
	
	public void createCustomer() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		if(existCustomer !=null) {
			request.setAttribute("message","Could not create new customer because the email '" + email + "' already exists...");
			listCustomers();
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFiledsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			request.setAttribute("message","A new customer has Been created successfully :P..");
			listCustomers();
		}
	}
	public void editCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.get(customerId);
		request.setAttribute("customer",customer);
		String editPage = "customer_form.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(editPage);
		Dispatcher.forward(request, response);
	}
	public void updateCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("customerId"));
		String email=request.getParameter("email");
		Customer CustomerByEmail = customerDAO.findByEmail(email);
		
		if(CustomerByEmail!=null && CustomerByEmail.getCustomerId() != customerId) {
			request.setAttribute("message","Could not update the customer ID"+customerId+" because there is existing customer having the same email...");
		}else {
			//customerForUpdate <- ID FROM HIDDEN FORM,that's customer for update..
			Customer customerForUpdate=customerDAO.get(customerId);
			
			updateCustomerFiledsFromForm(customerForUpdate);
			customerDAO.update(customerForUpdate);
			
			
			request.setAttribute("message","A new customer has Been updated successfully :P..");
			
		}
		listCustomers();
		
	}
	public void deleteCustomer() throws ServletException, IOException {
		Integer customerId=Integer.parseInt(request.getParameter("id"));
		customerDAO.delete(customerId);
		request.setAttribute("message","A customer has Been deleted successfully :(.");
		listCustomers();
	}
	
	public void createCustomerForFrontEnd() throws ServletException, IOException {
		String email=request.getParameter("email");
		Customer existCustomer = customerDAO.findByEmail(email);
		if(existCustomer !=null) {
			request.setAttribute("message","Could not register.The email '" + email + "' is already registered by another customer...");
		}else {
			Customer newCustomer=new Customer();
			updateCustomerFiledsFromForm(newCustomer);
			customerDAO.create(newCustomer);
			
			request.setAttribute("message","You have registered successfully. Thank you :).<br/>"
					+ "<a href='login'>Click here</a> to login");
	}
		String messagePage = "frontend/message.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(messagePage);
		Dispatcher.forward(request, response);
	
	}
	
	private void updateCustomerFiledsFromForm(Customer customer) {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String fullname=request.getParameter("fullname");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		String phoneNumber=request.getParameter("phoneNumber");
		String zipcode=request.getParameter("zipcode");
		System.out.println("AAAAAAAAAAAAAAAA"+address);
		if(email!=null && !email.equals("")) {
		customer.setEmail(email);
		}
		customer.setFullname(fullname);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setAddress(address);	
		if(password!=null && !password.equals("")) {
		customer.setPassword(password);
		}
		customer.setPhoneNumber(phoneNumber);
		customer.setZipcode(zipcode);
		
	}
	public void showLogin() throws ServletException, IOException {
		String loginPage="frontend/login.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(loginPage);
		Dispatcher.forward(request, response);
		
	}
	public void doLogin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		Customer customer = customerDAO.checkLogin(email,password);
		if(customer==null) {
			request.setAttribute("message","Login failed.Please check your email and password.");
			showLogin();
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("loggedCustomer", customer);
			Object objRedirectUrl = session.getAttribute("redirectURL");
			if(objRedirectUrl !=null) {
				String redirectURL=(String)objRedirectUrl;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			}else {
				showCustomerProfile();
			}
		}
	}
	public void showCustomerProfile() throws ServletException, IOException {
		String customerProfilePAge="frontend/customer_profile.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(customerProfilePAge);
		Dispatcher.forward(request, response);
	}
	public void showCustomerProfileEditForm() throws ServletException, IOException {
		
		String editPage="frontend/edit_profile.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(editPage);
		Dispatcher.forward(request, response);
	}
	public void updateCustomerProfile() throws ServletException, IOException {
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		updateCustomerFiledsFromForm(customer);
		customerDAO.update(customer);
		showCustomerProfile();
	}
	
}
