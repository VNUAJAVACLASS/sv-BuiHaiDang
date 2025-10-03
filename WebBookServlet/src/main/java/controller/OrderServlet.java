package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.OrderDao;
import model.Cart;
import model.CartItem;
import model.Order;
import model.User;
import untils.Constant;
import untils.MyUntils;

@WebServlet("/order")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 20) // 20MB

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDao orderDao;

	@Override
	public void init() throws ServletException {
		super.init();
		orderDao = new OrderDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		   RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/cart_view.jsp");
		    dispatcher.forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 List<String> errors = new ArrayList<>();

		    // Lấy dữ liệu từ form
		    String deliveryAddress = request.getParameter("deliveryAddress");
		    String paymentMode = request.getParameter("paymentMode");
		    Part filePart = request.getPart("file");
		    HttpSession session = request.getSession();

		    // Kiểm tra dữ liệu đầu vào
		    validateOrderForm(deliveryAddress, paymentMode, filePart, errors);

		    if (!errors.isEmpty()) {
		        request.setAttribute("errors", String.join(", ", errors));
		        RequestDispatcher dispatcher = request.getServletContext()
		                .getRequestDispatcher("/cart_view.jsp");
		        dispatcher.forward(request, response);
		        return;
		    }

		    // Tạo đối tượng Order từ dữ liệu form
		    Order order = createOrder(deliveryAddress, paymentMode, filePart, session);
		    String forwardPage = null;

		    // Kiểm tra tồn kho và xử lý đơn hàng
		    if (orderDao.checkAndUpdateAvaiableBookOfOrder(order)) {
		        boolean insertResult = false;
				insertResult = orderDao.insertOrder(order);

		        if (insertResult) {
		            // Đặt hàng thành công
		            request.setAttribute(Constant.CART_OF_CUSTOMER, MyUntils.getCartOfCustomer(session));
		            request.setAttribute(Constant.ORDER_OF_CUSTOMER, order);
		            MyUntils.deleteCart(session); 
		            forwardPage = "/detail_order_view.jsp";
		        } else {
		            // Lỗi khi ghi đơn hàng vào database
		            request.setAttribute("errors", Constant.ADD_ORDER_ERROR_MSG);
		            forwardPage = "/cart_view.jsp";
		        }
		    } else {
		        // Không đủ hàng trong kho
		        request.setAttribute("errors", Constant.NOT_ENOUGHT_ORDER_ERROR_MSG);
		        MyUntils.updateCartOfCustomer(session, convertListToMap(order.getOrderBookList()));
		        forwardPage = "/cart_view.jsp";
		    }

		    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(forwardPage);
		    dispatcher.forward(request, response);
	}
	
	private Map<Integer, CartItem> convertListToMap(List<CartItem> orderBookList) {
		Map<Integer, CartItem> cartItemList = new HashMap<>();
		for (CartItem cartItem : orderBookList) {
			cartItemList.put(cartItem.getSelectedBook().getBookId(), cartItem);
		}
		return cartItemList;
	}

	private void validateOrderForm(String deliveryAddress, String paymentMode, Part filePart, List<String> errors) {
		if (deliveryAddress == null || deliveryAddress.trim().isEmpty()) {
			errors.add(Constant.DELIVERY_ADDRESS_EMPTY_VALIDATE_MSG);
		}

		if (Constant.TRANSFER_PAYMENT_MODE.equals(paymentMode)) {
			if (filePart == null || filePart.getSize() <= 0) {
				errors.add(Constant.TRANSFER_IMAGE_EMPTY_VALIDATE_MSG);
			}
		}
	}

	private Order createOrder(String deliveryAddress, String paymentMode, Part filePart, HttpSession session)
			throws IOException {

		Order order = new Order();
		Date now = new Date(System.currentTimeMillis());
		Cart cartOfCustomer = MyUntils.getCartOfCustomer(session);
		String customUsername = MyUntils.getLoginedUser(session).getUsername();

		User customer = new User();
		customer.setUsername(customUsername);
		order.setCustomer(customer);
		order.setDeliveryAddress(deliveryAddress);
		order.setPaymentMode(paymentMode);
		order.setOrderDate(now);
		order.setStatusDate(now);
		order.setTotalCost(cartOfCustomer.getTotalCost());
		order.setOrderBookList(new ArrayList<>(cartOfCustomer.getCartItemList().values()));

		if (Constant.CASH_PAYMENT_MODE.equals(paymentMode)) {
			order.setOrderStatus(Constant.DELIVERING_ORDER_STATUS);
			order.setOrderApproveDate(now);
			order.setPaymentStatus(false);
		} else if (Constant.TRANSFER_PAYMENT_MODE.equals(paymentMode)) {
			// Lưu ảnh chuyển khoản
			String fileName = customUsername + "_" + MyUntils.getTimeLabel() + MyUntils.extractFileExtension(filePart);
			String appPath = getServletContext().getRealPath("");
			filePart.write(MyUntils.getFolderUpload(appPath, "transfer-img-upload").getAbsolutePath() + File.separator
					+ fileName);

			order.setOrderStatus(Constant.WAITING_CONFIRM_ORDER_STATUS);
			order.setPaymentStatus(false);
			order.setPaymentImagePath("transfer-img-upload" + File.separator + fileName);
		}

		return order;
	}
	

}
