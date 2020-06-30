<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result ${keyword}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<c:if test="${fn:length(result) ==0}">
			<h2>NO result for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<div align="left" style="width: 70%; margin: 0 auto;">
			<center><h2>Result for ${keyword}:</h2></center>
				<c:forEach items="${result}" var="book">
				<div>
					<div style="display: inline-block; margin: 20px; width: 10%">
						<div align="left">
							<a href="view_book?id=${book.bookId}"> <img
								src="data:image/jpg;base64,${book.base64image}" width="128"
								height="164" />
							</a>
						</div>
						
					</div>
					<div style="display: inline-block;margin: 20px; vertical-align: top; width: 60%"align="left">
						<div>
							<h2><a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
							</a></h2>
						</div>
						<div>Rating ******</div>
						<div>
							<i>by ${book.author}</i>
						</div>
						<div>
							<p>${fn:substring(book.description,0,100)}....</p>
						</div>
					</div>
					<div style="display: inline-block;margin: 20px; vertical-align: top;">
						<h3><b>$ ${book.price}</b></h3>
						<h3><a href="">ADD to Cart</a></h3>
					</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>