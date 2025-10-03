<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sách</title>
<link rel="stylesheet" href="css/detail.css">
</head>
<body>
    
    <div class="detail-container">
        <h2>${book.title}</h2>
        <p><b>Tác giả:</b> ${book.author}</p>
        <p><b>Giá:</b> ${book.price} VND</p>
		<p><b>Số lượng trong kho:</b> ${book.quantityInStock}</p>
		<p><b>Ngày tạo:</b> ${book.createDate}</p>
		<p><b>Mô tả sách:</b> ${book.detail} VND</p>
        <c:if test="${not empty book.imagePath}">
            <p><img src="${book.imagePath}" alt="${book.title}"></p>
        </c:if>

        <a href="${pageContext.request.contextPath}/adminHome">Quay lại danh sách</a>
    </div>
    
</body>
</html>