package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.*;
import java.util.*;

@WebServlet("/emp/ordersList")
public class OrdersListController extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		int rowPerPage = 20;
		int beginRow = (1-currentPage) * rowPerPage;
		
		ordersDao = new OrdersDao();
		List<Map<String, Object>> list = null;
		try {
			list = ordersDao.SelectOrdersList(beginRow, rowPerPage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/ordersList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
