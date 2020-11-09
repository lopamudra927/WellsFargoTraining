<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	-->
	<table>
		<tr>
			<th>Application Number</th>
			<th>Purpose</th>
			<th>Amount Requested</th>
			<th>Date of Approval</th>
			<th>Billing Structure</th>
			<th>Billing Indicator</th>
			<th>Address</th>
		</tr>
		<c:forEach var="loanList" items="${loans}">
			<tr>
				<td>${loans.getApplno()}</td>
				<td>${loans.getPurpose()}</td>
				<td>${loans.getAmtrequest()}</td>
				<td>${loans.getDoa()}</td>
				<td>${loans.getBstructure()}</td>
				<td>${loans.getBindicator()}</td>
				<td>${loans.getAddress()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>