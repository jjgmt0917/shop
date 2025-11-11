package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.*;
import dto.*;

@WebServlet("/emp/removeCust")
public class RemoveCustController extends HttpServlet {
	CustomerDao custDao;
	// 강제 탈퇴 화면
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("customerId");
		String memo = request.getParameter("memo");
		Outid outid = new Outid();
		outid.setId(custId);
		outid.setMemo(memo);
		custDao = new CustomerDao();
		custDao.deleteCustByEmp(outid);
		
		response.sendRedirect(request.getContextPath() + "/emp/customerList");
	}
	
	// 강제 탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
