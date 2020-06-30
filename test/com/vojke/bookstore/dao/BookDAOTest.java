package com.vojke.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.Category;

public class BookDAOTest{
	private static BookDAO bookDAO;

	@BeforeClass
	public static void setUp() {
		bookDAO = new BookDAO();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category("advanced java");
		category.setCategoryId(2);

		book.setCategory(category);
		book.setTitle("Effective Java (2nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop,");
		book.setPrice(38.87f);
		book.setIsbn("0321356683");
		String imagePath = "D:\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishedDate = dateFormat.parse("05/28/2008");
		book.setPublishedDate(publishedDate);
		Book createdBook = bookDAO.create(book);
		assertTrue(createdBook.getBookId() > 0);
	}
	@Test
	public void testCreateSecoundBook() throws ParseException, IOException {
		Book book = new Book();
		Category category = new Category("java core");
		category.setCategoryId(1);

		book.setCategory(category);
		book.setTitle("Java 8 in Action");
		book.setAuthor("Raoul-Gabriel Urma");
		book.setDescription("java 8 in Action is a clearly written guide to the new features of Java 8.");
		book.setPrice(36.72f);
		book.setIsbn("1617291994");
		String imagePath = "D:\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishedDate = dateFormat.parse("08/28/2014");
		book.setPublishedDate(publishedDate);
		Book createdBook = bookDAO.create(book);
		assertTrue(createdBook.getBookId() > 0);
	}

	@Test
	public void testUpdateBook() throws IOException, ParseException {
		// exist book is bellow.........
		Book book = new Book();
		Category category = new Category("java core");
		category.setCategoryId(1);
		book.setBookId(1);
		book.setCategory(category);
		book.setTitle("Effective Java (3nd Edition)");
		book.setAuthor("Joshua Bloch");
		book.setDescription("New coverage of generics, enums, annotations, autoboxing, the for-each loop,");
		book.setPrice(38.87f);
		book.setIsbn("0321356683");
		String imagePath = "D:\\books\\Effective Java.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		book.setImage(imageBytes);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishedDate = dateFormat.parse("05/28/2008");
		book.setPublishedDate(publishedDate);
		Book updateBook = bookDAO.update(book);
		assertEquals(updateBook.getTitle(), "Effective Java (3nd Edition)");
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId=999;
		bookDAO.delete(bookId);
		
	}
	@Test
	public void testDeleteBookSuccess() {
		bookDAO.delete(1);
		
	}
	@Test
	public void testGetBookFail() {
		Integer bookId=999;
		Book book = bookDAO.get(bookId);
		assertNull(book);
		
	}
	@Test
	public void testGetBookSuccess() {
		Integer bookId=2;
		Book book = bookDAO.get(bookId);
		assertNotNull(book);
	}
	@Test
	public void testListAll() {
		List<Book> ListBooks = bookDAO.listAll();
		assertFalse(ListBooks.isEmpty());
	}
	@Test
	public void testFindByTitleNotExist() {
		String title="Thinkin in javaaaaaaaaaaaaaaaaaa";
		Book result = bookDAO.findByTitle(title);
		assertNull(result);
		
	}
	@Test
	public void testFindByTitleExist() {
		String title="Java 8 in Action";
		Book result = bookDAO.findByTitle(title);
		assertNotNull(result);
		
	}
	@Test
	public void testCount() {
		long totalBooks= bookDAO.count();
		assertEquals(2, totalBooks);
		
	}
	@Test
	public void testListByCategory() {
		int categoryId=1;
		List<Book> booksByCategoryId = bookDAO.listBycategory(categoryId);
		assertTrue(booksByCategoryId.size()>0);
	}
	@Test
	public void testListNewBooks() {
		List<Book> listnewBooks = bookDAO.listnewBooks();
		
		assertEquals(4, listnewBooks.size());
	}
	@Test
	public void testSearchBookByTitle() {
		String keyword="java";
		List<Book> result = bookDAO.search(keyword);
		assertTrue(result.size()>4);
		
	}
	@Test
	public void testSearchBookByAuthor() {
		String keyword="Brian Goetz";
		List<Book> result = bookDAO.search(keyword);
		for (Book book : result) {
			System.out.println(book.getTitle());
		}
		assertTrue(result.size()>0);
		
	}
	@Test
	public void testSearchBookByDescription() {
		String keyword="The Big picture";
		List<Book> result = bookDAO.search(keyword);
		for (Book book : result) {
			System.out.println(book.getTitle());
		}
		assertTrue(result.size()>0);
		
	}
	@Test
	public void testCountByCategory() {
		int CategoryId=2;
		long numOfBooks = bookDAO.countByCategory(CategoryId);
		System.out.println(numOfBooks);
		assertTrue(numOfBooks>0);
	}
	@Test
	public void testListBestSelilingBooks() {
		List<Book> topBestSellingBooks = bookDAO.listBestSelilingBooks();
		for (Book book : topBestSellingBooks) {
			System.out.println(book.getTitle());
		}
		assertEquals(4, topBestSellingBooks.size());
	}
	@Test
	public void testListMostFavoredBooks() {
		List<Book> topFavoredBooks = bookDAO.listMostFavoredBooks();
		for (Book book : topFavoredBooks) {
			System.out.println(book.getTitle());
		}
		assertEquals(4, topFavoredBooks.size());
	}

	@AfterClass
	public static void tearDown() {
		bookDAO.close();
	}

}
