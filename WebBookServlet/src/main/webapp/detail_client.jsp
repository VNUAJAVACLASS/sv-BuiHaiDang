<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="detail_client.jsp">
</head>
<body>
 <%-- <!-- Truy cập đối tượng Book được ghi vào request scope theo tên "book" -->
    <h2>${book.title}</h2>
    <p><b>Tác giả:</b> ${book.author}</p>
    <p><b>Giá:</b> ${book.price} VND</p>
    
    <c:if test="${not empty book.imagePath}">
        <p><img src="${book.imagePath}" alt="${book.title}" style="max-width:200px;"></p>
    </c:if>
    
    <br>
    <a href="${pageContext.request.contextPath}/books">Quay lại danh sách</a> --%>
    
      <div class="book-detail-container">
        <h2>${book.title}</h2>
        <p><b>Tác giả:</b> ${book.author}</p>
        <p><b>Giá:</b> ${book.price} VND</p>

        <c:if test="${not empty book.imagePath}">
            <p><img src="${book.imagePath}" alt="${book.title}"></p>
        </c:if>

        <a href="${pageContext.request.contextPath}/books">Quay lại danh sách</a>
    </div>
</body>
</html>