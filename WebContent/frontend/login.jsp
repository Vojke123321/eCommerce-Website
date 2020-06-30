<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer login</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
	<div align="center">
		<h2>Customer Login</h2>
		<c:if test="${message !=null}">
			<div align="center">
				<h4>
					<h4 class="message">${message}</h4>
				</h4>
			</div>
		</c:if>
		<form action="login" id="formLogin" method="POST">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20" /></td>
				</tr>
				<tr>
					<td>password:</td>
					<td><input type="password" name="password" id="password"
						size="20" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
					</td>
				</tr>
			</table>
		</form>

	</div>
	<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#formLogin").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				password : "required"
			},
			messages : {
				email : {
					required : "Email is required",
					email : "please enter an valid eamil address.."
				},
				password : "password is required"
			}
		});
	});
</script>
</html>