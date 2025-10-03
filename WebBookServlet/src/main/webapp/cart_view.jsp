<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cart_view.css">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>

	<div class="cart-wrapper center-content">
		<h3 class="cart-title">Các cuốn sách có trong giỏ hàng</h3>

		<form id="removedBookFromCartForm" method="POST"
			action="removeFromCart">
			<input type="hidden" name="bookId" id="removedBookFromCart" />
		</form>

		<table class="cart-table">
			<thead>
				<tr>
					<th>Tiêu đề</th>
					<th>Tác giả</th>
					<th>Giá tiền</th>
					<th>Số lượng mua</th>
					<th>Tổng thành phần</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<!-- Nếu có sách hiển thị  -->
				<c:if test="${not empty cartOfCustomer.cartItemList}">
					<c:forEach items="${cartOfCustomer.cartItemList}" var="entry">
						<tr>
							<td>${entry.value.selectedBook.title}</td>
							<td>${entry.value.selectedBook.author}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="0"
									value="${entry.value.selectedBook.price}" /> <sup>đ</sup></td>
							<td>
								<div class="quantity-control">
									<button type="button"
										onclick="minusValueAndUpdateCart('quantity${entry.value.selectedBook.bookId}')">
										<i class="bi bi-dash-circle"></i>
									</button>

									<input type="text" value="${entry.value.quantity}"
										id="quantity${entry.value.selectedBook.bookId}"
										onchange="validateValueAndUpdateCart(this, ${entry.value.selectedBook.quantityInStock},
                                ${entry.value.selectedBook.bookId}, ${entry.value.selectedBook.price});">

									<button type="button"
										onclick="plusValueAndUpdateCart('quantity${entry.value.selectedBook.bookId}', ${entry.value.selectedBook.quantityInStock})">
										<i class="bi bi-plus-circle"></i>
									</button>
								</div>
							</td>
							<td><span id="subtotal${entry.value.selectedBook.bookId}">
									<fmt:formatNumber type="number" maxFractionDigits="0"
										value="${entry.value.selectedBook.price * entry.value.quantity}" />
							</span> <sup>đ</sup></td>
							<td>
								<button type="button" class="remove-btn"
									onclick="onClickRemoveBook('${entry.value.selectedBook.title}',${entry.value.selectedBook.bookId});">
									Loại khỏi giỏ hàng</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>

				<!-- Nếu không có hiển thị rỗng -->
				<c:if test="${empty cartOfCustomer.cartItemList}">
					<tr>
						<td colspan="6" style="text-align: center; color: gray;">Giỏ
							hàng của bạn đang trống.</td>
					</tr>
				</c:if>

			</tbody>
		</table>

		<!-- Footer giỏ hàng -->
		<div class="cart-footer">
			<div class="footer-row">
				<a href="${pageContext.request.contextPath}/clientHome"
					class="continue-link">← Tiếp tục chọn sách</a> <b
					class="total-amount">Tổng tiền: <span id="total">0</span> <sup>đ</sup></b>
			</div>
		</div>

		<!-- Form thanh toán -->
		<div class="checkout-section" align="center">
			<h3>THANH TOÁN & ĐẶT MUA</h3>
			<p style="color: red;">${errors}</p>

			<form method="POST" enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/order">
				<table border="1" class="checkout-table">
					<tr>
						<th align="left">Tài khoản:</th>
						<td>${user.username}</td>
					</tr>
					<tr>
						<th align="left">Họ tên:</th>
						<td>${user.fullname}</td>
					</tr>
					<tr>
						<th align="left">Số di động:</th>
						<td>${user.mobile}</td>
					</tr>
					<tr>
						<th align="left">Địa chỉ đăng ký:</th>
						<td>${user.address}</td>
					</tr>
					<tr>
						<th align="left">Nhập địa chỉ nhận sách:</th>
						<td><textarea name="deliveryAddress" required rows="2" cols="50" class="address-input"></textarea></td>
					</tr>
					<tr>
						<th align="left">Phương thức thanh toán:</th>
						<td><label> <input type="radio" name="paymentMode"
								value="cash" checked
								onclick="document.getElementById('uploadDiv').style.display='none';">
								Thanh toán tiền mặt khi nhận hàng
						</label> <br> <label> <input type="radio" name="paymentMode"
								value="transfer"
								onclick="document.getElementById('uploadDiv').style.display='block';">
								Chuyển khoản ngân hàng
						</label>

							<div id="uploadDiv"
								style="padding-left: 30px; display: none; margin-top: 10px;">
								<p>Quý khách hãy chuyển khoản tới tài khoản dưới đây với nội
									dung theo một trong 2 mẫu:</p>
								<ul>
									<li>Mẫu 1: (Số điện thoại đăng ký tài khoản) - thanh toán
										đơn hàng</li>
									<li>Mẫu 2: (Tên tài khoản đăng ký) - thanh toán đơn hàng</li>
								</ul>
								<p>Sau đó chụp ảnh màn hình kết quả chuyển khoản hoặc phiếu
									xác nhận từ cây ATM.</p>
								<p>
									<strong>Tên chủ tài khoản:</strong> ..............<br> <strong>Số
										tài khoản:</strong> ..............<br> <strong>Chi nhánh
										ngân hàng:</strong> ..............
								</p>
								<p>
									<strong>Chọn ảnh kết quả chuyển khoản:</strong>
								</p>
								<img id="bookImage" width="150" alt="Ảnh chuyển khoản"><br>
								<input type="file" name="file" accept="image/*"
									onchange="loadImage(event)">
							</div></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Đặt mua"></td>
					</tr>
				</table>
			</form>
		</div>


		<jsp:include page="_footer.jsp"></jsp:include>

		<script type="text/javascript">
	
	// Xác nhận xóa sách khỏi giỏ hàng
	function onClickRemoveBook(bookTitle, bookId) {
	    let c = confirm('Bạn chắc chắn muốn xóa cuốn sách "' + bookTitle + '" khỏi giỏ hàng?');
	    if (c) {
	        document.getElementById("removedBookFromCart").value = bookId;
	        document.getElementById("removedBookFromCartForm").submit();
	    }
	}

	// Gửi yêu cầu AJAX để cập nhật số lượng sách trong giỏ
	var request;

	function updateQuantityOfCartItem(newQuantity, bookId) {
	    var url = 'addToCart?bookId=' + bookId + '&quantityPurchased=' + newQuantity;

	    if (window.XMLHttpRequest) {
	        request = new XMLHttpRequest();
	    } else if (window.ActiveXObject) {
	        request = new ActiveXObject("Microsoft.XMLHTTP");
	    }

	    try {
	        request.onreadystatechange = getInfo;
	        request.open("GET", url, true);
	        request.send();
	    } catch (e) {
	        alert("Không thể kết nối đến máy chủ");
	    }
	}

	// Xử lý phản hồi từ server (nếu cần)
	function getInfo() {
	    if (request.readyState === 4) {
	        var val = request.responseText;
	        // Có thể xử lý thêm nếu cần
	    }
	}

	// Kiểm tra và cập nhật số lượng khi người dùng thay đổi
	function validateValueAndUpdateCart(element, maxQuantity, bookId, price) {
	    var newQuantity = parseInt(element.value);

	    if (newQuantity > maxQuantity) {
	        alert('Giá trị không được vượt quá: ' + maxQuantity);
	    } else if (newQuantity > 0) {
	        // Gửi AJAX để cập nhật giỏ hàng
	        updateQuantityOfCartItem(newQuantity, bookId);

	        // Cập nhật thành phần tiền
	        document.getElementById("subtotal" + bookId).innerText = toComma(newQuantity * price);

	        // Cập nhật tổng tiền
	        let subtotalList = document.querySelectorAll('[id^="subtotal"]');
	        let total = 0;
	        for (let i = 0; i < subtotalList.length; i++) {
	            total += parseInt(subtotalList[i].innerText.replace(/,/g, ""));
	        }
	        document.getElementById("total").innerText = toComma(total);
	    }
	}

	// Định dạng số tiền: phân cách hàng nghìn bằng dấu phẩy
	function toComma(n) {
	    return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
 
	// Giảm số lượng khi bấm nút trừ
	function minusValueAndUpdateCart(elementId) {
	    var quantity = parseInt(document.getElementById(elementId).value);
	    if (quantity - 1 >= 1) {
	        document.getElementById(elementId).value = quantity - 1;
	        document.getElementById(elementId).onchange();
	    }
	}

	// Tăng số lượng khi bấm nút cộng
	function plusValueAndUpdateCart(elementId, maxQuantity) {
	    var quantity = parseInt(document.getElementById(elementId).value);
	    if (quantity + 1 <= maxQuantity) {
	        document.getElementById(elementId).value = quantity + 1;
	        document.getElementById(elementId).onchange();
	    } else {
	        alert('Giá trị không được vượt quá: ' + maxQuantity);
	    }
	}
	
	function updateTotal() {
	    let subtotalList = document.querySelectorAll('[id^="subtotal"]');
	    let total = 0;
	    for (let i = 0; i < subtotalList.length; i++) {
	        let raw = subtotalList[i].innerText.replace(/,/g, "").replace(/\./g, "");
	        let value = parseInt(raw);
	        if (!isNaN(value)) {
	            total += value;
	        }
	    }
	    document.getElementById("total").innerText = toComma(total);
	}
	
	window.onload = function () {
	    updateTotal();
	};
	</script>
</body>
</html>