<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>

	<!-- Hiển thị thông báo lỗi nếu có -->
	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>

	<form action="login" method="POST" id="loginForm">
		<h2>Đăng nhập</h2>
		<p>Tên đăng nhập:</p>
		<input type="text" name="username" value="${rememberedUser}" required
			class="input-login"> <br>

		<p>Mật khẩu:</p>
		<input type="password" name="password" required class="input-login">
		<br>
		<br>
		<c:if test="${not empty error}">
			<p class="c-error">${error}</p>
		</c:if>

		<label> <input type="checkbox" name="remember"> Ghi
			nhớ đăng nhập
		</label> <br>
		<br> <input type="submit" value="Đăng nhập">
		<a href="index.jsp" id="exitHome">Quay về trang chủ.</a>
	</form>
</body>
</html>