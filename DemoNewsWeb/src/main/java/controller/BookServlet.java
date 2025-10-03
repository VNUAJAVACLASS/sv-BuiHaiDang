package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import service.BookService;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private BookService bookService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

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
                resp.sendRedirect("books");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String idStr = req.getParameter("book_id");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String priceStr = req.getParameter("price");
        String imagePath = req.getParameter("image_path");
        String quantityInStock = req.getParameter("quantityInStock");
		String detail = req.getParameter("detail");


        float price = (priceStr != null && !priceStr.isEmpty()) ? Float.parseFloat(priceStr) : 0;
        float quantity = (quantityInStock != null && !quantityInStock.isEmpty()) ? Float.parseFloat(quantityInStock) : 0;
		LocalDateTime createDate = LocalDateTime.now();
		
        if (idStr == null || idStr.isEmpty()) {
            // thêm mới
            Book newBook = new Book(0, title, author, price, imagePath, quantity, detail, createDate);
            bookService.insertBook(newBook);
        } else {
            // cập nhật
            int id = Integer.parseInt(idStr);
            Book updateBook = new Book(0, title, author, price, imagePath, quantity, detail, createDate);
            bookService.updateBook(updateBook);
        }

        resp.sendRedirect("books"); 
    }
}
