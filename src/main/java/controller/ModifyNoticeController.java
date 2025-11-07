package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import dto.*;
import dao.*;

@WebServlet("/emp/modifyNotice")
public class ModifyNoticeController extends HttpServlet {
	NoticeDao noticeDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		noticeDao = new NoticeDao();
		Map<String, Object> notice = noticeDao.selectNoticeOne(noticeCode);
		
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/WEB-INF/view/emp/modifyNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Emp loginEmp = (Emp)(request.getSession().getAttribute("loginEmp"));
		int writerCode = loginEmp.getEmpCode();
		int code = Integer.parseInt(request.getParameter("noticeCode"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNoticeCode(code);
		notice.setNoticeTitle(title);
		notice.setNoticeContent(content);
		notice.setEmpCode(writerCode);
		
		noticeDao = new NoticeDao();
		int row = 0;
		row = noticeDao.updateNotice(notice);
		if(row == 1) {
			System.out.println("ModifyNoticeController 성공 : 공지사항 등록 -> " + title);
			response.sendRedirect(request.getContextPath() + "/emp/noticeOne?noticeCode=" + code);
		} else {
			System.out.println("ModifyNoticeController 실패 : row -> " + row);
			response.sendRedirect(request.getContextPath() + "/emp/addNotice?noticeCode=" + code);
		}
	}

}
