<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="untils.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bookstore_style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<title>Thông tin tài khoản/đơn hàng</title>
<link rel="stylesheet" href="css/list.css">
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	%>

	<div class="container_body">
		<div class="sidebar">
			<div class="profile">
				<img src="img/people.png" alt="avatar">
				<div class="profile_info">
					<h4>${sessionScope.username}</h4>
					<p>Chào mừng bạn trở lại</p>
				</div>
			</div>
			<button class="pos_btn">POS Bán Hàng</button>
			<ul class="menu_list">
				<li><a href="${pageContext.request.contextPath}/adminHome">📚
						Quản lí sản phẩm</a></li>
				<li><a href="${pageContext.request.contextPath}/adminUser">👤
						Quản lí tài khoản</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/adminOrderList">📦
						Quản lí đơn hàng</a></li>
			</ul>
		</div>

		<div class="content">
			<div class="content_header">
				<h3>Danh Đơn Hàng</h3>
				<button class="logout">
					<a href="logout.jsp"><i class="bi bi-box-arrow-right"></i></a>
				</button>
			</div>

			<div class="action_buttons">
				<a class="btn green" href="adminOrderList?action=waiting">Đơn
					chờ xác nhận</a> <a class="btn blue"
					href="adminOrderList?action=delivering">Đơn đang giao</a> <a
					class="btn gray" href="adminOrderList?action=delivered">Đơn đã
					giao</a> <a class="btn yellow" href="adminOrderList?action=reject">Đơn
					hủy</a>

			</div>

			<div align="center">
				<h3>DANH SÁCH ĐƠN HÀNG ${listType}</h3>

				<form id="adminOrderForm" method="POST" action="">
					<input type="hidden" name="orderId" id="orderIdOfAction" /> <input
						type="hidden" name="confirmType" id="confirmTypeOfAction" />
				</form>

				<p style="color: red;">${errors}</p>
				<p style="color: blue;">${message}</p>

				<table border="1">
					<tr>
						<th>Mã hóa đơn</th>
						<th>Tên khách</th>
						<th>Số điện thoại</th>
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
							<td>${orderOfCustomer.customer.fullname}</td>
							<td>${orderOfCustomer.customer.mobile}</td>
							<td><fmt:formatDate value="${orderOfCustomer.orderDate}"
									pattern="dd-MM-yyyy HH:mm" /></td>
							<td><fmt:formatDate
									value="${orderOfCustomer.orderApproveDate}"
									pattern="dd-MM-yyyy HH:mm" /></td>
							<td>${orderOfCustomer.deliveryAddress}</td>
							<td>${orderOfCustomer.paymentModeDescription}<br /> <c:if
									test="${fn:contains(orderOfCustomer.paymentMode, Constant.TRANSFER_PAYMENT_MODE)}">
									<button
										onclick="document.getElementById('divImg${orderOfCustomer.orderId}').style.display='block';">Xem
										ảnh</button>
									<button
										onclick="document.getElementById('divImg${orderOfCustomer.orderId}').style.display='none';">Ẩn</button>
									<br />
									<div id="divImg${orderOfCustomer.orderId}"
										style="display: none; padding-top: 5px;">
										<img alt="Transfer Image"
											src="${pageContext.request.contextPath}/${orderOfCustomer.paymentImagePath}"
											width="150" />
									</div>
								</c:if>
							</td>
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


								<%
								byte WAITING = untils.Constant.WAITING_CONFIRM_ORDER_STATUS;
								byte DELIVERING = untils.Constant.DELIVERING_ORDER_STATUS;
								byte DELIVERED = untils.Constant.DELIVERED_ORDER_STATUS;
								byte REJECT = untils.Constant.REJECT_ORDER_STATUS;

								String ACTION_WAITING = "waiting";
								String ACTION_DELIVERING = "delivering";
								%>
								<div id="div${orderOfCustomer.orderId}" style="display: none;">
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
														value="${cartItem.selectedBook.price * cartItem.quantity}" /><sup>đ</sup></td>
											</tr>
										</c:forEach>
									</table>
									<br /> Tổng số tiền: <b><span><fmt:formatNumber
												type="number" maxFractionDigits="0"
												value="${orderOfCustomer.totalCost}" /></span><sup>đ</sup></b>

									<c:if
										test="${Constant.WAITING_CONFIRM_ORDER_STATUS == orderOfCustomer.orderStatus}">  &nbsp;&nbsp;&nbsp;&nbsp;
                            <button
											onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId}, ${Constant.DELIVERING_ORDER_STATUS}, '${Constant.WAITING_APPROVE_ACTION}');">Xác
											nhận đơn</button>
									</c:if>

									<c:if
										test="${Constant.DELIVERING_ORDER_STATUS == orderOfCustomer.orderStatus}">
										<br />
										<br />
										<button
											onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId}, ${Constant.DELIVERED_ORDER_STATUS}, '${Constant.DELIVERING_ACTION}');">
											Xác nhận đã giao hàng</button>  &nbsp;&nbsp;
                            <button
											onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId}, ${Constant.REJECT_ORDER_STATUS}, '${Constant.DELIVERING_ACTION}');">
											Xác nhận khách trả hàng</button>
									</c:if>
								</div>

								<div id="div${orderOfCustomer.orderId}" style="display: none;">
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
														value="${cartItem.selectedBook.price * cartItem.quantity}" /><sup>đ</sup></td>
											</tr>
										</c:forEach>
									</table>
									<br /> Tổng số tiền: <b><span><fmt:formatNumber
												type="number" maxFractionDigits="0"
												value="${orderOfCustomer.totalCost}" /></span><sup>đ</sup></b>

									<c:if test="${orderOfCustomer.orderStatus == WAITING}">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button
											onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId}, <%= DELIVERING %>, '<%= ACTION_WAITING %>');">
											Xác nhận đơn</button>
									</c:if>

									<c:if test="${orderOfCustomer.orderStatus == DELIVERING}">
										<br />
										<br />
										<button
											onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId}, <%= DELIVERED %>, '<%= ACTION_DELIVERING %>');">
											Xác nhận đã giao hàng</button>
        &nbsp;&nbsp;
        <button
											onclick="onClickAdminOrderConfirm(${orderOfCustomer.orderId}, <%= REJECT %>, '<%= ACTION_DELIVERING %>');">
											Xác nhận khách trả hàng</button>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>


	<script>
	function onClickAdminOrderConfirm(orderId, status, action) {
	    document.getElementById("orderIdOfAction").value = orderId;
	    document.getElementById("confirmTypeOfAction").value = status;
	    document.getElementById("adminOrderForm").action = "adminOrderList?action=" + action;
	    document.getElementById("adminOrderForm").submit();
	}
</script>
</body>
</html>
