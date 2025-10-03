<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/login.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="_header.jsp"></jsp:include>

	<div class="container-form-login">
		<form action="login" method="POST" class="form-login">
			<h2>Đăng nhập</h2>
			<input type="text" name="username" value="${rememberedUser}" required placeholder="Tên đăng nhập "
				class="input-login"> <br>

			<input type="password" name="password" required class="input-login" placeholder="Mật khẩu">
			<br> <br>
			<c:if test="${not empty error}">
				<p class="c-error">${error}</p>
			</c:if>

			<label> <input type="checkbox" name="remember" class="remember-login"> Ghi
				nhớ đăng nhập
			</label> <br> <br> <input type="submit" value="Đăng nhập" class="login-submit">
			<div  id="exitHome">
				<a href="index.jsp">Quay về trang chủ.</a>
			</div>
		</form>
	</div>
	
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>