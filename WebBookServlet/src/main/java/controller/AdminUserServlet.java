package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UserDao;
import model.User;

@WebServlet("/adminUser")
public class AdminUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Kiểm tra đăng nhập
        HttpSession session = req.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            resp.sendRedirect("login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        String username_key = req.getParameter("username");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                req.getRequestDispatcher("form_user.jsp").forward(req, resp);
                break;

            case "edit":
                String usernameEdit = username_key.toString();
                User editUser = userDao.findByUsername(usernameEdit);
                req.setAttribute("user", editUser);
                req.getRequestDispatcher("form_user.jsp").forward(req, resp);
                break;

            case "delete":
                String usernameDelete = username_key.toString();
                userDao.deleteUser(usernameDelete);
                resp.sendRedirect("adminUser");
                break;

            case "detail":
                String usernameDetail = username_key.toString();
                User detailUser = userDao.findByUsername(usernameDetail);
                req.setAttribute("user", detailUser);
                req.getRequestDispatcher("detail_user.jsp").forward(req, resp);
                break;

            default:
                List<User> users = userDao.getAllUsers();
                req.setAttribute("userList", users);
                req.getRequestDispatcher("admin_list_user_view.jsp").forward(req, resp);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        String roleStr = req.getParameter("role");

        byte role = (roleStr != null && !roleStr.isEmpty()) ? Byte.parseByte(roleStr) : 0;

        User findUser = userDao.findByUsername(username); 
        
        if (findUser == null) {
            // Thêm mới
            User newUser = new User(username, password, fullname, role, email, mobile, address);
            userDao.insertUser(newUser);
        } else {
            // Cập nhật
            User updateUser = new User(username, password, fullname, role, email, mobile, address);
            updateUser.setUsername(username);
            userDao.updateUser(updateUser);
        }

        resp.sendRedirect("adminUser");
    }
}