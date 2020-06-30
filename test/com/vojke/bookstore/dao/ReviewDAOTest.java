package com.vojke.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.common.AssertionFailure;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.Customer;
import com.vojke.bookstore.entity.Review;

public class ReviewDAOTest {
	private static ReviewDAO reviewDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDAO=new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDAO.close();
	}

	@Test
	public void testCreateReview() {
		Review review=new Review();
		Book book=new Book();
		book.setBookId(2);
		Customer customer=new Customer();
		customer.setCustomerId(7);
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline("Excellent book!");
		review.setRating(5);
		review.setComment("A comprehnsive book about spring framework..");
		Review savedReview = reviewDAO.create(review);
		
		assertTrue(savedReview.getReviewId()>0);
	}
	@Test
	public void testGetReview() {
		Integer reviewId=1;
		Review reviewFromDB = reviewDAO.get(reviewId);
		assertNotNull(reviewFromDB);
	}
	@Test
	public void testUpdateReview() {
		Integer reviewId=1;
		Review reviewFromDB = reviewDAO.get(reviewId);
		reviewFromDB.setHeadline("Excellent Book");
		
		Review updatedReview = reviewDAO.update(reviewFromDB);
		
		assertEquals("Excellent Book",updatedReview.getHeadline());
	}
	@Test
	public void testListAll() {
		List<Review> listReview= reviewDAO.listAll();
		for (Review review : listReview) {
			System.out.println(review.getHeadline());
		}
		assertTrue(!listReview.isEmpty());
	}
	@Test
	public void testCountReviews() {
		long totalReview=reviewDAO.count();
		
		assertTrue(totalReview>0);
	}
	@Test
	public void testDeleteReview() {
		reviewDAO.delete(2);
		Review reviewFromDB = reviewDAO.get(2);
		assertNull(reviewFromDB);
	}
	@Test
	public void testFindByCustomerAndBookNotFound() {
		Integer customerId=909;
		Integer bookID=999;
		Review result = reviewDAO.findByCustomerAndBook(customerId, bookID);
		assertNull(result);
		
	}
	@Test
	public void testFindByCustomerAndBookFound() {
		Integer customerId=4;
		Integer bookID=11;
		Review result = reviewDAO.findByCustomerAndBook(customerId, bookID);
		assertNotNull(result);
		
	}
	
	@Test
	public void testListMostRecent() {
		List<Review> recentReviews=reviewDAO.listMostRecent();
		assertEquals(3, recentReviews.size());
	}

}
