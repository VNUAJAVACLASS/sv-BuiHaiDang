<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">



<header class="header">
	<div class="login-header">
		<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<a> Hi: <strong> <%=session.getAttribute("username")%></strong>
				</a> |
				<a href="user_info_view.jsp"><i class="bi bi-person-circle"></i>
					Thông tin cá nhân</a> |
            
				<a href="${pageContext.request.contextPath}/cart_view.jsp"> <i class="bi bi-cart-check-fill"></i></a> |
				<a href="${pageContext.request.contextPath}/orderList"> 📦 Đơn hàng</a> |
				<a href="logout.jsp"><i class="bi bi-box-arrow-in-right"></i>
					Đăng xuất</a> 
			</c:when>
			<c:otherwise>
				<a href="login.jsp"><i class="bi bi-box-arrow-in-right"></i>
					Đăng nhập</a>
			</c:otherwise>
		</c:choose>
		
	</div>

	<div class="row">
		<div class="col-4 logo-header">
			<img alt="logo"
				src="${pageContext.request.contextPath}/img/logo_home.png">
		</div>
		<div class="col-7 title-header">Website cửa hàng bán sách với
			JSP/Servlet</div>
	</div>

	<nav class="nav-header">
		<ul class="navbar-nav ms-auto d-flex flex-row">
			<li class="nav-item"><a class="nav-link active"
				href="${pageContext.request.contextPath}/clientHome"><i
					class="bi bi-house"></i> Trang chủ</a></li>
			<li class="nav-item"><a class="nav-link active" href="#">Sách
					phổ biến</a></li>
			<li class="nav-item"><a class="nav-link active" href="#">Sách
					bán chạy</a></li>
			<li class="nav-item"><a class="nav-link active" href="#">Sách
					mới</a></li>
			<li class="nav-item"><a class="nav-link active" href="#">Giá
					thấp đến cao</a></li>
			<li class="nav-item"><a class="nav-link active" href="#">Giá
					cao đến thấp</a></li>
			<li class="nav-item"><input class="form-control" type="text"
				placeholder="Tìm kiếm sách..." aria-label="Search"></li>
		</ul>
	</nav>
</header>