<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Info-Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	
	<div align="center">
			<table class="normal" width="60%">
				<tr>
					<td><h3>you already wrote a review for this book ;)</h3></td>
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
				<td>
				<div id="rateYo">
				</div>
					<br/>
					<input type="text" name="headLine" size="60" readonly="readonly" value="${review.headline}" />
					<br/>
					<br/>
					<textarea rows="10" cols="70" name="comment" readonly="readonly">${review.comment}</textarea>
				</td>
				</tr>
				
			</table>		
	</div>
	
	
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	$(document).ready(function() {
			 
		$("#rateYo").rateYo({
		    starWidth: "40px",
		    fullStar:true,
		    rating:${review.rating},
	    	readOnly:true
		   	
		  });
		   
			 
	});
</script>
</body>

</html>