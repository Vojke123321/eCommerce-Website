<c:forTokens items="${book.ratingStars}" delims="," var="star">
	<c:if test="${star eq 'on'}">
		<img src="images/rating-on.png" />
	</c:if>
	<c:if test="${star eq 'off'}">
		<img src="images/rating-off.png" />
	</c:if>
	<c:if test="${star eq 'half'}">
		<img src="images/rating-half.png" />
	</c:if>
</c:forTokens>