<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="../css/style.css">
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>
Edit Review
</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
		<div>
		<div align="center">
			<h2>
				Edit Category
				</h2>
		</div>
		
		<div align="center">
		<form action="update_review" method="post" id="reviewForm">
		<input type="hidden" name="reviewId" value="${review.reviewId }">
		 <table class="form">
		 	<tr>
		 		<td align="right">Book:</td>
		 		<td align="left"><b>${review.book.title }</b></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Rating:</td>
		 		<td align="left"><b>${review.rating }</b></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Customer:</td>
		 		<td align="left"><b>${review.customer.fullname }</b></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Headline:</td>
		 		<td align="left"><input type="text"  name="headline" size="40" value="${review.headline}"/></td>
		 	</tr>
		 	<tr>
		 		<td align="right">Comment:</td>
		 		<td align="left">
		 			<textarea style="margin: 0px; width: 558px; height: 164px;" name="comment">${review.comment} </textarea>
		 		</td>
		 	</tr>
		 	<tr> <td>&nbsp;</td></tr>
		 	<tr>
		 		<td colspan="2" align="center">
		 			<input type="submit"  value="Save" />		&nbsp;&nbsp;&nbsp;
					<input type="button" id="buttonCancel"  value="Cancel" />
		 	</tr>
		 </table>
		 </form>
		</div>
		</div>
		<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#reviewForm").validate({
		rules: {
			headline:"required",
			comment:"required"
		},
		messages:{
			headline:"Headline required.",
			comment:"comment required"
		}
	});
	$("#buttonCancel").click(function(){
		history.go(-1);
	});
});
</script>
</html>