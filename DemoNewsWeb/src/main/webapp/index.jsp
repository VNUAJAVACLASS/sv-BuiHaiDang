<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<h2>Danh sach tin tuc client</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
             <th>Tác giả </th>
             <th>Giá</th>
             <th>Hình ảnh</th>
            <th>--</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.bookId}</td>
                <td>
                    <a>
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
                     <a href="clientHome?action=detail&id=${book.bookId}">
                        Xem chi tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        
    </tbody>
</table>

</body>
</html>