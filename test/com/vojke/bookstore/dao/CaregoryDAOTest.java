package com.vojke.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vojke.bookstore.entity.Category;

public class CaregoryDAOTest {
	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUp() {
		categoryDAO = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateCategory() {
		Category newCat=new Category("health");
		Category category = categoryDAO.create(newCat);
		assertTrue(category != null && category.getCategoryId()>0);
		
	}
	@Test
	public void testUpdateCategory() {
		Category cat=new Category("java core");
		cat.setCategoryId(1);
		Category category = categoryDAO.update(cat);
		assertEquals(cat.getName(), category.getName());
		
	}
	

	@Test
	public void testGet() {
		Integer catid=3;
		Category category = categoryDAO.get(catid);
		assertNotNull(category);
	}

	@Test
	public void testDeleteCategoryt() {
		Integer catid=3;
		categoryDAO.delete(catid);
		Category category = categoryDAO.get(catid);
		assertNull(category);
		
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDAO.listAll();
		assertTrue(listCategory.size()>0);
	}

	@Test
	public void testCount() {
		long count = categoryDAO.count();
		System.out.println(count);
		assertTrue(count>0);
	}
	@Test
	public void testFindByName() {
		Category category = categoryDAO.findByName("java core");
		assertNotNull(category);
	}
	@Test
	public void testFindByNameNotFound() {
		Category category = categoryDAO.findByName("IVICA BOMBAS");
		assertNull(category);
	}

	@AfterClass
	public static void tearDown() {
		categoryDAO.close();
	}
}
