<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Users</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<h2 class="pageheading">User Management</h2>
		<h3>
			<a href="user_form.jsp">Create New user</a>
		</h3>
	</div>
	<c:if test="${message !=null}">
		<div align="center">
			<h4>
				<i>${message}</i>
			</h4>
		</div>
	</c:if>


	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Fullname</th>
				<th>password</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${list_of_users}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>${user.password}</td>
					<td><a href="edit_user?id=${user.userId}">Edit</a>&nbsp; <a
						href="javascript:confrimDelete(${user.userId})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	function confrimDelete(id){
	if(confirm('Are you sure you want to delete the user with id '+id+'?')){
		window.location='delete_user?id='+id;
		}

	}
	</script>

</body>
</html>