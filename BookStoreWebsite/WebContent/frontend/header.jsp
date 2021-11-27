<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center"  style="background-color:#2F4F4F;">
	<div>
	<h1  style="color:#FF4500">@erots </h1>
	<h3 style="color:#FF4500"> BOOKCOUNTER</h3>
	</div>
	<div>
	<a href="${pageContext.request.contextPath}">
	<img src="frontend/images/book-icon-logo-vector-2982377.jpg">
	</a>
	</div>
	<div>
	<form action="search" method="get">
			 <input type="text" name="keyword" size="50"/>
			 <input type="submit"  value="Search"/><br/>
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>
	 </form>
	 <c:if test="${loggedCustomer==null}">
	 		<a href="login"><button>LOG IN.</button></a> <br><br>
			<a href="register"><button>SIGN UP.</button></a>	<br><br>
		</c:if>
		<c:if test="${loggedCustomer!=null}">
	 			<h1>Welcome</h1> <br>
	 		<a href="view_profile">	<h1>${loggedCustomer.fullname}</h1></a>
			<a href="view_orders">My Orders</a>	<br><br>
			<a href="logout">Log Out</a>	<br><br>
		</c:if>
			<a href="view_cart"><button>ADD TO CART.</button></a>	<br><br>
	</div>
	<div>
		<c:forEach var="category" items="${listCategory}" varStatus="status ">
			<a href="view_category?id=${category.categoryId }">
			<font size="+1"><b><c:out value="${category.name}"/></b></font>
			</a>
			<c:if test="${not status.last }">
			&nbsp; | &nbsp;
			</c:if>
	</c:forEach>
	</div>
</div>
