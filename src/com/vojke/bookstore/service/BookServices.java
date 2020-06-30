package com.vojke.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.vojke.bookstore.dao.BookDAO;
import com.vojke.bookstore.dao.CategoryDAO;
import com.vojke.bookstore.entity.Book;
import com.vojke.bookstore.entity.Category;

public class BookServices {
	private BookDAO bookDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO();
		categoryDAO = new CategoryDAO();
	}

	public void listBooks() throws ServletException, IOException {
		List<Book> listBooks = bookDAO.listAll();
		request.setAttribute("listbooks", listBooks);
		String listPage = "book_list.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(listPage);
		Dispatcher.forward(request, response);
	}

	public void ShowBookNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		for (Category category : listCategory) {
			System.out.println(category.getName());
		}
		request.setAttribute("listcategory", listCategory);

		String newPage = "book_form.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(newPage);
		Dispatcher.forward(request, response);
	}

	public void createBook() throws ServletException, IOException {
		String title = request.getParameter("title");

		Book existBook = bookDAO.findByTitle(title);
		if (existBook != null) {
			request.setAttribute("message",
					"Could not create new book becaus the title '" + title + "' already exists..");
			listBooks();
		}
		Book newBook=new Book();
		readBookFields(newBook);
		Book createdBook = bookDAO.create(newBook);
		if (createdBook.getBookId() > 0) {
			request.setAttribute("message", "A new Book has Been created Successfully..");
			listBooks();
		}
	}
	public void readBookFields(Book book)throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		DateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = null;
		try {
			publishDate = dateformat.parse(request.getParameter("publishdate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("error parsing publish date...");
		}
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishedDate(publishDate);
		book.setPrice(price);
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);

		Part part = request.getPart("bookImage");
		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imagebytes = new byte[(int) size];
			InputStream inputStream = part.getInputStream();
			inputStream.read(imagebytes);
			inputStream.close();

			book.setImage(imagebytes);
		}
	}

	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book = bookDAO.get(bookId);
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listcategory",listCategory);
		request.setAttribute("book", book);
		String editPage = "book_form.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(editPage);
		Dispatcher.forward(request, response);
	}

	public void updateBook() throws ServletException, IOException {
		Integer id=Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		Book existBook = bookDAO.get(id);
		Book bookbyTitle  = bookDAO.findByTitle(title);
		if(bookbyTitle!=null && !existBook.equals(bookbyTitle)) {
			request.setAttribute("message", "Could not update book because there's another book having same title.");
			listBooks();
		}
		readBookFields(existBook);
		
		bookDAO.update(existBook);
		request.setAttribute("message", "A Book has Been updated Successfully..");
		listBooks();
	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		bookDAO.delete(bookId);
		request.setAttribute("message", "The book has been deleted successfully my friend....");
		listBooks();
	}

	public void listBooksByCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		List<Book> listBooks = bookDAO.listBycategory(categoryId);
		Category category = categoryDAO.get(categoryId);
		request.setAttribute("category",category);
		request.setAttribute("listBooks",listBooks);
		RequestDispatcher Dispatcher = request.getRequestDispatcher("frontend/books_list_by_category.jsp");
		Dispatcher.forward(request, response);
	}

	public void viewBookDetail() throws ServletException, IOException {	
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book book=bookDAO.get(bookId);
		
		request.setAttribute("book",book);
		String detailPage = "frontend/book_detail.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(detailPage);
		Dispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		String keyword=request.getParameter("keyword");
		List<Book> result=null;
		if(keyword.equals("")) {
			result=bookDAO.listAll();
		}else {
			result= bookDAO.search(keyword);
		}
		request.setAttribute("keyword",keyword);
		request.setAttribute("result",result);
		
		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher Dispatcher = request.getRequestDispatcher(resultPage);
		Dispatcher.forward(request, response);
		
	}

}
