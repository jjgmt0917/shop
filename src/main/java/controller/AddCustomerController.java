package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
	}

}
