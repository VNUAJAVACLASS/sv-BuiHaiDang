package controller;

import java.io.Console;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;
import untils.Constant;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login","/admin"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// kiem tra cookie 
		Cookie[] cookies = request.getCookies();
		String rememberedUser = null;
		
		if (cookies == null) {
			for (Cookie c : cookies) {
				// chi lay cookies da ghi trc do
				if(c.getName().equals("rememberedUser")) {
					rememberedUser = c.getValue();
					break;
				}
			}
		}
		
		// ghi gia tri ra request scope su dung tren login 
		request.setAttribute("rememberedUser", rememberedUser);
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember"); 
		
		User user = userDao.findUser(username, password);
		if(user !=  null) {
			// success
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute(Constant.LOGINED_USER, user);
			
			if("on".equals(remember)) {
				Cookie cookie = new Cookie("rememberedUser", username);
				cookie.setMaxAge(60*60*24*7); // 7 ngay 
				response.addCookie(cookie);
			}else {
				Cookie cookie = new Cookie("rememberedUser", "");
				cookie.setMaxAge(0); 
				response.addCookie(cookie);
			}
			
			
			byte role = user.isRole();
			if(role == 1) {
				response.sendRedirect("clientHome");
			}else {
				response.sendRedirect("adminHome");
			}
		}else {
			request.setAttribute("error", "Sai ten dang nhap hoac mat khau ");
			request.getRequestDispatcher("login.jsp").forward(request, response);;
			
		}
		
	}

}
