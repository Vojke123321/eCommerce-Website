<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>


</head>
<body>

	<jsp:include page="header.jsp" />
	<div align="center">
		<c:choose>
			<c:when test="${customer!=null}">
				<h2 class="pageheading">Edit Customer</h2>
			</c:when>
			<c:when test="${customer==null}">
				<h2 class="pageheading">Create new Customer</h2>
			</c:when>
		</c:choose>
	</div>
	<div align="center">
		<c:if test="${customer!=null}">
			<form action="update_customer" method="POST" id="customerForm">
				<input type="hidden" name="customerId" value="${customer.customerId}" />
		</c:if>
		<c:if test="${customer==null}">
			<form action="create_customer" method="POST" id="customerForm">
		</c:if>
		<table class="form">
			<tr>
			<td align="right">E-mail:</td>
			<td align="left"><input type="text" name="email" size="45"
				id="email" value="${customer.email}" /></td>
			</tr>
			<tr>
				<td align="right">Full name:</td>
				<td align="left"><input type="text" name="fullname" size="45"
					id="fullname" value="${customer.fullname}" /></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" name="password"
					size="45" id="password" value="${customer.password}" /></td>
			</tr>
			<tr>
				<td align="right">Confrim Password:</td>
				<td align="left"><input type="password" name="confrimpassword"
					size="45" id="confrimpassword" value="${customer.password}" /></td>
			</tr>
			<tr>
				<td align="right">address:</td>
				<td align="left"><input type="text" name="address" size="45"
					id="address" value="${customer.address}" /></td>
			</tr>
			<tr>
				<td align="right">city</td>
				<td align="left"><input type="text" name="city" size="45"
					id="city" value="${customer.city}" /></td>
			</tr>
			<tr>
				<td align="right">country:</td>
				<td align="left"><input type="text" name="country" size="45"
					id="country" value="${customer.country}" /></td>
			</tr>
			<tr>
				<td align="right">phone Number:</td>
				<td align="left"><input type="text" name="phoneNumber"
					size="45" id="phoneNumber" value="${customer.phoneNumber}" /></td>
			</tr>
			<tr>
				<td align="right">zipcode:</td>
				<td align="left"><input type="text" name="zipcode" size="45"
					id="zipcode" value="${customer.zipcode}" /></td>
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

		$("#customerForm").validate({
			rules : {
				email:{
					required:true,
					email:true
				},
				fullname:"required",
				address: "required",
				city: "required",
				country:"required",
				phoneNumber:"required",
				password:"required",
				confrimpassword:{
					required:true,
					equalTo: "#password"
				},
				zipcode:"required",
			},
			messages : {
				email:{
					required:"Email is required",
					email:"please enter an valid eamil address.."
					},
					confrimpassword:{
						required:"please confrim password",
						equalTo:"Confrim password does not match password my friend..."
					},
					fullname : "fullname is required",
					address : "address is required",
					city : "city is required",
					country : "country is required",
					phoneNumber : "phoneNumber is required",
					password : "password is required",
					zipcode : "zipcode is required",
			}
		});
	});
</script>









</html>