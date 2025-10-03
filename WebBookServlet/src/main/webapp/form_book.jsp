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
<link rel="stylesheet" href="css/form.css">
<title>${isEdit ? 'Sửa sách' : 'Thêm sách mới'}</title>

</head>
<body>

    <form action=adminHome method="post" >
    <h2>${isEdit ? 'Sửa sách' : 'Thêm sách mới'}</h2>
        <c:if test="${isEdit}">
			<p>Mã sản phẩm </p>
            <input type="text" name="bookId" value="${book.bookId}" readonly="readonly">
        </c:if>
		
        <p>Tiêu đề: </p>
        <input type="text" name="title" value="${book.title}" required>

        <p>Tác giả:</p>
        <input type="text" name="author" value="${book.author}" required>

        	<p>Giá: </p>
        <input type="number" step="0.01" name="price" value="${book.price}" required>

        <p>Ảnh (đường dẫn): </p>
        <input type="text" name="image_path" value="${book.imagePath}"  required="required">

		<p>Số lượng hàng: </p>
        <input type="number" name="quantityInStock" value="${book.quantityInStock}"  required="required">
        
        <p>Chi tiết hàng hóa: </p>
        <input type="text" name="detail" value="${book.detail}"  required="required">
        
        
        <input type="submit" value="${isEdit ? 'Cập nhật' : 'Thêm mới'}">
    <a href="${pageContext.request.contextPath}/adminHome">Quay lại Admin</a>
    </form>

</body>
</html>
