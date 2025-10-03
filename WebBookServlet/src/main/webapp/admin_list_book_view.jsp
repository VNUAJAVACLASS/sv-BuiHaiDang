<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
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
				<li class="active"><a
					href="${pageContext.request.contextPath}/adminHome">📚 Quản
						lí sản phẩm</a></li>
				<li><a href="${pageContext.request.contextPath}/adminUser">👤
						Quản lí tài khoản</a></li>
				<li><a href="${pageContext.request.contextPath}/adminOrderList">📦
						Quản lí đơn hàng</a></li>
			</ul>
		</div>

		<!-- Content -->
		<div class="content">
			<div class="content_header">
				<h3>Danh Sách Book</h3>
				<button class="logout">
					<a href="logout.jsp"><i class="bi bi-box-arrow-right"></i></a>
				</button>
			</div>

			<div class="action_buttons">
				<button class="btn green">
					<a href="adminHome?action=create">+ Tạo sách mới</a>
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
					<th>ID</th>
					<th>Tiêu đề</th>
					<th>Tác giả</th>
					<th>Giá</th>
					<th>Số hàng tồn kho</th>
					<th>Chi tiết hàng hóa</th>
					<th>Ngày tạo đơn hàng</th>
					<th>Hình ảnh</th>
					<th>Hành động</th>
				</tr>
				<!-- Duyệt qua danh sách book -->
				<c:forEach var="book" items="${bookList}">
					<tr>
						<td>${book.bookId}</td>
						<td><a href="adminHome?action=detail&id=${book.bookId}">
								${book.title} </a></td>
						<td>${book.author}</td>
						<td>${book.price}</td>
						<td>${book.quantityInStock }</td>
						<td>${book.detail}</td>
						<td>${book.createDate }</td>
						<td><c:if test="${not empty book.imagePath}">
								<img src="${book.imagePath}" alt="${book.title}"
									style="max-width: 60px;">
							</c:if></td>
						<td><a href="adminHome?action=edit&id=${book.bookId}"><i
								class="bi bi-pencil-square text-success"></i></a> | <a
							href="adminHome?action=delete&id=${book.bookId}"
							onclick="return confirm('Xoá sách này?');"> <i
								class="bi bi-trash text-danger"></i></a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
</html>
