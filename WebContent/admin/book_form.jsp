<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>


</head>
<body>

	<jsp:include page="header.jsp" />
	<div align="center">
		<c:choose>
			<c:when test="${book!=null}">
				<h2 class="pageheading">Edit Book</h2>
			</c:when>
			<c:when test="${book==null}">
				<h2 class="pageheading">Create new Book</h2>
			</c:when>
		</c:choose>
	</div>
	<div align="center">
		<c:if test="${book!=null}">
			<form action="update_book" method="POST" id="bookForm" enctype="multipart/form-data">
				<input type="hidden" name="bookId" value="${book.bookId}" />
		</c:if>
		<c:if test="${book==null}">
			<form action="create_book" method="POST" id="bookForm" enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td>Category:</td>
				<td><select name="category">
						<c:forEach items="${listcategory}" var="c">
						<c:if test="${c.categoryId eq book.category.categoryId}">
						<option value="${c.categoryId}" selected>
						</c:if>
						<c:if test="${c.categoryId ne book.category.categoryId}">
						<option value="${c.categoryId}">
						</c:if>
							${c.name}
							</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" name="title" size="20"
					id="title" value="${book.title}" /></td>
			</tr>
			<tr>
				<td align="right">Autohor:</td>
				<td align="left"><input type="text" name="author" size="20"
					id="author" value="${book.author}" /></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" name="isbn" size="20"
					id="isbn" value="${book.isbn}" /></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left"><input type="text" name="publishdate"
					size="20" id="publishdate" value='<fmt:formatDate pattern="MM/dd/yyyy" value="${book.publishedDate}"/>' /></td>
			</tr>
			<tr>
				<td align="right">Book Image:</td>
				<td align="left">
				<input type="file" name="bookImage" size="20" id="bookImage" /> <br/>
				<img alt="image preview" id="thumbnail" style="width: 20%;margin-top: 10px;'" src="data:image/jpg;base64,${book.base64image}"  >
				</td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" name="price" size="20"
					id="price" value="${book.price}" /></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left"><textarea rows="5" cols="50" id="description" name="description">${book.description}</textarea>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type="submit">Save</button>&nbsp;&nbsp;&nbsp; 
				<button type="button" onclick="javascript:history.go(-1);">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<div align="center"></div>

	<jsp:include page="footer.jsp" />

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#publishdate").datepicker();

		$("#bookImage").change(function(){
			showImageThnumbnail(this);
		});
		$('#description').richText();
		
	
		$("#bookForm").validate({
			rules : {
				category: "required",
				title:"required",
				author: "required",
				isbn: "required",
				publishdate:"required",

				<c:if test="${book==null}">
				bookImage:"required",
				</c:if>
				price:"required",
				description:"required"
			},
			messages : {
				category:"category is requred",
				title : "title is required",
				author : "author is required",
					isbn : "isbn is required",
						publishdate : "publishdate is required",
							bookImage : "bookImage is required",
								price : "price is required",
								description : "description is required"
			}
		});
	});
	function showImageThnumbnail(FileInput){
		var file=FileInput.files[0];

		var reader=new FileReader();
		reader.onload=function(e){
			$("#thumbnail").attr('src',e.target.result);
		};
		reader.readAsDataURL(file)
	};
</script>









</html>