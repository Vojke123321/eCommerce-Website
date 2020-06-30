<div style="display: inline-block; margin: 20px;">
	<div>
		<a href="view_book?id=${book.bookId}"> <img
			src="data:image/jpg;base64,${book.base64image}" width="128"
			height="164" />
		</a>
	</div>
	<div>
		<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
		</a>
	</div>
	<div>
		<jsp:directive.include file="book_rating.jsp" />
	</div>
	<div>
		<i>by ${book.author}</i>
	</div>
	<div>
		<b>$ ${book.price}</b>
	</div>
</div>