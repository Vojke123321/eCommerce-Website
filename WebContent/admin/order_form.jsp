<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Order Details</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
	</div>
	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">
				<i>${message}</i>
			</h4>
		</div>
	</c:if>

	<form action="update_order" method="POST" id="orderForm">
	<div align="center">
		
			<table>
				<tr>
					<td><b>Ordered By </b></td>
					<td>${order.customer.fullname}</td>				
				</tr>
				<tr>
					<td><b>Order Date: </b></td>
					<td>${order.orderDate}</td>				
				</tr>
				<tr>
					<td><b>Recipient Name: </b></td>
					<td><input type="text" value="${order.recipientName}" name="recipientName" size="45" /></td>				
				</tr>
				<tr>
					<td><b>Recipient phone: </b></td>
					<td><input type="text" value="${order.recipientPhone}" name="recipientPhone" size="45" /></td>				
				</tr>
				<tr>
					<td><b>Ship to: </b></td>
					<td><input type="text" value="${order.shippingAddress}" name="shippingAddress" size="45" /></td>				
				</tr>
					<tr>
					<td><b>Payment Method: </b></td>
					<td>
						<select name="paymentMethod">
							<option value="Cash on Delivery">Cash on Delivery</option>
						</select>
					</td>				
				</tr>
				<tr>
					<td><b>Order status: </b></td>
					<td>
						<select name="orderStatus">
							<option value="Procesing" <c:if test="${order.status eq 'Processing'}">selected='selected'</c:if> >Processing</option>	
							<option value="Shipping" <c:if test="${order.status eq 'Shipping'}">selected='selected'</c:if>>Shipping</option>
							<option value="Delivered" <c:if test="${order.status eq 'Delivered'}">selected='selected'</c:if> >Delivered</option>
							<option value="Completed" <c:if test="${order.status eq 'Completed'}">selected='selected'</c:if> >Completed</option>
							<option value="Cancelled" <c:if test="${order.status eq 'Cancelled'}">selected='selected'</c:if> >Cancelled</option>					
						</select>
					</td>				
				</tr>
				
			</table>
	</div>
	<div align="center">
		<h2>Ordered Books</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
				<th></th>
			</tr>
			<c:forEach items="${order.orderDetailses}" var="orderDetail" varStatus="status">
			
			<tr>
				<td>${status.index + 1}</td>
				<td>
				${orderDetail.book.title}
				</td>
				<td>${orderDetail.book.author}</td>
				<td>
					<input type="hidden" name="price" value="${orderDetail.book.price}"  />
				<fmt:formatNumber value="${orderDetail.book.price}" type="currency" />
				</td>
				<td>
				<input type="hidden" name="bookId" value="${orderDetail.book.bookId}"  />
				<input type="text" name="quantity${status.index+1}" value="${orderDetail.quantity}" size="5" />
				</td>
				<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
				<td><a href="remove_book_from_order?id=${orderDetail.book.bookId}">Remove</a></td>
				
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right"><b><i>TOTAL:</i></b></td>
				<td>
					<b>${order.bookCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
				<td></td>
			</tr>
		
		</table>
	
	</div> 
	
	<div align="center">
		<br/>	
		<a href="javascript:showAddBookPopUp()"><b>Add Books</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Save" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Cancel" onclick="javascript:window.location.href='list_order';" />
	</div>
	</form>
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	function confrimDelete(id){
	if(confirm('Are you sure you want to delete the order with id '+id+'?')){
		window.location='delete_order?id='+id;
		}

	}
	
	function showAddBookPopUp(){
		var width=600;
		var height=240;
		var left=(screen.width-width)/2;
		var top=(screen.height-height)/2;
		window.open('add_book_form','_blank','width='+width+',height='+height+',top='+top+',left='+left);
	}

	$(document).ready(function(){
		$("#orderForm").validate({
			rules:{
			recipientName:"required",
			recipientPhone:"required",
			shippingAddress:"required",
			<c:forEach items="${order.orderDetailses}" var="book" varStatus="status">
					quantity${status.index+1}: { 
					required:true , number: true , min: 1
			},
			</c:forEach>
		},
		messages:{
			recipientName:"Please enter recipient name",
			recipientPhone:"Please enter recipient phone",
			shippingAddress:"Please enter shipping address",
			<c:forEach items="${order.orderDetailses}" var="book" varStatus="status">
					quantity${status.index+1}: {
					 required: "please enter quantity" ,
					number: "Quantity must be a number",
					min: "Quantity must be greater than 0"
			},
		</c:forEach>
		}
		});
	});
		

	
	</script>

</body>
</html>