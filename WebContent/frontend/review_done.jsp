<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted-Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	
	
	<div align="center">
			<table class="normal" width="60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullname}</h2></td>				
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
				<td>
				<b><span style="font-size: 25px;">${book.title}</span></b><br/>
					<img src="data:image/jpg;base64,${book.base64image}"
					width="240" height="300" />				
				</td>
				<td colspan="2">
					<h3>Your review has been posted.Thank you!</h3>
				</td>
				</tr>
			</table>		
		
	
	</div>
	
	
	<jsp:include page="footer.jsp" />
</body>
</html>