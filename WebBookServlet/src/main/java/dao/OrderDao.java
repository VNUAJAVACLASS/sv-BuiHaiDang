package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Book;
import model.CartItem;
import model.Order;
import model.User;
import untils.Constant;
import untils.MyUntils;

public class OrderDao {
	private Connection jdbcConnection;
	private Statement statement;
	private PreparedStatement preStatement;
	private ResultSet resultSet;

	public OrderDao() {}

	public List<Order> getOrderList(byte orderStatus) {
		Map<Integer, Order> orderMap = new HashMap<>();
		String sql = "SELECT ord.*, ordb.quantity, ordb.price, b.*, u.* " + "FROM tblorder ord "
				+ "INNER JOIN tblorder_book ordb ON ord.order_id = ordb.order_id "
				+ "INNER JOIN book b ON ordb.book_id = b.book_id "
				+ "INNER JOIN tbluser u ON ord.customer_username = u.user_name " + "WHERE ord.order_status = ? "
				+ "ORDER BY ord.status_date DESC, ord.order_date DESC";

		try {
			jdbcConnection = DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setByte(1, orderStatus);
			resultSet = preStatement.executeQuery();

			while (resultSet.next()) {
				int orderId = resultSet.getInt("order_id");

				if (!orderMap.containsKey(orderId)) {
					// Tạo mới đối tượng Order nếu chưa có
					Order order = new Order();
					fillOrderFromResultSet(resultSet, order);

					List<CartItem> cartItems = new ArrayList<>();
					Book book = new Book();
					fillBookFromResultSet(resultSet, book);

					CartItem item = new CartItem(book, resultSet.getInt("ordb.quantity"));
					cartItems.add(item);
					order.setOrderBookList(cartItems);

					orderMap.put(orderId, order);
				} else {
					// Nếu đã có đơn hàng, thêm sách vào danh sách
					Order existingOrder = orderMap.get(orderId);
					List<CartItem> cartItems = existingOrder.getOrderBookList();

					Book book = new Book();
					fillBookFromResultSet(resultSet, book);

					CartItem item = new CartItem(book, resultSet.getInt("ordb.quantity"));
					cartItems.add(item);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preStatement, jdbcConnection);
		}

		List<Order> orderList = new ArrayList<>(orderMap.values());
		Collections.sort(orderList); // Sắp xếp nếu cần
		return orderList;
	}


	public void fillOrderFromResultSet(ResultSet rs, Order order) {
		try {
			order.setOrderNo(rs.getString("order_no"));
			order.setOrderId(rs.getInt("order_id"));
			order.setOrderStatus(rs.getByte("order_status"));
			order.setPaymentStatus(rs.getBoolean("payment_status"));
			order.setOrderDate(rs.getTimestamp("order_date"));
			order.setOrderApproveDate(rs.getTimestamp("order_approve_date"));
			order.setStatusDate(rs.getTimestamp("status_date"));
			order.setDeliveryAddress(rs.getString("delivery_address"));
			order.setPaymentMode(rs.getString("payment_mode"));



			User customer = new User();
			customer.setUsername(rs.getString("user_name"));
			customer.setFullname(rs.getString("fullname"));
			customer.setEmail(rs.getString("email"));
			customer.setMobile(rs.getString("mobile"));
			customer.setAddress(rs.getString("address"));
			customer.setRole(rs.getByte("role"));

			order.setCustomer(customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fillBookFromResultSet(ResultSet rs, Book book) {

		try {
			book.setBookId(rs.getInt("book_id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			book.setPrice(rs.getFloat("price"));
			book.setQuantityInStock(rs.getInt("quantity_in_stock"));
			book.setDetail(rs.getString("detail"));
			book.setImagePath(rs.getString("image_path"));
			Timestamp timestamp = rs.getTimestamp("create_date");
			if (timestamp != null) {
				book.setCreateDate(timestamp.toLocalDateTime());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean updateOrder(int orderId, byte orderStatus) {
		boolean success = false;
		String sql = "UPDATE tblorder SET order_status = ?, status_date = ?, payment_status = ? WHERE order_id = ?";

		Date now = new Date(System.currentTimeMillis());
		try {
			jdbcConnection = DBConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setByte(1, orderStatus);
			preStatement.setString(2, MyUntils.convertDateToString(now));
			preStatement.setBoolean(3, (orderStatus == Constant.DELIVERED_ORDER_STATUS)); // chỉ đánh dấu đã thanh toán
			// nếu đã giao
			preStatement.setInt(4, orderId);

			success = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preStatement, jdbcConnection);
		}

		return success;
	}

	public boolean updateOrderNo(int orderId, byte orderStatus) {
		boolean success = false;
		String sql = "UPDATE tblorder SET order_no = ?, order_approve_date = ?, order_status = ?, status_date = ?, payment_status = ? WHERE order_id = ?";

		Date now = new Date(System.currentTimeMillis());
		String orderNo = MyUntils.createOrderNo(orderId); // Tạo mã đơn hàng

		try {
			jdbcConnection = DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			preStatement = jdbcConnection.prepareStatement(sql);
			preStatement.setString(1, orderNo);
			preStatement.setString(2, MyUntils.convertDateToString(now));
			preStatement.setByte(3, orderStatus);
			preStatement.setString(4, MyUntils.convertDateToString(now));
			preStatement.setBoolean(5, true); // đã thanh toán
			preStatement.setInt(6, orderId);

			success = preStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preStatement, jdbcConnection);
		}

		return success;
	}

	// Kiểm tra và cập nhật số lượng sách trong kho
	public boolean checkAndUpdateAvaiableBookOfOrder(Order order) {
		boolean checkAvaiable = true;
		String sql;
		List<CartItem> orderBookList = order.getOrderBookList();

		try {
			jdbcConnection = DBConnection.getConnection();

			for (CartItem cartItem : orderBookList) {
				sql = "SELECT quantity_in_stock FROM book WHERE book_id = ?";
				preStatement = jdbcConnection.prepareStatement(sql);
				Book selectedBook = cartItem.getSelectedBook();
				preStatement.setInt(1, selectedBook.getBookId());

				resultSet = preStatement.executeQuery();
				if (resultSet.next()) {
					int presentQuantityInStock = resultSet.getInt("quantity_in_stock");
					if (presentQuantityInStock < cartItem.getQuantity()) {
						checkAvaiable = false;
						selectedBook.setQuantityInStock(presentQuantityInStock);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preStatement, jdbcConnection);
		}

		return checkAvaiable;
	}

	// Thêm đơn hàng vào cơ sở dữ liệu
	public boolean insertOrder(Order order)  {
		boolean insertResult = false;
		int orderId = -1;
		String orderNo = null;

		String sql1 = "INSERT INTO tblorder (customer_username, payment_mode, order_status, total_cost, payment_img, payment_status, delivery_address, order_approve_date, status_date, order_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "SELECT max(order_id) FROM tblorder";
		String sql3 = "UPDATE tblorder SET order_no = ? WHERE order_id = ?";
		String sql4 = "INSERT INTO tblorder_book (book_id, order_id, quantity, price) VALUES (?, ?, ?, ?)";
		String sql5 = "UPDATE book SET quantity_in_stock = ? WHERE book_id = ?";

		try {
			jdbcConnection = DBConnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jdbcConnection.setAutoCommit(false);

			// Thêm hóa đơn
			preStatement = jdbcConnection.prepareStatement(sql1);
			preStatement.setString(1, order.getCustomer().getUsername());
			preStatement.setString(2, order.getPaymentMode());
			preStatement.setByte(3, order.getOrderStatus());
			preStatement.setFloat(4, order.getTotalCost());
			preStatement.setString(5, order.getPaymentImagePath());
			preStatement.setBoolean(6, order.isPaymentStatus());
			preStatement.setString(7, order.getDeliveryAddress());

//			if (order.getOrderApproveDate() != null) {
//				preStatement.setString(8, MyUntils.convertDateToString(order.getOrderApproveDate()));
//			} else {
//				preStatement.setString(8, null);
//			}

			if (order.getOrderApproveDate() != null) {
				preStatement.setTimestamp(8, new Timestamp(order.getOrderApproveDate().getTime()));
			} else {
				preStatement.setNull(8, Types.TIMESTAMP);
			}

			preStatement.setString(9, MyUntils.convertDateToString(order.getStatusDate()));
			preStatement.setString(10, MyUntils.convertDateToString(order.getOrderDate()));
			insertResult = preStatement.executeUpdate() > 0;

			// Lấy order_id vừa tạo
			statement = jdbcConnection.createStatement();
			resultSet = statement.executeQuery(sql2);
			if (resultSet.next()) {
				orderId = resultSet.getInt(1);

				// Nếu thanh toán tiền mặt, tạo orderNo
				if (Constant.CASH_PAYMENT_MODE.equals(order.getPaymentMode())) {
					preStatement = jdbcConnection.prepareStatement(sql3);
					orderNo = MyUntils.createOrderNo(orderId);
					preStatement.setString(1, orderNo);
					preStatement.setInt(2, orderId);
					insertResult = preStatement.executeUpdate() > 0;
					if (!insertResult)
						throw new SQLException();
				}

				// Thêm sách vào tblorder_book và cập nhật kho
				List<CartItem> orderBookList = order.getOrderBookList();
				for (CartItem cartItem : orderBookList) {
					// Thêm sách
					preStatement = jdbcConnection.prepareStatement(sql4);
					preStatement.setInt(1, cartItem.getSelectedBook().getBookId());
					preStatement.setInt(2, orderId);
					preStatement.setInt(3, cartItem.getQuantity());
					preStatement.setFloat(4, cartItem.getSelectedBook().getPrice());
					insertResult = preStatement.executeUpdate() > 0;
					if (!insertResult)
						throw new SQLException();

					// Cập nhật kho
					preStatement = jdbcConnection.prepareStatement(sql5);
					int newQuantity = (int) (cartItem.getSelectedBook().getQuantityInStock() - cartItem.getQuantity());
					preStatement.setInt(1, newQuantity);
					preStatement.setInt(2, cartItem.getSelectedBook().getBookId());
					insertResult = preStatement.executeUpdate() > 0;
					if (!insertResult)
						throw new SQLException();
				}

				jdbcConnection.commit();
			}
		} catch (SQLException e) {
			try {
				jdbcConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			close(resultSet, preStatement, jdbcConnection);
		}

		// Gán lại thông tin đơn hàng nếu thành công
		if (insertResult) {
			order.setOrderId(orderId);
			order.setOrderNo(orderNo);
		}

		return insertResult;
	}

	// Hàm đóng kết nối dùng chung
	private void close(ResultSet jdbcResultSet, PreparedStatement jdbcPreparedStatement, Connection jdbcConnection) {
		try {
			if (jdbcResultSet != null)
				jdbcResultSet.close();
		} catch (Exception ignored) {
		}
		try {
			if (jdbcPreparedStatement != null)
				jdbcPreparedStatement.close();
		} catch (Exception ignored) {
		}
		try {
			if (jdbcConnection != null)
				jdbcConnection.close();
		} catch (Exception ignored) {
		}
	}

	public List<Order> getOrdersByUsername(String username) {
		List<Order> orderList = new ArrayList<>();
		String sqlOrder = "SELECT * FROM tblorder WHERE customer_username = ? ORDER BY order_date DESC";
		String sqlOrderBook = "SELECT ob.*, b.title, b.author, b.price FROM tblorder_book ob JOIN book b ON ob.book_id = b.book_id WHERE ob.order_id = ?";

		try {
			jdbcConnection = DBConnection.getConnection();
			preStatement = jdbcConnection.prepareStatement(sqlOrder);
			preStatement.setString(1, username);
			resultSet = preStatement.executeQuery();

			while (resultSet.next()) {
				Order order = new Order();
				order.setOrderId(resultSet.getInt("order_id"));
				order.setOrderNo(resultSet.getString("order_no"));
				order.setOrderDate(MyUntils.convertStringToDate(resultSet.getString("order_date")));
				order.setOrderApproveDate(MyUntils.convertStringToDate(resultSet.getString("order_approve_date")));
				order.setDeliveryAddress(resultSet.getString("delivery_address"));
				order.setPaymentMode(resultSet.getString("payment_mode"));
				order.setOrderStatus(resultSet.getByte("order_status"));
				order.setPaymentStatus(resultSet.getBoolean("payment_status"));
				order.setTotalCost(resultSet.getFloat("total_cost"));
				order.setPaymentImagePath(resultSet.getString("payment_img"));
				order.setStatusDate(MyUntils.convertStringToDate(resultSet.getString("status_date")));

				// Lấy danh sách sách trong đơn hàng
				PreparedStatement bookStmt = jdbcConnection.prepareStatement(sqlOrderBook);
				bookStmt.setInt(1, order.getOrderId());
				ResultSet bookRs = bookStmt.executeQuery();

				List<CartItem> cartItemList = new ArrayList<>();
				while (bookRs.next()) {
					Book book = new Book();
					book.setBookId(bookRs.getInt("book_id"));
					book.setTitle(bookRs.getString("title"));
					book.setAuthor(bookRs.getString("author"));
					book.setPrice(bookRs.getFloat("price"));

					CartItem cartItem = new CartItem();
					cartItem.setSelectedBook(book);
					cartItem.setQuantity(bookRs.getInt("quantity"));

					cartItemList.add(cartItem);
				}
				bookRs.close();
				bookStmt.close();

				order.setOrderBookList(cartItemList);
				orderList.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preStatement, jdbcConnection);
		}

		return orderList;
	}
}