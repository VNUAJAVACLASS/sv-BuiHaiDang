<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách Sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container my-4">
    <h2 class="mb-4">Danh sách sách</h2>

    <div class="row">
        <c:forEach var="book" items="${bookList}">
            <div class="col-md-3 mb-4">
                <div class="card h-100 shadow-sm">
                    <c:if test="${not empty book.imagePath}">
                        <img src="${book.imagePath}" class="card-img-top" alt="${book.title}" style="height:250px; object-fit:cover;">
                    </c:if>
                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${book.title}</h5>
                        <p class="card-text text-muted mb-1">Tác giả: ${book.author}</p>
                        <p class="card-text text-danger fw-bold">${book.price}₫</p>
                        <a href="clientHome?action=detail&id=${book.bookId}" class="btn btn-primary mt-auto">
                            Xem chi tiết
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
