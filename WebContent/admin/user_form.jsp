<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New user</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<c:choose>
			<c:when test="${user!=null}">
				<h2 class="pageheading">Edit User</h2>
			</c:when>
			<c:when test="${user==null}">
				<h2 class="pageheading">Create new User</h2>
			</c:when>
		</c:choose>
	</div>
	<div align="center">
		<c:if test="${user!=null}">
			<form action="update_user" method="POST" id="userForm">
				<input type="hidden" name="userId" value="${user.userId}" />
		</c:if>
		<c:if test="${user==null}">
			<form action="create_user" method="POST" id="userForm">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Email</td>
				<td align="left"><input type="text" name="email" size="20"
					id="email" value="${user.email}" /></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" name="fullname" size="20"
					id="fullName" value="${user.fullName}" /></td>
			</tr>
			<tr>
				<td align="right">password</td>
				<td align="left"><input type="password" name="password"
					size="20" id="password" value="${user.password}" /></td>
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
		$("#userForm").validate({
			rules:{
			email:{
				required:true,
				email:true
			},
			fullname:"required",
			password:"required"
		},
		messages:{
			email:{
			required:"Email is required",
			email:"please enter an valid eamil address.."
			},
			fullname:"full name is required",
			password:"password is required"
		}
		});
	});

</script>









</html>