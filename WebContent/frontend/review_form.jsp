<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write a Review-Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>

</head>
<body>
	<jsp:include page="header.jsp" />
	
	
	<div align="center">
		<form action="submit_review" id="reviewForm" method="POST">
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
				<td>
				<input type="hidden" name="RatingValue" value="" id="Rating" />
				<input type="hidden" name="bookId" value="${book.bookId}" />
				<div id="rateYo">
				</div>
					<br/>
					<input type="text" name="headLine" size="60" placeholder="HeadLine or summary for your review (required)">
					<br/>
					<br/>
					<textarea rows="10" cols="70" name="comment" placeholder="Write your review details"></textarea>
				</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<button type="submit">Submit</button>
						&nbsp;&nbsp;
						<button type="button" onclick="javascript:history.go(-1);">Cancel</button>
					</td>
				</tr>
			</table>		
		
		</form>
	
	</div>
	
	
	<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#reviewForm").validate({
			rules : {
				headLine : "required",
				comment : "required"
			},
			messages : {
				headLine : "Please enter headline",
				comment : "Please enter review details"
			}
		});
			 
		$("#rateYo").rateYo({
		    starWidth: "40px",
		    fullStar:true,
		    onSet: function (rating, rateYoInstance) {
				$("#Rating").val(rating);
			}
		  });
			 
	});
</script>
</html>