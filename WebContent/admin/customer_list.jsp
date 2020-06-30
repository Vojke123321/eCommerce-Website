<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Customer Management</h2>
		<h3>
			<a href="customer_form.jsp">Create New Customer</a>
		</h3>
	</div>
	<c:if test="${message !=null}">
		<div align="center">
			<h4>
				<i class="message">${message}</i>
			</h4>
		</div>
	</c:if>


	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${customers}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td><fmt:formatDate pattern="MM/dd/yyyy" value="${customer.registerDate}"/></td>
					<td><a href="edit_customer?id=${customer.customerId}">Edit</a>&nbsp; <a
						href="javascript:confrimDelete(${customer.customerId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	function confrimDelete(id){
	if(confirm('Are you sure you want to delete customer with id '+id+'?')){
		window.location='delete_customer?id='+id;
		}

	}
	</script>

</body>
</html>