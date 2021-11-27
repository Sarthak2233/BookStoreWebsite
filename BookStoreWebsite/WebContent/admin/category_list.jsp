<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>manage Category-@erotscimoc ADMINISTRATION</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
		<h2>Category Management</h2>
		<a href="category_form.jsp"><h4>Create New Category</h4></a>
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
					<th>Name</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="category" items="${listCategory }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${category.categoryId}</td>
					<td>${category.name}</td>
					<td>
						<a href="edit_category?id=${category.categoryId}">Edit</a> &nbsp;
						<a href="javascript:void(0)" class="deletelink" id="${category.categoryId }">Delete</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		</div>
		<jsp:directive.include file="footer.jsp"/>
		<script>
		$(document).ready(function(){
			$(".deletelink").each(function(){
				$(this).on("click" , function(){
					categoryId=$(this).attr("id");
					if(confirm("Are you sure you want to delete the category having categoryid "+ categoryId)){
						window.location='delete_category?id='+categoryId;
					}

				});
			});
		});
		</script>
</body>
</html>