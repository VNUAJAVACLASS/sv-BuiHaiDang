<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info</title>
<link rel="stylesheet" href="css/login.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<style type="text/css">
table {
	border-collapse: collapse;
	margin: 20px auto;
	width: 60%;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	font-family: 'Segoe UI', sans-serif;
}

table td {
	border: 1px solid #ddd;
	padding: 12px 16px;
	font-size: 16px;
	color: #333;
}

table tr:nth-child(even) {
	background-color: #f9f9f9;
}

table tr:hover {
	background-color: #f1f1f1;
}

h3 {
	margin-top: 30px;
	font-size: 22px;
	color: #2c3e50;
}
</style>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div align="center">
		<table>
			
			<tr>
				<td>Tai khoan</td>
				<td>${sessionScope.user.username}</td>
			</tr>
			<tr>
				<td>Họ và tên</td>
				<td>${sessionScope.user.fullname}</td>
			</tr>
			<tr>
				<td>Số điện thoại</td>
				<td>${sessionScope.user.mobile}</td>
			</tr>
			<tr>
				<td>email</td>
				<td>${sessionScope.user.email}</td>
			</tr>

			<tr>
				<td>Dia Chi</td>
				<td>${sessionScope.user.address}</td>
			</tr>
		</table>
	</div>


	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>