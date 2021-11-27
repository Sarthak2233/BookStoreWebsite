<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>manage User-@erotscimoc ADMINISTRATION</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
		<h2>User Management</h2>
		<a href="user_form.jsp"><h4>Create NewUser</h4></a>
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
					<th>Actions</th>
				</tr>
				<c:forEach var="user" items="${listUsers }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td>
						<a href="edit_user?id=${user.userId }">Edit</a> &nbsp;
						<a href="javascript:void(0);"  class="deletelink" id="${user.userId }">Delete</a>
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
					userId=$(this).attr("id");
					if(confirm("Are you sure you want to delete the user having userid "+ userId)){
						window.location='delete_user?id='+userId;
					}
				});
			});
		});
		</script>
</body>
</html>