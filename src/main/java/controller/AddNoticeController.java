package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import java.io.IOException;
import dto.*;
import dao.*;

@WebServlet("/emp/addNotice")
public class AddNoticeController extends HttpServlet {
	NoticeDao noticeDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/emp/addNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Emp loginEmp = (Emp)(request.getSession().getAttribute("loginEmp"));
		int writerCode = loginEmp.getEmpCode();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		notice.setEmpCode(writerCode);
		
		noticeDao = new NoticeDao();
		int row = 0;
		row = noticeDao.insertNotice(notice);
		if(row == 1) {
			System.out.println("AddNoticeController 성공 : 공지사항 등록 -> " + title);
			response.sendRedirect(request.getContextPath() + "/emp/noticeList");
		} else {
			System.out.println("AddNoticeController 실패 : row -> " + row);
			response.sendRedirect(request.getContextPath() + "/emp/addNotice");
		}
	}

}
