package untils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Cart;
import model.CartItem;
import model.User;

public class MyUntils {

	public static String getPathInfoFromServletPath(String servletPath) {
		if (servletPath == null || servletPath.isEmpty()) {
			return "";
		}

		if (servletPath.startsWith("/")) {
			servletPath = servletPath.substring(1);
		}

		String[] parts = servletPath.split("/");
		if (parts.length >= 2) {
			return parts[1];
		} else if (parts.length == 1) {
			return parts[0];
		}

		return "";
	}
	
	// Lấy thông tin người dùng lưu trữ trong Session.
	public static User getLoginedUser(HttpSession session) {
	User loginedUser = (User)
	session.getAttribute(Constant.LOGINED_USER);
	return loginedUser;
	}

	public static Cart getCartOfCustomer(HttpSession session) {
		Cart cart = (Cart) session.getAttribute(Constant.CART_OF_CUSTOMER);
		return cart;
	}

	public static void storeCart(HttpSession session, Cart cart) {
		session.setAttribute(Constant.CART_OF_CUSTOMER, cart);
	}

	public static void updateCartOfCustomer(HttpSession session, Map<Integer, CartItem> cartItemList) {
		Cart cartOfCustomer = getCartOfCustomer(session);
		cartOfCustomer.setCartItemList(cartItemList);
		session.setAttribute(Constant.CART_OF_CUSTOMER, cartOfCustomer);
	}

	public static void deleteCart(HttpSession session) {
		session.removeAttribute(Constant.CART_OF_CUSTOMER);
	}

	public static String getTimeLabel() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy HH_mm");
		return sdf.format(new Date());
	}

	public static String createOrderNo(int orderId) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
		int code = orderId % 100;
// giả sử một ngày không quá 100 đơn hàng,
// phép toán %100 đảm bảo code không bị trùng nhau trong 1 ngày

		return sdf.format(new Date()) + code;
	}

	public static String extractFileExtension(Part part) {
// Ví dụ: form-data; name="file"; filename="C:\Note\A.jpg"
		String contentDisp = part.getHeader("content-disposition");
		int indexOfDot = contentDisp.lastIndexOf(".");
		return contentDisp.substring(indexOfDot, contentDisp.length() - 1); // return .jpg
	}

	public static File getFolderUpload(String appPath, String folderName) {
// user.dir: thư mục ứng dụng Web hiện tại
		File folderUpload = new File(appPath + File.separator + folderName);
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static Date convertStringToDate(String dateStr) {
	    if (dateStr == null || dateStr.trim().isEmpty()) {
	        return null;
	    }

	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	        return formatter.parse(dateStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
