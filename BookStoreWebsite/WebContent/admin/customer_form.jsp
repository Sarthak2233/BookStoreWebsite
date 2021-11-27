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
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
			<h2>
				<c:if test="${customer != null }">
				Edit Customer
				</c:if>
				<c:if test="${customer == null }">
					Create New Customer
				</c:if>
				</h2>
		</div>
		
		<div align="center">
		<c:if test="${customer != null }">
		<form action="update_customer" method="post" id="customerForm">
		<input type="hidden" name="customerId" value="${customer.customerId}">
		</c:if>
		<c:if test="${customer == null }">
		<form action="create_customer" method="post"  id="customerForm" >
		</c:if>
		 <table class="form" align="center">
		 	<tr>
		 		<td align="right">E-mail:</td>
		 		<td align="left"><input type="text" id="email" name="email" size="45" value="${customer.email}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">FullName:</td>
		 		<td align="left"><input type="text" id="fullname" name="fullname" size="45" value="${customer.fullname}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Address:</td>
		 		<td align="left"><input type="text" id="address"  name="address" size="45" value="${customer.address}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Password:</td>
		 		<td align="left"><input type="text" id="password"  name="password" size="45" value="${customer.password}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Confirm-Password:</td>
		 		<td align="left"><input type="text" id="comfirmPassword"  name="confirmPassword" size="45" value="${customer.password}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">City:</td>
		 		<td align="left"><input type="text" id="city" name="city" size="45"  value="${customer.city}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Phone Number:</td>
		 		<td align="left"><input type="text" id="phoneNumber" name="phoneNumber" size="45" value="${customer.phone}"/></td>
		 	</tr>
			<tr>
		 		<td align="right">Zip-Code</td>
		 		<td align="left"><input type="text" id="zipCode" name="zipCode" size="10" value="${customer.zipcode}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Country:</td>
		 		<td align="left"><input type="text" id="country" name="country" size="20" value="${customer.country}"/></td>
		 	</tr>
		 	<tr> <td>&nbsp;</td></tr>
		 	<tr>
		 		<td colspan="2" align="center" id="button">
		 			<button><input type="submit"  value="Save" /></button>		&nbsp;&nbsp;&nbsp;
					<button><input type="button" id="buttonCancel"  href="javascript:history.go(-1);" value="Cancel" /></button>
		 		</td>
		 	</tr>
		 </table>
		 </form>
		</div>
		</div>
		<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
$('#customerForm').validate({
	rules:{
		email:{
			required:true,
			email:true
			},
		fullname:'required',
		address:'required',
		password:'required',
		
		confirmPassword:{
			required:true,
			equalTo:"#password"
			},
			
		city:'required',
		phoneNumber:'required',
		zipCode:'required',
		country:'required'
	},
	messages:{
		email:{
			required:"Please enter the email.",
			email:"Please enter the valid email address please"
			},
		fullname:"Please enter the valid full name please",
		address:"Please enter the valid address please",
		password:"Please enter the valid password please",
		confirmPassword:{
			required:'Password required',
			equalTo:"Confirm password dosent not match the password",
		},
		city:"Please enter the valid city please",
		phoneNumber:"Please enter the valid phone number please",
		zipCode:"Please enter the valid zip code please",
		country:"Please enter the valid country please"
	}
});
$("#buttonCancel").click(function(){
	history.go(-1);
});
});

</script>
</html>