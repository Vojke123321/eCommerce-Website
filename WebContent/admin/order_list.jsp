<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Orders</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Book Order Management</h2>
	</div>
	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">
				<i>${message}</i>
			</h4>
		</div>
	</c:if>


	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>OrderID</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrder}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
					<td>${order.paymentMethod}</td>
					<td>${order.status}</td>
					<td>${order.orderDate}</td>
					<td><a href="view_order?id=${order.orderId}">Details</a>&nbsp;
						<a href="edit_order?id=${order.orderId}">Edit</a>&nbsp;
						<a href="javascript:confrimDelete(${order.orderId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	function confrimDelete(id){
	if(confirm('Are you sure you want to delete the order with id '+id+'?')){
		window.location='delete_order?id='+id;
		}

	}
	</script>

</body>
</html>