<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin loginPage</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<div align="center">
	<h1>
	@erots <br><br> BOOKCOUNTER 
	</h1>
	<h1>Admin Login Page</h1>
		<c:if test="${message!=null }">
		<div align="center">
			<h4><i>${message}</i></h4>
		</div>
		</c:if>
		<form id="formlogin" action="login" method="post" >
			<table class="form">
			<tr>
		 		<td align="right">Email</td>
		 		<td align="left"><input type="text" id="email" name="email" size="20" value=""/></td>
		 	</tr>
		 	<tr> <td>&nbsp;</td></tr>
		 	<tr>
		 		<td align="right">Password</td>
		 		<td align="left"><input type="password" id="password" name="password" size="20" value=""/>
		 	</tr>
		 	<tr> <td>&nbsp;</td></tr>
		 	<tr>
		 		<td colspan="2" align="center">
		 		<button type="submit" >Login</button>&nbsp;&nbsp;&nbsp;&nbsp;
		 	</tr>
			</table>	
		</form>
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#formlogin").validate({
		rules:{
			email:{
				required:true,
				email:true
			},
			password:"required",
		},
		messages:{
			email:{
				required:"Enter email address.",
				email:"VALID EMAIL REQUIRED",
			},
			password:"Password required. Please enter it.",
		}
	});	
});
</script>
</html>