<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
	<h1>
	@erots <br><br> BOOKCOUNTER 
	</h1>
	<h1>Your Shopping Cart</h1>
		<c:if test="${message!=null }">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
		</c:if>

	<c:set var="cart" value="${sessionScope['cart']}"/>
	<c:if test="${cart.totalItems==0 }">
		<h1>There's no items in your cart.</h1>
	</c:if>
	<c:if test="${cart.totalItems > 0 }">
		<div>
			<form action="updateCart" method="post" id="cartForm">
			<div>
				<table class="form">
					<tr>
						<th>No</th>
						<th colspan="2">Book</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>SubTotal</th>
						<th><a href="clear_cart" style=" color:white"><b>Clear Cart</b></a></th>
					</tr>
					<c:forEach items="${cart.items }" var="item" varStatus="status">
					<tr>
						<td>${status.index+1 }</td>
						<td valign="middle"><img src="data:image/jpg;base64,${item.key.base64Image}"  width="100"  height="120"/>&nbsp;&nbsp;</td>
						<td><b>${item.key.title}</b></td>
						<td>
						<input type="hidden" name="bookId" value="${item.key.bookId }"/>
						<input type="text" name="quantity${status.index+1 }" value="${item.value}" size="5"/>
						</td><!-- this is the value from the map a integer -->
						<td>Rs ${item.key.price}</td>
						<td>Rs ${item.value*item.key.price}</td>
						<td><a href="remove_book?book_id=${item.key.bookId }">Remove</a></td>
					</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><b>${cart.totalQuantity }book(s)</b></td>
						<td>Total:</td>
						<td colspan="2">Rs ${cart.totalAmount}</td>
					</tr>
				</table>
				<table class="form">
					<tr>
						<td></td>
						<td align="char"><button type="submit">Update</button></td>
						<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
						<td><a href="">Check out</a></td>
					</tr>
				</table>
				</div>
			</form>
		</div>	
	</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#cartForm").validate({
		rules:{
		<c:forEach items="${cart.items }" var="item" varStatus="status">
			quantity${statis.index+1}:{required:true,
				number:true,
				min:1
				}
		</c:forEach>
		},
		messages:{
			<c:forEach items="${cart.items }" var="item" varStatus="status">
			quantity${statis.index+1}:{required:"Please enter quantity.", 
				number:"quantity must be a number.",
				min:"Quantity must be greater than 0"
				}
		</c:forEach>
		}
	});	
});
</script>
</html>