<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Sách</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

<link href="css/index.css" rel="stylesheet">
</head>
<body class="bg-light">

	<!-- Header -->
	<header class="header">
		<div class="login-header">
			<a href="login.jsp"><i class="bi bi-person-circle"></i> Đăng nhập</a>
		</div>
		<div class="row">
			<div class="col-4 logo-header">
				<img alt="logo" src="img/logo_home.png">
			</div>
			<div class="col-7 title-header">Website Cửa hàng bán sách với
				JSP/Servlet</div>
		</div>

		<nav class="nav-header">
			<ul class="navbar-nav ms-auto d-flex flex-row">
				<li class="nav-item"><a class="nav-link active"
					href="index.jsp"><i class="bi bi-house"></i> Trang chủ</a></li>
				<li class="nav-item"><a class="nav-link active" href="#">Sách
						phổ biến</a></li>
				<li class="nav-item"><a class="nav-link active" href="#">Sách
						bán chạy</a></li>
				<li class="nav-item"><a class="nav-link active" href="#">Sách
						mới</a></li>
				<li class="nav-item"><a class="nav-link active" href="#">Giá
						thấp đến cao</a></li>
				<li class="nav-item"><a class="nav-link active" href="#">Giá
						cao đến giá thấp</a></li>
				<li class="nav-item"><input class=" form-control" type="text"
					placeholder="Tìm kiếm sách..." aria-label="Search"></li>
			</ul>
		</nav>
	</header>


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


				<!-- Book 1 -->
				<div class="book-card">
					<img src="img/CuLinhThanChuong.jpg" alt="Book 1">
					<div class="card-body">
						<h5>Lập Trình Java Cơ Bản</h5>
						<p>Tác giả: Nguyễn Văn A</p>
						<p class="price">120000₫</p>
						<a href="#" class="btn">Xem chi tiết</a>
					</div>
				</div>

				<!-- Book 2 -->
				<div class="book-card">
					<img src="img/MuaThan.jpg" alt="Book 2">
					<div class="card-body">
						<h5>Học Spring Boot Nâng Cao</h5>
						<p>Tác giả: Trần Văn B</p>
						<p class="price">150000₫</p>
						<a href="#" class="btn">Xem chi tiết</a>
					</div>
				</div>

				<!-- Book 3 -->
				<div class="book-card">
					<img src="img/NhuLai.jpg" alt="Book 3">
					<div class="card-body">
						<h5>Thuật Toán Và Cấu Trúc Dữ Liệu</h5>
						<p>Tác giả: Lê Văn C</p>
						<p class="price">180000₫</p>
						<a href="#" class="btn">Xem chi tiết</a>
					</div>
				</div>

				<!-- Book 4 -->
				<div class="book-card">
					<img src="img/XichLoi.jpg" alt="Book 4">
					<div class="card-body">
						<h5>C# Toàn Tập</h5>
						<p>Tác giả: Phạm Văn D</p>
						<p class="price">200000₫</p>
						<a href="#" class="btn">Xem chi tiết</a>
					</div>
				</div>



				<!-- BookList -->
				<c:forEach var="book" items="${bookList}">
					<div class="book-card">
						<c:if test="${not empty book.imagePath}">
							<img src="${book.imagePath}" alt="${book.title}">
						</c:if>
						<div class="card-body">
							<h5>${book.title}</h5>
							<p>Tác giả: ${book.author}</p>
							<p class="price">${book.price}₫</p>
							<a href="clientHome?action=detail&id=${book.bookId}" class="btn">Xem
								chi tiết</a>
						</div>
					</div>
				</c:forEach>
			</div>

			<div id="show-book-list">
				<a href="clientHome">Xem thêm danh sách sản phẩm</a>
			</div>

			<div class="container_pagination">
				<div class="pagination">
					<a href="#" class="prev">Previous</a> <a href="#" class="active"
						data-page="1">1</a> <a href="#" data-page="2">2</a>
				</div>
			</div>
		</section>
	</div>







	<footer>
		<div class="footer_container">
			<span>Bản quyền thuộc nhóm tác giả cuốn sách "Giáo trình lập
				trình Java 2"</span>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
