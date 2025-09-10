package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import service.BookService;

/**
 * Servlet implementation class ClientHomeServlet
 */
@WebServlet("/clientHome")
public class ClientHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private BookService bookService;
  
	 @Override
	    public void init() throws ServletException {
	        super.init();
	        bookService = new BookService();
	    }
	 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html;charset=UTF-8");
	        req.setCharacterEncoding("UTF-8");

	        String action = req.getParameter("action");
	        String idStr = req.getParameter("id");
	        if (action == null) action = "list";
	
	        switch (action) {
           
            case "detail": 
                int idDetail = Integer.parseInt(idStr);
                Book detailBook = bookService.findById(idDetail);
                req.setAttribute("book", detailBook);
                req.getRequestDispatcher("detail_client.jsp").forward(req, resp);
                break;

            default: 
                List<Book> bookList = bookService.getAllBooks();
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
	}

}
