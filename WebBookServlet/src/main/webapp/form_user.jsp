<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
request.setAttribute("isEdit", request.getAttribute("user") != null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/form.css">
<title>${isEdit ? 'Sửa tài khoản' : 'Thêm tài khoản mới'}</title>
</head>
<body>

	<form action="adminUser" method="post">
		<h2>${isEdit ? 'Sửa tài khoản' : 'Thêm tài khoản mới'}</h2>

		<c:choose>
			<c:when test="${isEdit}">
				<p>Tên đăng nhập:</p>
				<input type="text" name="username" value="${user.username}" readonly>
			</c:when>
			<c:otherwise>
				<p>Tên đăng nhập:</p>
				<input type="text" name="username" required>
			</c:otherwise>
		</c:choose>

		<p>Mật khẩu:</p>
		<input type="password" name="password" value="${user.password}"
			required>

		<p>Họ tên:</p>
		<input type="text" name="fullname" value="${user.fullname}" required>

		<p>Email:</p>
		<input type="email" name="email" value="${user.email}" required>

		<br>
		<p>Số điện thoại:</p>
		<input type="text" name="mobile" value="${user.mobile}" required>

		<p>Địa chỉ:</p>
		<input type="text" name="address" value="${user.address}" required>

		<p>Vai trò (0: User, 1: Admin):</p>
		<input type="number" name="role" value="${user.role}" min="0" max="1"
			required> <input type="submit"
			value="${isEdit ? 'Cập nhật' : 'Thêm mới'}"> <a
			href="${pageContext.request.contextPath}/adminUser">Quay lại danh
			sách</a>
	</form>

</body>
</html>