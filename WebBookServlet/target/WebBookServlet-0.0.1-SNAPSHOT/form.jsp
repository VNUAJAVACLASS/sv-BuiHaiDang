<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Trang form.jsp dùng chung cho chức năng tạo mới và sửa sách
    request.setAttribute("isEdit", request.getAttribute("book") != null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${isEdit ? 'Sửa sách' : 'Thêm sách mới'}</title>
</head>
<body>
    <h2>${isEdit ? 'Sửa sách' : 'Thêm sách mới'}</h2>

    <form action="books" method="post">
        <c:if test="${isEdit}">
            <input type="hidden" name="bookId" value="${book.bookId}">
        </c:if>

        Tiêu đề: <br>
        <input type="text" name="title" value="${book.title}" required><br><br>

        Tác giả: <br>
        <input type="text" name="author" value="${book.author}" required><br><br>

        Giá: <br>
        <input type="number" step="0.01" name="price" value="${book.price}" required><br><br>

        Ảnh (đường dẫn): <br>
        <input type="text" name="image_path" value="${book.imagePath}"><br><br>

        <input type="submit" value="${isEdit ? 'Cập nhật' : 'Thêm mới'}">
    </form>

    <br>
    <a href="books">Quay lại danh sách</a>
</body>
</html>
