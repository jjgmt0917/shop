package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.*;


@WebServlet("/emp/removeNotice")
public class RemoveNoticeController extends HttpServlet {
	NoticeDao noticeDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		
		noticeDao = new NoticeDao();
		int row = noticeDao.deleteNotice(noticeCode);
		if(row == 1) {
			System.out.println("RemoveNoticeController 성공 : 공지사항 삭제 -> " + noticeCode);
			response.sendRedirect(request.getContextPath() + "/emp/noticeList");
		} else {
			System.out.println("RemoveNoticeController 실패 : row -> " + row);
			response.sendRedirect(request.getContextPath() + "/emp/noticeList");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
