<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết tài khoản</title>
<link rel="stylesheet" href="css/detail.css">
</head>
<body>

    <div class="detail-container">
        <h2>Thông tin tài khoản: ${user.username}</h2>

 		<p><b>Mật khẩu :</b> ${user.password}</p>
        <p><b>Họ tên:</b> ${user.fullname}</p>
        <p><b>Email:</b> ${user.email}</p>
        <p><b>Số điện thoại:</b> ${user.mobile}</p>
        <p><b>Địa chỉ:</b> ${user.address}</p>
        <p><b>Vai trò:</b>
            <c:choose>
                <c:when test="${user.role == 1}">Admin</c:when>
                <c:otherwise>Người dùng</c:otherwise>
            </c:choose>
        </p>

        <a href="${pageContext.request.contextPath}/adminUser">Quay lại danh sách</a>
    </div>

</body>
</html>