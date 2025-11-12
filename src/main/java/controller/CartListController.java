package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import dto.*;
import dao.*;

@WebServlet("/customer/cartList")
public class CartListController extends HttpServlet {
	CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCust = (Customer)session.getAttribute("loginCustomer");
		
		cartDao = new CartDao();
		List<Map<String, Object>> list = cartDao.selectCartList(loginCust.getCustomerCode());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/customer/cartList.jsp").forward(request, response);
	}
}
