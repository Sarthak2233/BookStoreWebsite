<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>manage User-@erotscimoc ADMINISTRATION</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
		<h2>Customer Management</h2>
		<a href="customer_form.jsp"><h4>Create Customer</h4></a>
		</div>
		
		<c:if test="${message!=null }">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
		</c:if>
		<div align="center">
			<table border="1"cellpadding="7">
				<tr>
					<th>Index</th>
					<th>ID</th>
					<th>Email</th>
					<th>FullName</th>
					<th>Address</th>
					<th>City</th>
					<th>Country</th>
					<th>Phone</th>
					<th>RegisterDate</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="customer" items="${listCustomer }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname }</td>
					<td>${customer.address}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.phone}</td>
					<td>${customer.registerDate}</td>
					<td>
						<a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp;
						<a href="javascript:void(0);"  class="deletelink" id="${customer.customerId}">Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		</div>
		<jsp:directive.include file="footer.jsp"/>
		<script>
		$(document).ready(function() {
			$(".deletelink").each(function() {
				$(this).on("click", function() {
					customerId=$(this).attr("id");
					if(confirm("Are you sure you want to delete the customet having customerid "+ customerId)){
						window.location='delete_customer?id='+customerId;
					}
				});
			});
		});
		</script>
</body>
</html>