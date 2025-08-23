package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.News;
import service.NewsService;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/news")
public class NewsServlet extends HttpServlet {

//	private static List<News> newsList = new ArrayList<News>();
	private static int idCounter = 1;

	private NewsService newsService;

	public NewsServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		newsService = new NewsService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Thiết lập UTF-8 cho request, response để hiển thị đúng tiếng Việt
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// Lấy về giá trị các tham số được gửi qua request từ client
		String action = req.getParameter("action");
		String idStr = req.getParameter("id");
		// action == null ứng với trường hợp lần đầu vào ứng dụng
		// hoặc khi click link "Quay lại danh sách", ko cần tham số action
		if (action == null)
			action = "list";

		switch (action) {
		case "create": // bấm link tạo tin mới, trả về form tạo mới
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "edit": // bấm link sửa
			// Lấy nội dung với id tin tức tương ứng cần sửa
			int idEdit = Integer.parseInt(idStr);
			News editNews = newsService.findById(idEdit); // ghi vào request để trang form.jsp lấy ra điền vào các
			req.setAttribute("news", editNews);
			req.getRequestDispatcher("form.jsp").forward(req, resp);
			break;
		case "delete":
			int idDelete = Integer.parseInt(idStr);
			newsService.deleteNews(idDelete);
			resp.sendRedirect("news"); // quay lại trang chủ tin tức
			break;
		case "detail":
			int idDetail = Integer.parseInt(idStr);
			News detailNews = newsService.findById(idDetail);
			req.setAttribute("news", detailNews);
			req.getRequestDispatcher("detail.jsp").forward(req, resp);
			break;
		default: // trường hợp lần đầu vào ứng dụng hoặc bấm link quay lại danh sách
			// ghi ds tin tức vào request để trang list.jsp lấy ra hiển thị
			List<News> newsList = newsService.getAllNews();
			req.setAttribute("newsList", newsList);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
			break;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// Lấy các trường từ form được submit tới ở trang form.jsp
		String idStr = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// Nếu ko có id, ứng với tình huống tạo mới tin tức
		if (idStr == null || idStr.isEmpty()) {
			// Tạo tin tức mới với id mới
			newsService.insertNews(idStr, title, content);
		} else {
			// Cập nhật tin tức với id tương ứng
			int id = Integer.parseInt(idStr);
			News existing = newsService.findById(id);
			if (existing != null)
				newsService.updateNews(id, title, content);
		}
		// Khi chuyển hướng đến servlet "news", phương thức doGet được gọi
		// sẽ ghi newsList vào request và chuyển tiếp tới trang list.jsp
		resp.sendRedirect("news");
	}

}
