<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="jquery.rateyo.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<form id="reviewForm" action="submit_review" method="post">
			<table class="normal" style="border:none; " width=80% >
			<tr>
				<td><h1>Your reviews</h1></td>
				<td align="right">
				<h1><b>${loggedCustomer.fullname}</b></h1>
				</td>
			</tr>
			<tr>
				<td colspan="3"><hr/></td>
			</tr>
			<tr>
				<td align="left"><i>${book.title}</i></td>
			</tr>
			<tr>
				<td>
				<img src="data:image/jpg;base64,${ book.base64Image}" width="300"  height="350"/><br>
				<b>by ${book.author}</b><br>
				</td>
				<td>
					<div id="rateYo"></div>
					<input type="hidden" id="rating" name="rating"/>
					<input type="hidden"  name="bookId" value="${book.bookId}"/>
					<br/>
					<input type="text" name="headline" size="60"  placeholder="Headline for review required.">
					<br><br/>
					<textarea name="comment" rows="10" cols="60" placeholder="Write your review details here.."></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3"valign="middle">
					<button type="submit">Submit</button>
					&nbsp;&nbsp;
					<button id="Cancel">Cancel</button>
				</td>
			</tr>
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#reviewForm").validate({
		rules:{
			headline:"required",
			comment:"required"
		},
		messages:{
			headline:"Please enter headline.",
			comment:"Please enter review details.."
		}
	});	
	
	$("#Cancel").click(function(){
		history.go(-1);
	});
	 
	$("#rateYo").rateYo({
		    starWidth: "40px",
		    fullStar: true,
		    onSet: function(rating,rateYoInstance){
		    	$("#rating").val(rating);
		    }
		  });
});
</script>
</html>