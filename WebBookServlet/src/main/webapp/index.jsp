<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<link href="css/index.css" rel="stylesheet">
</head>
<body class="bg-light">

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<jsp:include page="_header.jsp"></jsp:include>



	<div class="container_body">
		<!-- Cột trái -->
		<div class="sidebar">
			<div class="sidebar_menu">
				<ul class="category-list">
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách thiếu nhi</li>
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách ngoại ngữ</li>
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách kỹ năng sống</li>
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách nuôi dạy con</li>
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách có học tinh hoa</li>
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách kinh tế - xã hội</li>
					<li><img class="nav_icon_book" src="asses/icon/book_nav.png"
						alt="">Sách khoa học - công nghệ</li>
				</ul>
			</div>

			<div class="sidebar_email">
				<div class="email-register">
					<h4>Đăng ký nhận email</h4>
					<input type="email" placeholder="Nhập email..." />
					<button>Gửi</button>
				</div>
			</div>

		</div>

		<!-- Cột phải -->
		<section class="content">
			<!-- Book list -->
			<div class="book-list">
				<c:forEach var="book" items="${bookList}">
					<div class="book-card">
						<c:if test="${not empty book.imagePath}">
							<img src="img/MuaThan.jpg" alt="${book.title}">
						</c:if>
						<div class="card-body">
							<h5>${book.title}</h5>
							<p>Tác giả: ${book.author}</p>
							<p class="price">${book.price}₫</p>
							<a href="clientHome?action=detail&id=${book.bookId}" class="btn">
							Xem chi tiết</a>
						</div>
					</div>
				</c:forEach>
			</div>

			<!-- Phan trang  -->
			<div class="container_pagination">
				<div class="pagination">
					<c:if test="${currentPage > 1}">
						<a href="clientHome?action=list&page=${currentPage - 1}"
							class="prev">← Trước</a>
					</c:if>

					<c:forEach var="i" begin="1" end="${totalPages}">
						<a href="clientHome?action=list&page=${i}"
							class="${i == currentPage ? 'active' : ''}">${i}</a>
					</c:forEach>

					<c:if test="${currentPage < totalPages}">
						<a href="clientHome?action=list&page=${currentPage + 1}"
							class="next">Tiếp →</a>
					</c:if>
				</div>
			</div>
		</section>
	</div>


	<jsp:include page="_footer.jsp"></jsp:include>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
