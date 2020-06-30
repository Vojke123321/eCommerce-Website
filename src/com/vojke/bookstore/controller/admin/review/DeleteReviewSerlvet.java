package com.vojke.bookstore.controller.admin.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vojke.bookstore.service.ReviewServices;

/**
 * Servlet implementation class DeleteReviewSerlvet
 */
@WebServlet("/admin/delete_review")
public class DeleteReviewSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteReviewSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewServices reviewServices=new ReviewServices(request, response);
		reviewServices.deleteReview();
	}

}
