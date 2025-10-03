<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 20px;
}

h3 {
	color: #333;
	margin-bottom: 10px;
}

table {
	border-collapse: collapse;
	width: 90%;
	margin: 0 auto 20px auto;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
}

th, td {
	padding: 10px 15px;
	text-align: left;
	border: 1px solid #ddd;
}

th {
	background-color: #f0f0f0;
	font-weight: bold;
}

tr:nth-child(even) {
	background-color: #fdfdfd;
}

tr:hover {
	background-color: #f1f1f1;
}

button {
	padding: 6px 12px;
	margin: 5px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}

.order-detail {
  margin-top: 10px;
  padding: 10px;
  background-color: #fffbe6;
  border: 1px solid #ffd966;
  border-radius: 5px;
}
</style>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>

	<div align="center">
		<h3>Xin chào: ${user.fullname}</h3>

		<table>
			<tr>
				<td><b>Tài khoản:</b>&nbsp;${user.username}</td>
				<td><b>Số di động:</b>&nbsp;${user.mobile}</td>
			</tr>
			<tr>
				<td><b>Email:</b>&nbsp;${user.email}</td>
				<td><b>Địa chỉ:</b>&nbsp;${user.address}</td>
			</tr>
		</table>

		<hr>

		<div align="center">
			<h3>DANH SÁCH ĐƠN HÀNG</h3>

			<table border="1">
				<tr>
					<th>Mã hóa đơn</th>
					<th>Ngày đặt mua</th>
					<th>Ngày xác nhận</th>
					<th>Địa chỉ nhận sách</th>
					<th>Phương thức thanh toán</th>
					<th>Trạng thái đơn hàng</th>
					<th>Thao tác</th>
				</tr>

				<c:forEach items="${orderListOfCustomer}" var="orderOfCustomer">
					<tr>
						<td>${orderOfCustomer.orderNo}</td>
						<td><fmt:formatDate value="${orderOfCustomer.orderDate}"
								pattern="dd-MM-yyyy HH:mm" /></td>
						<td><fmt:formatDate
								value="${orderOfCustomer.orderApproveDate}"
								pattern="dd-MM-yyyy HH:mm" /></td>
						<td>${orderOfCustomer.deliveryAddress}</td>
						<td>${orderOfCustomer.paymentModeDescription}</td>
						<td>${orderOfCustomer.orderStatusDescription}<c:if
								test="${Constant.WAITING_CONFIRM_ORDER_STATUS != orderOfCustomer.orderStatus}">
              &nbsp;-&nbsp;${orderOfCustomer.paymentStatusDescription}
            </c:if>
						</td>
						<td>
							<button
								onclick="document.getElementById('div${orderOfCustomer.orderId}').style.display='block';">Xem
								chi tiết</button>
							<button
								onclick="document.getElementById('div${orderOfCustomer.orderId}').style.display='none';">Ẩn</button>

							<!-- Chi tiết hóa đơn -->
							<div id="div${orderOfCustomer.orderId}"  class="order-detail"  style="display: none;">
								<h3>Các cuốn sách trong hóa đơn</h3>
								<table border="1">
									<tr style="background-color: yellow;">
										<th>Tiêu đề</th>
										<th>Tác giả</th>
										<th>Giá tiền</th>
										<th>Số lượng mua</th>
										<th>Tổng thành phần</th>
									</tr>

									<c:forEach items="${orderOfCustomer.orderBookList}"
										var="cartItem">
										<tr>
											<td>${cartItem.selectedBook.title}</td>
											<td>${cartItem.selectedBook.author}</td>
											<td><fmt:formatNumber type="number"
													maxFractionDigits="0"
													value="${cartItem.selectedBook.price}" /><sup>đ</sup></td>
											<td>${cartItem.quantity}</td>
											<td><fmt:formatNumber type="number"
													maxFractionDigits="0"
													value="${cartItem.selectedBook.price * cartItem.quantity}" />
												<sup>đ</sup></td>
										</tr>
									</c:forEach>
								</table>
								<br> Tổng số tiền: <b> <span> <fmt:formatNumber
											type="number" maxFractionDigits="0"
											value="${orderOfCustomer.totalCost}" />
								</span> <sup>đ</sup>
								</b>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>


		<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>