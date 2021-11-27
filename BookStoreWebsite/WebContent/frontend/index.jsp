<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>EVERGREEN BOOKS- ONLINE BOOKSTORE</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		
		<div align="center">
			<div align="center" >
			<h2> NEW BOOKS</h2>
			<c:forEach items="${listNewBooks}" var="book">
			<div style="display:inline-block; margin:30px;">
				<div >
					<img src="data:image/jpg;base64,${ book.base64Image}" width="100"  height="120"/>
				</div>
				<div><a href="view_book?id=${book.bookId }"><b>${book.title}</b></a></div>
				<div>
				<c:forTokens items="${book.ratingStar}" delims="," var="stars">
					<c:if test="${stars eq 'on'}">
						<img src="${pageContext.request.contextPath}/frontend/images/rating_on.png">
					</c:if>
					<c:if test="${stars eq 'off'}">
						<img src="${pageContext.request.contextPath}/frontend/images/rating_off.png">
					</c:if>
					<c:if test="${stars eq 'half'}">
						<img src="${pageContext.request.contextPath}/frontend/images/rating_half.png">
					</c:if>
				</c:forTokens>
				</div>
				<div><i>by${book.author}</i></div>
				<div>Rs${book.price}</div>
				</div>
			</c:forEach>
			</div>
			<h3>BEST-SELLING's</h3>
			<h4>OUR TOP LISTS</h4>
		</div>
		<jsp:directive.include file="footer.jsp" />
</body>
</html>