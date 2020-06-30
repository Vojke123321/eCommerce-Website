package com.vojke.bookstore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.BookOrder;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO() {
	}
	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}
	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class,id);
	}

	@Override
	public void delete(Object book_id) {
		super.delete(Book.class,book_id);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}
	public Book findByTitle(String title) {
		List<Book> result = super.findWithNamedQueryAndParamater("Book.findByTitle", "title", title);
		if(!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	public List<Book> listBycategory(int categoryID){
	return	super.findWithNamedQueryAndParamater("Book.findByCategory","catId", categoryID);
		
	}
	public List<Book> listnewBooks(){
		return super.findWithNamedQuery("Book.listNew", 0, 4);
	}
	public List<Book> search(String keyword){
		return super.findWithNamedQueryAndParamater("Book.search","keyword", keyword);
	}
	

	@Override
	public long count() {
		return  super.countWithNamedQuery("Book.countAll");
	}
	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Book.countByCategory","catId", categoryId);
		
	}
	public List<Book> listBestSelilingBooks(){
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
	}
	
	public List<Book> listMostFavoredBooks(){
		 List<Book> mostFavoredBooks=new ArrayList<Book>();
		 List<Object[]> result= super.findWithNamedQueryObjects("Review.mostFavoredBooks", 0, 4);
		 if(!result.isEmpty()) {
			 for (Object[] elements:result) {
				Book book=(Book) elements[0];
				mostFavoredBooks.add(book);
			}
		 }
		 return  mostFavoredBooks;
		 
	}


}
