package com.vojke.bookstore.controller.admin.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.service.BookServices;

@WebServlet("/admin/create_book")
@MultipartConfig(fileSizeThreshold = 1014*10,maxFileSize = 1024*300,maxRequestSize = 1024*1024)
public class CreatedBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreatedBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookservices=new BookServices(request, response);
		bookservices.createBook();
	}

}
