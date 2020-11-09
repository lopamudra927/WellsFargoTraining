<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

	<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->
	<div align="center">
		<h1>Place Loan</h1>
		<form action="<%=request.getContextPath()%>/placeloan" method="post">
			<table style="with: 80%">
				<tr>
					<td>Application Number</td>
					<td><input type="text" name="appNumber" /></td>
				</tr>
				<tr>
					<td>Purpose</td>
					<td><input type="text" name="purpose" /></td>
				</tr>
				<tr>
					<td>Amount Request</td>
					<td><input type="text" name="amtRequest" /></td>
				</tr>
				<tr>
					<td>DOA</td>
					<td><input type="text" name="doa" /></td>
				</tr>
				<tr>
					<td>Billing Structure</td>
					<td><input type="text" name="billStructure" /></td>
				</tr>
				<tr>
					<td>Billing Indicator</td>
					<td><input type="text" name="billIndicator" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" /></td>
				</tr>
			</table>
			<input type="submit" value="Apply Loan" />
		</form>
	</div>
</body>
</html>