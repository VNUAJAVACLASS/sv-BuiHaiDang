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
.invoice-container {
  font-family: 'Segoe UI', sans-serif;
  background-color: #fefefe;
  padding: 30px;
  max-width: 1000px;
  margin: auto;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
  border-radius: 8px;
}

h3 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 30px;
}

th, td {
  padding: 12px 15px;
  border: 1px solid #ddd;
  text-align: left;
}

th {
  background-color: #f0f0f0;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #fafafa;
}

tr:hover {
  background-color: #f1f1f1;
}

img {
  border-radius: 4px;
  box-shadow: 0 0 5px rgba(0,0,0,0.1);
}

.total-box {
  text-align: right;
  font-size: 1.2em;
  margin-bottom: 20px;
}

.button-box {
  text-align: center;
}

.home-button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  text-decoration: none;
  font-weight: bold;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.home-button:hover {
  background-color: #218838;
}
</style>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
<%-- 
	<div align="center">
		<h3>CHI TIẾT HÓA ĐƠN</h3>
		<table border="1">
			<tr>
				<th align="left">Tài khoản:</th>
				<td>${user.username}</td>
				<c:if
					test="${Constant.WAITING_CONFIRM_ORDER_STATUS == orderOfCustomer.orderStatus}">
					<td rowspan="9"><img alt="Transfer Image"
						src="${orderOfCustomer.paymentImagePath}" width="150"></td>
				</c:if>
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
				<th align="left">Mã hóa đơn:</th>
				<td>${orderOfCustomer.orderNo}</td>
			</tr>
			<tr>
				<th align="left">Ngày đặt mua:</th>
				<td><fmt:formatDate value="${orderOfCustomer.orderDate}"
						pattern="dd-MM-yyyy HH:mm" /></td>
			</tr>
			<tr>
				<th align="left">Ngày xác nhận đơn:</th>
				<td><fmt:formatDate value="${orderOfCustomer.orderApproveDate}"
						pattern="dd-MM-yyyy HH:mm" /></td>
			</tr>
			<tr>
				<th align="left">Địa chỉ nhận sách:</th>
				<td>${orderOfCustomer.deliveryAddress}</td>
			</tr>
			<tr>
				<th align="left">Phương thức thanh toán:</th>
				<td>${orderOfCustomer.paymentModeDescription}</td>
			</tr>
			<tr>
				<th align="left">Trạng thái đơn hàng:</th>
				<td>${orderOfCustomer.orderStatusDescription} <c:if
						test="${Constant.WAITING_CONFIRM_ORDER_STATUS != orderOfCustomer.orderStatus}">
          &nbsp;-&nbsp;${orderOfCustomer.paymentStatusDescription}
        </c:if>
				</td>
			</tr>
		</table>
	</div>

	<div align="center">
		<h3>Các cuốn sách trong hóa đơn</h3>
		<table border="1">
			<tr>
				<th>Tiêu đề</th>
				<th>Tác giả</th>
				<th>Giá tiền</th>
				<th>Số lượng mua</th>
				<th>Tổng thành phần</th>
			</tr>
			<c:forEach items="${cartOfCustomer.cartItemList}" var="entry">
				<tr>
					<td>${entry.value.selectedBook.title}</td>
					<td>${entry.value.selectedBook.author}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0"
							value="${entry.value.selectedBook.price}" /> <sup>đ</sup></td>
					<td>${entry.value.quantity}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0"
							value="${entry.value.selectedBook.price * entry.value.quantity}" />
						<sup>đ</sup></td>
				</tr>
			</c:forEach>
		</table>
		<br> Tổng số tiền: <b> <span id="total"> <fmt:formatNumber
					type="number" maxFractionDigits="0"
					value="${cartOfCustomer.totalCost}" />
		</span> <sup>đ</sup>
		</b>
	</div>
 --%>


<div class="invoice-container">
  <h3>CHI TIẾT HÓA ĐƠN</h3>
  <table class="info-table">
    <tr>
      <th>Tài khoản:</th>
      <td>${user.username}</td>
      <c:if test="${Constant.WAITING_CONFIRM_ORDER_STATUS == orderOfCustomer.orderStatus}">
        <td rowspan="9">
          <img alt="Transfer Image" src="${orderOfCustomer.paymentImagePath}" width="150">
        </td>
      </c:if>
    </tr>
    <tr><th>Họ tên:</th><td>${user.fullname}</td></tr>
    <tr><th>Số di động:</th><td>${user.mobile}</td></tr>
    <tr><th>Mã hóa đơn:</th><td>${orderOfCustomer.orderNo}</td></tr>
    <tr><th>Ngày đặt mua:</th>
      <td><fmt:formatDate value="${orderOfCustomer.orderDate}" pattern="dd-MM-yyyy HH:mm" /></td>
    </tr>
    <tr><th>Ngày xác nhận đơn:</th>
      <td><fmt:formatDate value="${orderOfCustomer.orderApproveDate}" pattern="dd-MM-yyyy HH:mm" /></td>
    </tr>
    <tr><th>Địa chỉ nhận sách:</th><td>${orderOfCustomer.deliveryAddress}</td></tr>
    <tr><th>Phương thức thanh toán:</th><td>${orderOfCustomer.paymentModeDescription}</td></tr>
    <tr>
      <th>Trạng thái đơn hàng:</th>
      <td>
        ${orderOfCustomer.orderStatusDescription}
        <c:if test="${Constant.WAITING_CONFIRM_ORDER_STATUS != orderOfCustomer.orderStatus}">
          &nbsp;-&nbsp;${orderOfCustomer.paymentStatusDescription}
        </c:if>
      </td>
    </tr>
  </table>

  <h3>Các cuốn sách trong hóa đơn</h3>
  <table class="book-table">
    <tr>
      <th>Tiêu đề</th>
      <th>Tác giả</th>
      <th>Giá tiền</th>
      <th>Số lượng mua</th>
      <th>Tổng thành phần</th>
    </tr>
    <c:forEach items="${cartOfCustomer.cartItemList}" var="entry">
      <tr>
        <td>${entry.value.selectedBook.title}</td>
        <td>${entry.value.selectedBook.author}</td>
        <td><fmt:formatNumber value="${entry.value.selectedBook.price}" type="number" maxFractionDigits="0" /><sup>đ</sup></td>
        <td>${entry.value.quantity}</td>
        <td><fmt:formatNumber value="${entry.value.selectedBook.price * entry.value.quantity}" type="number" maxFractionDigits="0" /><sup>đ</sup></td>
      </tr>
    </c:forEach>
  </table>

  <div class="total-box">
    Tổng số tiền: <b>
      <span><fmt:formatNumber value="${cartOfCustomer.totalCost}" type="number" maxFractionDigits="0" /></span><sup>đ</sup>
    </b>
  </div>

  <div class="button-box">
    <a href="${pageContext.request.contextPath}/clientHome" class="home-button">🏠 Quay về trang chủ</a>
  </div>
</div>
	<jsp:include page="_footer.jsp"></jsp:include>
	
	
	
	<script>
  // Ngăn người dùng reload bằng F5 hoặc Ctrl+R
  window.addEventListener("keydown", function (e) {
    if ((e.key === "F5") || (e.ctrlKey && e.key === "r")) {
      e.preventDefault();
    }
  });

  // Ngăn reload bằng chuột phải → Reload
  window.addEventListener("beforeunload", function (e) {
    e.preventDefault();
    e.returnValue = "";
  });
  
  history.pushState(null, "", location.href);
  window.addEventListener("popstate", function () {
    history.pushState(null, "", location.href);
  });

</script>
</body>
</html>