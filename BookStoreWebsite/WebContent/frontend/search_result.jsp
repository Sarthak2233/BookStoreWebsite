<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Results for  ${keywords} on BOOKS- ONLINE BOOKSTORE</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		<div align="left">
			<c:if test="${fn:length(result)==0 }">
				<h1>Nothing for ${keyword}</h1>
			</c:if>
			<c:if test="${fn:length(result)>0 }">
				<div align="left" style="width:80%: margin:0 auto;" >
					<h2> Results for ""${keyword}""</h2>
					<c:forEach items="${result}" var="book">
					<div>
						<div style="display:inline-block; margin:20px;">
							<div align="left">
									<a href="view_book?id=${book.bookId }"><img 
									src="data:image/jpg;base64,${ book.base64Image}" width="80"  height="80"/>
									</a>
							</div>
						</div>
						<div  style="display:inline-block; margin:20px; vertical-align:top;" align="justify">
							<div><h4><a href="view_book?id=${book.bookId }"><b>${book.title}</b></a></h4></div>
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
							<div>
							<i> by${book.author}</i>
							<div><p>${fn:substring(book.description,0,55)}..........</p></div>
							</div>
						</div>
						<div style="display:inline-block; margin:20px; vertical-align:top;" ">
							<h5>Rs${book.price}</h5>
							  <a href="add_to_cart?book_id=${book.bookId }"><button>ADD To Cart</button></a>
						</div>
					</div>
				</c:forEach>
			</div>
			</c:if>
		</div>
		<jsp:directive.include file="footer.jsp" />
</body>
</html>