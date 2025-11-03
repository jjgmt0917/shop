package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dto.*;
import dao.*;

/**
 * Servlet implementation class LoignController
 */
@WebServlet("/public/login")
public class LoginController extends HttpServlet {
	// form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/public/login.jsp").forward(request, response);
	}

	// action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerOrEmpSel = request.getParameter("customerOrEmpSel");
		String loginId = request.getParameter("id");
		String loginPw = request.getParameter("pw");
		System.out.println("회원Id: " + loginId + ", 회원PW: " + loginPw);
		if(customerOrEmpSel.equals("customer")) {	// 회원 로그인
			CustomerDao custDao = new CustomerDao();
			Customer pCust = new Customer();
			pCust.setCustomerId(loginId);
			pCust.setCustomerPw(loginPw);
			Customer loginCust = null;
			try {
				loginCust = custDao.SelectCustomerByLogin(pCust);
				if(loginCust != null) {	// 로그인 성공
					request.getSession().setAttribute("loginCustomer", loginCust);
					System.out.println("회원(" + loginCust.getCustomerName() + ") 로그인 성공");
					response.sendRedirect(request.getContextPath() + "/customer/customerIndex");
				} else {	// 로그인 실패
					System.out.println("회원 로그인에 실패하셨습니다.");
					response.sendRedirect(request.getContextPath() + "/public/login");
				}
			} catch (SQLException e) {
				System.out.println("SQL 실패로 인한 로그인 실패");
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/public/login");
			}
		} else if(customerOrEmpSel.equals("emp")) {	// 직원 로그인
			EmpDao custDao = new EmpDao();
			Emp empCust = new Emp();
			empCust.setEmpId(loginId);
			empCust.setEmpPw(loginPw);
			Emp loginEmp = null;
			try {
				loginEmp = custDao.SelectEmpByLogin(empCust);
				if(loginEmp != null) {	// 로그인 성공
					request.getSession().setAttribute("loginEmp", loginEmp);
					System.out.println("직원(" + loginEmp.getEmpName() + ") 로그인 성공");
					response.sendRedirect(request.getContextPath() + "/emp/empIndex");
				} else {	// 로그인 실패
					System.out.println("직원 로그인에 실패하셨습니다.");
					response.sendRedirect(request.getContextPath() + "/public/login");
				}
			} catch (SQLException e) {
				System.out.println("SQL 실패로 인한 로그인 실패");
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/public/login");
			}
		}
	}

}
