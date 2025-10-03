package controller;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;
import model.Order;
import untils.Constant;
import untils.MyUntils;

@WebServlet("/adminOrderList")
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao;

	@Override
	public void init() throws ServletException {
		orderDao = new OrderDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String servletPath = request.getServletPath();

		List<Order> orderList = new ArrayList<>();
		

		String listType = "";
		String action = request.getParameter("action");
		byte orderStatus = 1;
		
	    if (action == null || action.isEmpty()) {
	        action = Constant.WAITING_APPROVE_ACTION; 
	    }

		
		switch (action) {
		case Constant.WAITING_APPROVE_ACTION:
//			orderList = orderDao.getOrderList(Constant.WAITING_CONFIRM_ORDER_STATUS);
			  orderStatus = Constant.WAITING_CONFIRM_ORDER_STATUS;

			System.out.println("Tổng đơn hàng: " + orderList.size());
			listType = "CHỜ XÁC NHẬN";
			break;
		case Constant.DELIVERING_ACTION:
//			orderList = orderDao.getOrderList(Constant.DELIVERING_ORDER_STATUS);
			  orderStatus = Constant.DELIVERING_ORDER_STATUS;

			listType = "ĐANG CHỜ GIAO";
			break;
		case Constant.DELIVERED_ACTION:
//			orderList = orderDao.getOrderList(Constant.DELIVERED_ORDER_STATUS);
			 orderStatus = Constant.DELIVERED_ORDER_STATUS;

			listType = "ĐÃ GIAO";
			break;
		case Constant.REJECT_ACTION:
//			orderList = orderDao.getOrderList(Constant.REJECT_ORDER_STATUS);
			  orderStatus = Constant.REJECT_ORDER_STATUS;

			listType = "KHÁCH TRẢ LẠI";
			break;
		}
		
		orderList = orderDao.getOrderList(orderStatus);
		request.setAttribute("listType", listType);
		request.setAttribute(Constant.ORDER_LIST_OF_CUSTOMER, orderList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_list_order_view.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	List<String> errors = new ArrayList<>();
	        String orderIdStr = request.getParameter("orderId");
	        String confirmTypeStr = request.getParameter("confirmType");

	        int orderId = -1;
	        byte confirmType = -1;

	        try {
	            orderId = Integer.parseInt(orderIdStr);
	        } catch (NumberFormatException e) {
	            errors.add(Constant.ORDER_ID_INVALID_VALIDATE_MSG);
	        }

	        try {
	            confirmType = Byte.parseByte(confirmTypeStr);
	        } catch (NumberFormatException e) {
	            errors.add(Constant.VALUE_INVALID_VALIDATE_MSG);
	        }

	        if (errors.isEmpty()) {
	            boolean updateResult = false;

	            if (confirmType == Constant.DELIVERING_ORDER_STATUS) {
	                updateResult = orderDao.updateOrderNo(orderId, confirmType);
	            } else if (confirmType == Constant.DELIVERED_ORDER_STATUS ||
	                       confirmType == Constant.REJECT_ORDER_STATUS) {
	                updateResult = orderDao.updateOrder(orderId, confirmType);
	            }

	            if (updateResult) {
	                request.setAttribute("message", Constant.UPDATE_ORDER_SUCCESS);
	            } else {
	                errors.add(Constant.UPDATE_ORDER_FAIL);
	            }
	        }

	        if (!errors.isEmpty()) {
	            request.setAttribute("errors", String.join(", ", errors));
	        }

	        doGet(request, response);
	    }

	}

