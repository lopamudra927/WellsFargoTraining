<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body>
	<!-- write the html code to display hyperlinks for user functionalities -->
	<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4>User Dash Board</h4>
<a href="user?action=application1">Loan Application</a><br>
<a href="user?action=trackloan">Track Loan</a><br>
<a href="user?action=editloan">Edit Loan</a><br>
<jsp:include page="footer.jsp"/>
	
</body>
</html>