<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Categories</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
		<h3>
			<a href="category_form.jsp">Create New Category</a>
		</h3>
	</div>
	 <c:if test = "${message !=null}">
	<div	align="center">
	<h4 cl><i>${message}</i></h4> 
	</div>
	</c:if>
	

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="cat" items="${listCategory}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${cat.categoryId}</td>
					<td>${cat.name}</td>
					<td>
					<a href="edit_category?id=${cat.categoryId}">Edit</a>&nbsp;
					<a href="javascript:confrimDelete(${cat.categoryId})">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	function confrimDelete(id){
	if(confirm('Are you sure you want to delete the category with id '+id+'?')){
		window.location='delete_category?id='+id;
		}

	}
	</script>

</body>
</html>