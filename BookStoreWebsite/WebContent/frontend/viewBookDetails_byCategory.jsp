<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>${book.title}- ONLINE BOOKSTORE</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp" />
		
		<div align="center">
			<table style="border:none; background: #FFFAFA;" width="80%">
				<tr>
					<td valign="middle">
					
					</td>
					<td valign="middle">
					<h2>${book.title}</h2>
					</td>
				</tr>
				<tr>
					<td>
						<img src="data:image/jpg;base64,${ book.base64Image}" width="300"  height="350"/><br>
						by${book.author}
					</td>
					<td colspan="0" valign="top">
						${book.description}<br>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<br>
						<button id="buttonAddtoCart">ADD To Cart</button>
						<br>
						<br>
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
				<a href="#reviews">View ${fn:length(book.reviews)} Reviews</a>
					</td>
					<td valign="top" rowspan="2" width="20%">
						<h3>Rs${book.price}</h3>
					</td>
				</tr>
				<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
				<tr>
					<td>
						<a id="reviews"><h2>Customer Reviews</h2></a>
					</td>
					<td colspan="2" align="center">
						<button id="ButtonWriteReview">Write Review</button>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="left">
							<table style="border:none;">
							<c:forEach items="${book.reviews}" var="review">
								<tr>
									<td>
									<c:forTokens items="${review.stars}" delims="," var="stars">
										<c:if test="${stars eq 'on'}">
											<img src="${pageContext.request.contextPath}/frontend/images/rating_on.png">
										</c:if>
										<c:if test="${stars eq 'off'}">
											<img src="${pageContext.request.contextPath}/frontend/images/rating_off.png">
										</c:if>
									</c:forTokens>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<b> ${review.headline}</b>
									</td>
								</tr>
								<tr>
									<td>
										by ${review.customer.fullname} on ${review.reviewTime}
									</td>
								</tr>
								<tr><td><i>${review.comment}</i></td></tr>
								<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<jsp:directive.include file="footer.jsp" />
		<script type="text/javascript">
		$(document).ready(function(){
			$("#ButtonWriteReview").click(function(){
				window.location='write_review?book_id='+${book.bookId};
			});
			$("#buttonAddtoCart").click(function(){
				window.location='add_to_cart?book_id='+${book.bookId};
			});
		});
		</script>
</body>
</html>