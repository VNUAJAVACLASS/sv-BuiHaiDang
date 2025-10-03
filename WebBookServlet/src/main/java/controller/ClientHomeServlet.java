package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import model.Book;


@WebServlet("/clientHome")
public class ClientHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDao = new BookDao();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String idStr = req.getParameter("id");
		if (action == null || action.isEmpty())
			action = "list";

		switch (action) {
		case "detail": // chuyen sang trang chi tiet 
			int idDetail = Integer.parseInt(idStr);
			Book detailBook = bookDao.findById(idDetail);
			req.setAttribute("book", detailBook);
			req.getRequestDispatcher("detail_client.jsp").forward(req, resp);
			break;
		case "list":
		default: // mac dinh hien thi thong tin 
			int page = 1;
			int recordsPerPage = 4;
			
			String pageStr =  req.getParameter("page");
			if(pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
			
			List<Book>  bookList = bookDao.getBooksByPage((page -1 ) * recordsPerPage, recordsPerPage);
			int totalRecords = bookDao.getTotalBookCount();
			int totalPage = (totalRecords + recordsPerPage - 1) / recordsPerPage;
			
			req.setAttribute("currentPage", page);
			req.setAttribute("totalPages", totalPage);
			req.setAttribute("bookList", bookList);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
