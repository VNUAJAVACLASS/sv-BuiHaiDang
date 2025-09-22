<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sách</title>
</head>
<body>
 	<p>Xin chào, ${sessionScope.username} | <a href="logout.jsp">Logout</a></p>
 	
    <h2>Trang chủ Admin - Danh sách tin tức</h2>
    <!-- Link gọi đến servlet "books" với tham số action=create -->
    <a href="adminHome?action=create">Thêm sách mới</a>
    <br><br>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Tác giả</th>
            <th>Giá</th>
            <th>Hình ảnh</th>
            <th>Hành động</th>
        </tr>
        <!-- Duyệt qua danh sách book -->
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.bookId}</td>
                <td>
                    <a href="adminHome?action=detail&id=${book.bookId}">
                        ${book.title}
                    </a>
                </td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td>
                    <c:if test="${not empty book.imagePath}">
                        <img src="${book.imagePath}" alt="${book.title}" style="max-width:60px;">
                    </c:if>
                </td>
                <td>
                    <a href="adminHome?action=edit&id=${book.bookId}">Sửa</a> |
                    <a href="adminHome?action=delete&id=${book.bookId}" onclick="return confirm('Xoá sách này?');">Xoá</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
