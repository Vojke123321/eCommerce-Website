package com.vojke.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vojke.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {
		
	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}

	@Override
	public Review get(Object id) {
		return super.find(Review.class, id);
	}
	@Override
	public Review update(Review entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Review.class, id);
		
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}
	public Review findByCustomerAndBook(Integer customerId,Integer bookId) {
		Map<String,Object> parameters=new HashMap<>();
		parameters.put("customerID",customerId);
		parameters.put("bookID", bookId);
		 List<Review> result = super.findWithNamedQueryAndParamater("Review.findByCustomerAndBook",parameters);
		 if(!result.isEmpty()) {
			 return result.get(0);
		 }
		 return null;
		
	}
	public List<Review> listMostRecent(){
		return super.findWithNamedQuery("Review.listAll",0,3);
	}
}
