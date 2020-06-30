package com.vojke.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.dao.BookDAO;
import com.vojke.bookstore.dao.ReviewDAO;
import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.Customer;
import com.vojke.bookstore.entity.Review;

public class ReviewServices {
	private ReviewDAO reviewDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		reviewDAO=new ReviewDAO();
	}
	public void listAllReview() throws ServletException, IOException {
		List<Review> listReviews = reviewDAO.listAll();
		request.setAttribute("listReviews",listReviews);
		String listPage = "review_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(listPage);
		Dispatcher.forward(request, response);
	}
	public void editReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.get(reviewId);
		request.setAttribute("review",review);
		String editPage = "review_form.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(editPage);
		Dispatcher.forward(request, response);
	}
	public void updateReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("reviewId"));
		String headline=request.getParameter("headline");
		String comment=request.getParameter("comment");
		Review review = reviewDAO.get(reviewId);
		review.setComment(comment);
		review.setHeadline(headline);
		
		reviewDAO.update(review);
		request.setAttribute("message","the review has been updated successfully");
		listAllReview();
		
		
		
	}
	public void deleteReview() throws ServletException, IOException {
		Integer reviewId=Integer.parseInt(request.getParameter("id"));
		reviewDAO.delete(reviewId);
		request.setAttribute("message","The review has been deleted successfully");
		listAllReview();
	}
	
	
	public void showReviewForm() throws ServletException, IOException {
		Integer bookID=Integer.parseInt(request.getParameter("book_id"));
		BookDAO bookDAO=new BookDAO();
		Book book=bookDAO.get(bookID);
		request.getSession().setAttribute("book",book);
		request.setAttribute("book",book);
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		
		Review existReview = reviewDAO.findByCustomerAndBook(customer.getCustomerId(),bookID);
		String targetPage="frontend/review_form.jsp";
		if(existReview!=null) {
			request.setAttribute("review",existReview);
			 targetPage="frontend/review_info.jsp";
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);
		
	
	}
	public void submitReview() throws ServletException, IOException {
		Integer bookID=Integer.parseInt(request.getParameter("bookId"));
		Integer rating=Integer.parseInt(request.getParameter("RatingValue"));
		String headline=request.getParameter("headLine");
		String comment=request.getParameter("comment");
		Review review=new Review();
		review.setHeadline(headline);
		review.setComment(comment);
		review.setRating(rating);
		
		Book book=new Book();
		book.setBookId(bookID);
		review.setBook(book);
		
		Customer customer=(Customer) request.getSession().getAttribute("loggedCustomer");
		review.setCustomer(customer);
		reviewDAO.create(review);
		String messagePage="frontend/review_done.jsp";
		RequestDispatcher dispatcher=request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
		
	}
	
	
	
	
	
	
	
	
	
	
}
