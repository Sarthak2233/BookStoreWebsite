<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>manage Reviews-@erotscimoc ADMINISTRATION</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
		<h2>Review Management</h2>
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
					<th>Book</th>
					<th>Rating</th>
					<th>Headline</th>
					<th>Customer</th>
					<th>Reviewed On</th>
					<th>Action</th>
				</tr>
				<c:forEach var="review" items="${listReview}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${review.reviewId}</td>
					<td>${review.book.title}</td>
					<td>${review.rating}</td>
					<td>${review.headline}</td>
					<td>${review.customer.fullname}</td>
					<td>${review.reviewTime }</td>
					<td>
						<a href="edit_review?id=${review.reviewId}">Edit</a> &nbsp;
						<a href="javascript:void(0)" class="deletelink" id="${review.reviewId }">Delete</a>
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
					reviewId=$(this).attr("id");
					if(confirm("Are you sure you want to delete the category having reviewid "+ reviewId)){
						window.location='delete_review?id='+reviewId;
					}

				});
			});
		});
		</script>
</body>
</html>