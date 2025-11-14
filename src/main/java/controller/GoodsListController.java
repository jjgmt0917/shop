package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import dto.*;
import dao.*;

@WebServlet("/emp/goodsList")
public class GoodsListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 20;
		int beginRow = (currentPage - 1) * rowPerPage;
		int lastPage = 0;
		
		GoodsDao goodsDao = new GoodsDao();
		List<Map<String, Object>> list = goodsDao.selectGoodsList(beginRow, rowPerPage);
		
		int totalGoods = 0;
		try {
			totalGoods = goodsDao.EmpTotalCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastPage = totalGoods / rowPerPage + (totalGoods % rowPerPage == 0 ? 0 : 1);
		// 페이징
		int startPage = (currentPage-1)/10*10 + 1;
		int endPage = startPage + 9;
		if(lastPage < endPage) {
			endPage = lastPage;
		}
		
		// 모델 속성
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/goodsList.jsp").forward(request, response);
	}
}
