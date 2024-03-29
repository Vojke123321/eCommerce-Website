package com.vojke.bookstore.entity;
// Generated Jun 10, 2020 6:04:10 PM by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * OrderDetails generated by hbm2java
 */
@Entity
@Table(name = "order_details", catalog = "nagzt44h6wbjp1g9")
@NamedQueries({
	@NamedQuery(name = "OrderDetail.bestSelling",query = "select od.book from OrderDetail od group by od.book.bookId order by sum(od.quantity) DESC")
})
public class OrderDetail implements java.io.Serializable {

	private OrderDetailId id=new OrderDetailId();
	private Book book;
	private BookOrder bookOrder;
	private int quantity;
	private float subtotal;


	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Book book, BookOrder bookOrder,int quantity, float subtotal) {
		this.id = id;
		this.book = book;
		this.bookOrder = bookOrder;
		this.quantity=quantity;
		this.subtotal=subtotal;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id",nullable = false)),
			@AttributeOverride(name = "bookId", column = @Column(name = "book_id",nullable = false))
			})
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", insertable = false, updatable = false,nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book; 
		this.id.setBook(book);
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", insertable = false, updatable = false,nullable = false)
	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
		this.id.setBookOrder(bookOrder);
	}
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "subtotal", nullable = false, precision = 12, scale = 0)
	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}


}
