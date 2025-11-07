package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.NoticeDao;
import dto.Notice;

@WebServlet("/emp/noticeList")
public class NoticeListController extends HttpServlet {
	private NoticeDao noticeDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage= Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage-1) * rowPerPage;
		
		noticeDao = new NoticeDao();
		List<Notice> list = noticeDao.selectNoticeList(beginRow, rowPerPage);
		int count = noticeDao.selectCount();
		int lastPage = count % rowPerPage == 0 ? count/rowPerPage : count/rowPerPage + 1;
		// 페이징
		/*
		 * cp		sc									ep
		 * 1~10		(cp-1)/pagePerPage*pagePerPage + 1	sp+(pagePerPage-1)
		 */
		int startPage = (currentPage-1)/10*10 + 1;
		int endPage = startPage + 9;
		if(lastPage < endPage) {
			endPage = lastPage;
		}
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.getRequestDispatcher("/WEB-INF/view/emp/noticeList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
