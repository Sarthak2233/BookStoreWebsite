<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="jquery.rateyo.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
			<table class="normal" style="border:none; " width=80% >
			<tr>
				<td><h1>Your reviews</h1></td>
				
				<td align="right">
				<h1><b>${loggedCustomer.fullname}</b></h1>
				</td>
			</tr>
			<tr>
				<td colspan="3"><hr/></td>
			</tr>
			<tr>
				<td>
				<img src="data:image/jpg;base64,${ book.base64Image}" width="300"  height="350"/><br>
				<b>by ${book.author}</b>
				<b>by ${book.title}</b>
				</td>
				<td>
					<p>Your review has been posted Successfully!</p>
				</td>
			</tr>
			</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>