<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>@erotscimoc ADMINISTRATION</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
			<hr width="100%"/>
			<h3  style="color:#FF4500">Quick Actions</h3>
			<b>
			 <a  href="create-book">New Book</a><br><br>
			 <a  href="create-category">New Category</a><br><br>
			 <a href="create-user">New User</a><br><br>
			 <a  href="create-customer">New Customer </a>
			 </b>
		</div>
		<div align="center">
		<hr width="60%"/>
		<h3  style="color:#FF4500">Recent Sales</h3>
		</div>
		<div align="center">
		<hr width="60%"/>
		<h3  style="color:#FF4500">Recent Reviews</h3>
		</div>
		<div align="center">
		<hr width="60%"/>
		<h3  style="color:#FF4500">Statistics</h3>
		</div>
		</div>
		<jsp:directive.include file="footer.jsp"/>
</body>
</html>