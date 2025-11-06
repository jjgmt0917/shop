package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dto.*;
import dao.*;

@WebServlet("/emp/goodsList")
public class GoodsListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		int lastPage = 0;
		
		GoodsDao goodsDao = new GoodsDao();
		
		int totalGoods = 0;
		try {
			totalGoods = goodsDao.EmpTotalCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastPage = totalGoods / rowPerPage + (totalGoods % rowPerPage == 0 ? 0 : 1);
		
		List<Goods> list = null;
		try {
			list = goodsDao.SelectGoodsList(beginRow, rowPerPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 모델 속성
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("lastPage", lastPage);
				request.setAttribute("list", list);
				
		request.getRequestDispatcher("/WEB-INF/view/emp/goodsList.jsp").forward(request, response);
	}
}
