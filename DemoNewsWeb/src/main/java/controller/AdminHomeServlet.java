package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Book;
import service.BookService;

@WebServlet("/adminHome")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private BookService bookService;
	 
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        bookService = new BookService();
	    }
	 
	 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   // Kiểm tra trạng thái đăng nhập
	    HttpSession session = req.getSession(false); // không tạo mới session
	    String username = (session != null) ? (String) session.getAttribute("username") : null;

	    if (username == null) {
	        // Nếu chưa đăng nhập thì chuyển hướng về trang login
	        resp.sendRedirect("login");
	        return;
	    }

	    // Nếu đã đăng nhập thì tiếp tục xử lý
	    req.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    
	    String action = req.getParameter("action");
        String idStr = req.getParameter("id");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "edit":
                int idEdit = Integer.parseInt(idStr);
                Book editBook = bookService.findById(idEdit);
                req.setAttribute("book", editBook);
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "delete": 
                int idDelete = Integer.parseInt(idStr);
                bookService.deleteBook(idDelete);
                resp.sendRedirect("adminHome");
                break;

            case "detail": 
                int idDetail = Integer.parseInt(idStr);
                Book detailBook = bookService.findById(idDetail);
                req.setAttribute("book", detailBook);
                req.getRequestDispatcher("detail.jsp").forward(req, resp);
                break;

            default: 
                List<Book> bookList = bookService.getAllBooks();
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("list.jsp").forward(req, resp);
                break;
        }

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  req.setCharacterEncoding("UTF-8");
	        resp.setContentType("text/html;charset=UTF-8");

	        String idStr = req.getParameter("book_id");
	        String title = req.getParameter("title");
	        String author = req.getParameter("author");
	        String priceStr = req.getParameter("price");
	        String imagePath = req.getParameter("image_path");

	        float price = (priceStr != null && !priceStr.isEmpty()) ? Float.parseFloat(priceStr) : 0;

	        if (idStr == null || idStr.isEmpty()) {
	            // thêm mới
	            Book newBook = new Book(0, title, author, price, imagePath);
	            bookService.insertBook(newBook);
	        } else {
	            // cập nhật
	            int id = Integer.parseInt(idStr);
	            Book updateBook = new Book(id, title, author, price, imagePath);
	            bookService.updateBook(updateBook);
	        }

	        resp.sendRedirect("adminHome"); 
	}

}
