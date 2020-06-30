package com.vojke.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vojke.bookstore.entity.Users;


public class UsersTest {

	public static void main(String[] args) {
		Users user1=new Users();
		user1.setEmail("kositi@gmail.com");
		user1.setFullName("smilance peric");
		user1.setPassword("122345");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("BookStore");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(user1);
		
		
		tx.commit();
		em.close();
	}

}
