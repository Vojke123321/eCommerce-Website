package com.vojke.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vojke.bookstore.entity.Users;


public class categoryTest {

	public static void main(String[] args) {
		Category newcat=new Category();
		newcat.setName("finalna_proba");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("BookStore");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(newcat);
		em.flush();
		System.out.println(newcat.getCategoryId());
		em.refresh(newcat);
		
		tx.commit();
		em.close();
	}

}
