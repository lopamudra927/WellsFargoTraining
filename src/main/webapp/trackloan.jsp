<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
	<table>
		<tr>
			<th>First Name</th>
			<th>Middle Name</th>
			<th>Last Name</th>
			<th>mob</th>
			<th>age</th>
			<th>email</th>
			<th>Address</th>
			<th>Occupation</th>
			<th>Loan Status</th>
		</tr>
		<c:forEach var="status" items="${userDto}">
			<tr>
				<td>${userDto.getFirstName()}</td>
				<td>${userDto.getMiddleName()}</td>
				<td>${userDto.getLastName()}</td>
				<td>${userDto.getMob()}</td>
				<td>${userDto.getAge()}</td>
				<td>${userDto.getEmail()}</td>
				<td>${userDto.getAddress()}</td>
				<td>${userDto.getOccupation()}</td>
				<td>${userDto.getLoanStatus()}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>