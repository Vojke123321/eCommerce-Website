<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${book.title}-BookStore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<table width="80%" style="border: 0">
			<tr>
				<td colspan="3" align="left">
					<h2>${book.title}</h2> by ${book.author}
				</td>
			</tr>
			<tr>
				<td rowspan="2"><img src="data:image/jpg;base64,${book.base64image}"
					width="240" height="300" /></td>
				<td valign="top" align="left"><jsp:directive.include file = "book_rating.jsp" />&nbsp;&nbsp;
				<a href="#reviews">${fn:length(book.reviews)}	Reviews</a>
				</td>

				<td valign="top" rowspan="2" width="20%"><h2>$${book.price}</h2> <br />
					<button id="buttonAddToCart">Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td valign="top" style="text-align: justify;">${book.description}</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
			<td><h2><a id="reviews">Customer Reviews</a></h2></td>
			<td colspan="2" align="center">
				<button type="button" id="buttonWriteAreview">Write a Customer Review</button>
			</td>
			</tr>
			
			<tr>
			<td colspan="3" align="left">
				<table class="normal">
					<c:forEach items="${book.reviews}" var="review">
					<tr>
					<td>
					<c:forTokens items="${review.stars}" delims="," var="star">
										<c:if test="${star eq 'on'}">
											<img src="images/rating-on.png" />
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src="images/rating-off.png" />
										</c:if>
					</c:forTokens>
					- <b>${review.headline}</b>
									</td>
					</tr>
					<tr>
						<td>
							by ${review.customer.fullname} on ${review.reviewTime}
						</td>
					</tr>
					<tr><td><i>${review.comment}</i></td></tr>
					<tr><td>&nbsp;</td></tr>
					</c:forEach>
				</table>
			</td>
			</tr>
			
		</table>

	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	$(document).ready(function() {
	
		$("#buttonWriteAreview").click(function(){
			window.location='write_review?book_id='+${book.bookId};
		});

		$("#buttonAddToCart").click(function(){
			window.location='add_to_cart?book_id='+${book.bookId};
		});
	});
	</script>
</body>
</html>