<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<jsp:include page="header.jsp" />
<div align="center">
	<h2 class="pageheading">Administrative Dashboard</h2>
</div>
<div align="center">
	<hr width="60%" />
	<h2 class="pageheading">Quick actions:</h2>
	<b> <a href="new_book">New Book</a> &nbsp; <a href="user_form.jsp">New
			User</a> &nbsp; <a href="category_form.jsp">New Category</a> &nbsp; <a
		href="customer_form.jsp">New Customer</a> &nbsp;
	</b>
</div>
<div align="center">
	<hr width="60%" />
	<h2 class="pageheading">Recent sales:</h2>
	<table border="1">
		<tr>
			<th>Order ID</th>
			<th>Ordered By</th>
			<th>Book Copies</th>
			<th>Total</th>
			<th>Payment Method</th>
			<th>Status</th>
			<th>Order Date</th>
		</tr>
		<c:forEach items="${listMostRecentSales}" var="order"
			varStatus="status">

			<tr>
				<td><a href="view_order?id=${order.orderId}">${order.orderId}</a></td>
				<td>${order.customer.fullname}</td>
				<td>${order.bookCopies}</td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
				<td>${order.paymentMethod}</td>
				<td>${order.status}</td>
				<td>${order.orderDate}</td>
			</tr>

		</c:forEach>
	</table>
</div>
<div align="center">
	<hr width="60%" />
	<h2 class="pageheading">Recent reviews:</h2>
	<table border="1">
		<tr>
			<th>Book</th>
			<th>Rating</th>
			<th>HeadLine</th>
			<th>Customer</th>
			<th>Review On</th>
		</tr>
		<c:forEach items="${mostRecentReviews}" var="review">

			<tr>
				<td>${review.book.title}</td>
				<td>${review.rating}</td>
				<td><a href="edit_review?id=${review.reviewId}">${review.headline}</a></td>
				<td>${review.customer.fullname}</td>
				<td>${review.reviewTime}</td>
			</tr>

		</c:forEach>
	</table>
</div>
<div align="center">
	<hr width="60%" />
	<h2 class="pageheading">statistics:</h2>
	<b>Total Users:${totalUsers} </b> &nbsp;&nbsp;&nbsp;&nbsp;
	<b> Total Books:${totalBooks} </b> &nbsp;&nbsp;&nbsp;&nbsp;
	<b> Total Customers:${totalCustomers} </b> &nbsp;&nbsp;&nbsp;&nbsp;
	<b> Total Reviews:${totalReviews} </b> &nbsp;&nbsp;&nbsp;&nbsp;
	<b> Total Orders:${totalORders} </b> &nbsp;&nbsp;&nbsp;&nbsp;
	<hr width="60%" />
	
</div>

<jsp:include page="footer.jsp" />

</body>
</html>