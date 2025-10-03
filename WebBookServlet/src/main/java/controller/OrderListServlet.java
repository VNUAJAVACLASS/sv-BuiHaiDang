package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import model.Order;
import model.User;
import untils.Constant;
import untils.MyUntils;


@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    OrderDao orderDao;
   
   @Override
	public void init() throws ServletException {
	   orderDao = new OrderDao();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession();
	        User user = MyUntils.getLoginedUser(session);

	        if (user == null) {
	            response.sendRedirect(request.getContextPath() + "/login.jsp");
	            return;
	        }

	        List<Order> orders = orderDao.getOrdersByUsername(user.getUsername());
	        request.setAttribute(Constant.LOGINED_USER, user);
	        request.setAttribute(Constant.ORDER_LIST_OF_CUSTOMER, orders);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/order_list_view.jsp");
	        dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
