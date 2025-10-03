<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details Client</title>
<style type="text/css">
	.book-detail-wrapper {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    gap: 40px;
    padding: 30px;
    max-width: 900px;
    margin: 0 auto;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 12px rgba(0,0,0,0.1);
    font-family: 'Segoe UI', sans-serif;
}

.book-info {
    flex: 1;
    font-size: 16px;
    color: #333;
}

.book-info p {
    margin-bottom: 10px;
}

.book-image img {
    max-width: 400px;
    border-radius: 8px;
    box-shadow: 0 0 8px rgba(0,0,0,0.1);
}

.quantity-control {
    display: flex;
    align-items: center;
    margin-top: 15px;
}

.qty-btn {
    width: 30px;
    height: 30px;
    font-size: 18px;
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.qty-input {
    width: 40px;
    text-align: center;
    margin: 0 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 5px;
}

.add-to-cart-btn {
    margin-top: 20px;
    background-color: #2ecc71;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 6px;
    cursor: pointer;
    font-weight: bold;
    transition: background-color 0.3s;
}

.add-to-cart-btn:hover {
    background-color: #27ae60;
}

.add-to-cart-btn a{
	text-decoration: none;
	color: #fff;
}
</style>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>


	<div class="book-detail-wrapper">
      <!-- Cột phải: ảnh sách -->
    <div class="book-image">
        <c:if test="${not empty book.imagePath}">
            <img src="${book.imagePath}" alt="${book.title}">
        </c:if>
    </div>
    
    <!-- Cột trái: thông tin sách -->
    <div class="book-info">
        <p><b>Tiêu đề:</b> ${book.title}</p>
        <p><b>Tác giả:</b> ${book.author}</p>
        <p><b>Giá:</b> ${book.price} VND</p>
        <p><b>Số lượng:</b> ${book.quantityInStock}</p>
        <p><b>Ngày tạo sản phẩm:</b> ${book.createDate}</p>
        <p><b>Chi tiết:</b> ${book.detail}</p>

        <div class="quantity-control">
            <button class="qty-btn" onclick="decreaseQty(this)">−</button>
            <input type="text" value="1" class="qty-input" readonly>
            <button class="qty-btn" onclick="increaseQty(this)">+</button>
        </div>
	
	<c:choose>
	    <c:when test="${not empty sessionScope.user}">
	        <button class="add-to-cart-btn" onclick="addToCart()">Thêm vào giỏ hàng</button>
	    </c:when>
	    <c:otherwise>
	          <button class="add-to-cart-btn">
	        	<a href="${pageContext.request.contextPath}/login.jsp">Đăng nhập để thêm vào giỏ hàng</a>
	            
	           </button>
	        
	    </c:otherwise>
	</c:choose>
	
        <br><br>
          <a href="${pageContext.request.contextPath}/clientHome">← Tiếp tục xem danh sách</a>
    </div>

</div>

	<jsp:include page="_footer.jsp"></jsp:include>
	
	
	<script type="text/javascript">
		function increaseQty(btn) {
			const input = btn.previousElementSibling;
			let value = parseInt(input.value);
			const maxQty = ${book.quantityInStock};

			if (value < maxQty) {
				input.value = value + 1;
			}

		}

		function decreaseQty(btn) {
			const input = btn.nextElementSibling;
			let value = parseInt(input.value);
			if (value > 1) {
				input.value = value - 1;
			}
		}
		
		function addToCart() {
			const quantity = document.querySelector('.qty-input').value;
			const bookId = ${book.bookId};
			window.location.href = '${pageContext.request.contextPath}/cartBook/addToCart?bookId=' + bookId + '&quantityPurchased=' + quantity;
		}
		
		
	</script>
</body>
</html>