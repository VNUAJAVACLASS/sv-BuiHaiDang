package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import model.Book;
import model.Cart;
import model.CartItem;
import untils.Constant;
import untils.MyUntils;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(urlPatterns = { "/cartBook/addToCart", "/cartBook/removeFromCart", "/cartBook/viewCart" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDao = new BookDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();

		String servletPath = request.getServletPath();
		String pathInfo = MyUntils.getPathInfoFromServletPath(servletPath);
		String bookIdStr = request.getParameter("bookId");
		String quantityPurchasedStr = request.getParameter("quantityPurchased");

		int bookId = -1;
		int quantityPurchased = -1;

		try {
			if (bookIdStr != null) {// Neu co gui toi
				bookId = Integer.parseInt(bookIdStr);
			}
		} catch (NumberFormatException e) {
			errors.add(Constant.BOOK_ID_INVALID_VALIDATE_MSG);

		}

		try {
			if (quantityPurchasedStr != null) {
				quantityPurchased = Integer.parseInt(quantityPurchasedStr);
			}

		} catch (NumberFormatException e) {
			errors.add(Constant.BOOK_QUANTITY_IN_STOCK_INVALID_VALIDATE_MSG);
		}

		if (errors.isEmpty()) {
			if (Constant.ADD_TO_CART_ACTION.equals(pathInfo)) {
				// them vao gio hang
				Book selectBook = bookDao.findById(bookId);
				Cart cartOfCustomer = MyUntils.getCartOfCustomer(session);

				if (cartOfCustomer == null) {
					cartOfCustomer = new Cart();
					System.out.println("Cart chua ton tai tao moi");
				} else {
					System.out.println("Cart session đã tồn tại.");
					System.out.println("Số lượng sách trong giỏ: " + cartOfCustomer.getCartItemList().size());

				}

				if (selectBook != null && quantityPurchased > 0) {
				    cartOfCustomer.addCartItemToCart(bookId, new CartItem(selectBook, quantityPurchased));
				    System.out.println("Đã thêm sách vào giỏ: " + selectBook.getTitle() + ", số lượng: " + quantityPurchased);
				} else {
				    System.out.println("Không thể thêm sách vào giỏ: bookId không hợp lệ hoặc số lượng không hợp lệ.");
				}
				
				System.out.println("Đã thêm sách vào giỏ: " + selectBook.getTitle() + ", số lượng: " + quantityPurchased);
				// luu tru sesion
				MyUntils.storeCart(session, cartOfCustomer);
			} else if (Constant.REMOVE_FROM_CART_ACTION.equals(pathInfo)) {
				// xoa tu gio hang
				Cart cartOfCustomer = MyUntils.getCartOfCustomer(session);
				cartOfCustomer.removeCartItemFromCart(bookId);
				MyUntils.storeCart(session, cartOfCustomer);

			}

			// Truong hop ye yeu cầu view cart nhảy trực den
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cart_view.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/clientHome");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
