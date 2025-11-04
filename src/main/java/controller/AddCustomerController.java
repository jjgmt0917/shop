package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.CustomerDao;
import dto.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCustomerController
 */
@WebServlet("/public/addCustomer")
public class AddCustomerController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// addCustomer.jsp
		request.getRequestDispatcher("/WEB-INF/view/public/addCustomer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// CustomerDao.insertCustomer(Customer)
		// response.sendRedirect(request.getContextPath() + "public/login");
		String custId = request.getParameter("id");
		String custPw = request.getParameter("pw");
		String custName = request.getParameter("name");
		String custPhone = request.getParameter("phone");
		
		Customer cust = new Customer();
		cust.setCustomerId(custId);
		cust.setCustomerPw(custPw);
		cust.setCustomerName(custName);
		cust.setCustomerPhone(custPhone);
		
		CustomerDao custDao = new CustomerDao();
		int row = 0;
		try {
			row = custDao.InsertCustomer(cust);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row == 1) {
			System.out.println("회원 가입 성공 : 회원 Id -> " + cust.getCustomerId());
			response.sendRedirect(request.getContextPath() + "/public/login");
		} else {
			System.out.println("회원 가입 실패 : row -> " + row);
			response.sendRedirect(request.getContextPath() + "/public/addCustomer");
		}
		
	}

}
