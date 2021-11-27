<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center"  style="background-color:#2F4F4F;">
	<div>
	<h1>Administrative Dashboard</h1>
	<h1><a href="index.jsp">
	@erots <br><br> BOOKCOUNTER 
	</a>
	</h1>
	</div>
	<div>
		<a href="${pageContext.request.contextPath }/admin/">
		<img  src="images/download.jpg"/>
		</a>
	</div>
	 <div>
		<h1>Welcome <br><c:out value="${sessionScope.useremail }"></c:out> </h1> <br><a  href="logout" >Log out</a>
	</div>
	<br>
	<div id="headermenu">
		<div >
			 <a  href="list_users">
			 <img  src="images/user.png"/> <br> Users
			 </a>
		 </div> &nbsp;&nbsp;&nbsp;&nbsp;
		 
		 <div class="menu_item">
			 <a  href="list_category">
			 <img src="images/category.png"><br>Categories
			 </a> 
		 </div>&nbsp;&nbsp;&nbsp;&nbsp;
		 
		 <div class="menu_item">
			 <a  href="list_books">
			 <img src="images/books.png"/><br> Books
			 </a>
		 </div>&nbsp;&nbsp;&nbsp;&nbsp;
		 
		 <div class="menu_item">
			 <a  href="list_reviews" >
			 <img src="images/rating.png"/><br> Reviews
			 </a>&nbsp;&nbsp;&nbsp;
		 </div>
		 
		 <div class="menu_item">
			 <a href="list_customer">
			 <img src="images/customer.png"/><br> Customers
			 </a>
		</div> &nbsp;&nbsp;&nbsp;&nbsp;
		
	 	<div class="menu_item">
	 	 	<a  href="orders">
	 	 	<img src="images/order.png"/><br>Orders
	 	 	</a>
	 	</div> 
	</div>
</div>