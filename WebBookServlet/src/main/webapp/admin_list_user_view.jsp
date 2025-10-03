<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin User</title>
<link rel="stylesheet" href="css/list.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<div class="container_body">
	<!-- Sidebar -->
	<div class="sidebar">
		<div class="profile">
			<img src="img/people.png" alt="avatar">
			<div class="profile_info">
				<h4>${sessionScope.username}</h4>
				<p>Chào mừng bạn trở lại</p>
			</div>
		</div>
		<button class="pos_btn">POS Bán Hàng</button>
		<ul class="menu_list">
			<li><a href="${pageContext.request.contextPath}/adminHome">📚 Quản lí sản phẩm</a></li>
			<li class="active"><a href="${pageContext.request.contextPath}/adminUser">👤 Quản lí tài khoản</a></li>
			<li><a href="${pageContext.request.contextPath}/adminOrderList">📦 Quản lí đơn hàng</a></li>
		</ul>
	</div>

	<!-- Content -->
	<div class="content">
		<div class="content_header">
			<h3>Danh Sách Tài Khoản</h3>
			<button class="logout">
				<a href="logout.jsp"><i class="bi bi-box-arrow-right"></i></a>
			</button>
		</div>

		<div class="action_buttons">
			<button class="btn green">
				<a href="adminUser?action=create">+ Tạo Tài khoản</a>
			</button>
			<button class="btn blue">Tải từ file</button>
			<button class="btn gray">In dữ liệu</button>
			<button class="btn yellow">Sao chép</button>
			<button class="btn green">Xuất Excel</button>
			<button class="btn red">Xuất PDF</button>
			<button class="btn gray">Xóa tất cả</button>
		</div>

		<table border="1" cellpadding="5">
			<tr>
				<th>Tên đăng nhập</th>
				<th>Họ tên</th>
				<th>Email</th>
				<th>Số điện thoại</th>
				<th>Địa chỉ</th>
				<th>Vai trò</th>
				<th>Hành động</th>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td><a href="adminUser?action=detail&username=${user.username}">${user.username}</a></td>
					<td>${user.fullname}</td>
					<td>${user.email}</td>
					<td>${user.mobile}</td>
					<td>${user.address}</td>
					<td>
						<c:choose>
							<c:when test="${user.role == 1}">Admin</c:when>
							<c:otherwise>Người dùng</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="adminUser?action=edit&username=${user.username}"><i class="bi bi-pencil-square text-success"></i></a> |
						<a href="adminUser?action=delete&username=${user.username}" onclick="return confirm('Xoá tài khoản này?');">
							<i class="bi bi-trash text-danger"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>