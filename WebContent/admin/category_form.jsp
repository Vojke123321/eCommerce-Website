<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Category</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<c:choose>
			<c:when test="${category!=null}">
				<h2 class="pageheading">Edit Category</h2>
			</c:when>
			<c:when test="${category==null}">
				<h2 class="pageheading">Create new Category</h2>
			</c:when>
		</c:choose>
	</div>
	<div align="center">
		<c:if test="${category!=null}">
			<form action="update_category" method="POST" id="categoryForm">
			<input type="hidden"  name="categoryId" value="${category.categoryId}"/>	
		</c:if>
		<c:if test="${category==null}">
			<form action="create_category" method="POST" id="categoryForm">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Name:</td>
				<td align="left"><input type="text" name="name" size="20"
					id="name" value="${category.name}" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type="submit">Save</button>&nbsp;&nbsp;&nbsp; 
				<button onclick="javascript:history.go(-1);">Cancel</button>
					</td>
			</tr>
		</table>
		</form>
	</div>

	<div align="center"></div>

	<jsp:include page="footer.jsp" />

</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#categoryForm").validate({
		rules:{
		name:"required"
	},
	messages:{
		name:"Plase enter category name..."
	}
	});
});
	
	
</script>









</html>