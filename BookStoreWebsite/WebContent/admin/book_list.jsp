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
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
		<h2>Books Management</h2>
		<a href="new_book"><h4>Create NewBook</h4></a>
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
					<th>Image</th>
					<th>Title</th>
					<th>Author</th>
					<th>Description</th>
					<th>Isbn</th>
					<th>Price</th>
					<th>Category</th>
					<th>Last UpdatedTime</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="book" items="${listBooks }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${book.bookId}</td>
					<td>  
						<img src="data:image/jpg;base64,${ book.base64Image}" width="94"  height="120"/>
					</td>
					<td>${book.title }</td>
					<td>${book.author}</td>
					<td>${book.description}</td>
					<td>${book.isbn}</td>
					<td>Rs.${book.price}</td>
					<td>${book.category.name}</td>
					<td> <fmt:formatDate pattern='MM/dd/yyyy' value="${book.lastUpdateTime }"/></td>
				
					<td>
						<a href="edit_book?id=${book.bookId }">Edit</a> &nbsp;
						<a href="javascript:void(0);"  class="deletelink" id="${book.bookId }">Delete</a>
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
					bookId=$(this).attr("id");
					if(confirm("Are you sure you want to delete the user having bookid "+ bookId)){
						window.location='delete_book?id='+bookId;
					}
				});
			});
		});
		</script>
</body>
</html>