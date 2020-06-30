package com.vojke.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vojke.bookstore.entity.Users;

public class UserDAOTest{
	
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUp() {
		userDAO = new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("hajduk_ilic@gmail.com");
		user1.setFullName("hajduk peric");
		user1.setPassword("milancekralj");

		user1 = userDAO.create(user1);

		
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		

		user1 = userDAO.create(user1);

	}
	@Test
	public void testUpdateUserMethod() {
		Users user=new Users();
		user.setUserId(1);
		user.setEmail("update@gmail.com");
		user.setFullName("updated name");
		user.setPassword("updatedpassword");
		user=userDAO.update(user);
		String expected_email="update@gmail.com";
		assertEquals(expected_email,user.getEmail());
	}
	@Test
	public void testGetUsersById() {
		Integer userId=1;
		Users users = userDAO.get(userId);
		assertNotNull(users);
	}
	@Test
	public void testGetUsersNotFound() {
		Integer userId=99111;
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test
	public void testDeleteUsers() {
		Integer userId=6;
		userDAO.delete(userId);
		//above code delete user and then bellow method should back user which references pointing to null MY FRIEND
		Users user=userDAO.get(userId);
		assertNull(user);
	}
	@Test(expected = Exception.class)
	public void testDEletehNonExistUserShouldThrowException(){
		Integer userId=999;
		userDAO.delete(userId);
	}
	@Test
	public void testFindAllUsers() {
		List<Users> users=userDAO.listAll();
		assertTrue(users.size()>0);
	}

	@Test
	public void testCount() {
		Long count_of_users=userDAO.count();
		assertTrue(count_of_users>0);
	}
	@Test
	public void testFindByEmail() {
		String email="kurcina@gmail.com";
		Users user = userDAO.findbyEmail(email);
		assertNotNull(user);
	}
	@Test
	public void TestLoginSuccess() {
		String email="jovanic.vojke@gmail.com";
		String pass="vojke123321";
		boolean checkLogin = userDAO.checkLogin(email, pass);
		assertTrue(checkLogin);
	}
	@Test
	public void TestLoginFailed() {
		String email="DOESN'T EXISTSSSSe@gmail.com";
		String pass="9999";
		boolean checkLogin = userDAO.checkLogin(email, pass);
		assertFalse(checkLogin);
	}

	@AfterClass
	public static void tearDown() {
		userDAO.close();
	}

}
