package restapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.google.gson.Gson;

import dao.StatsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/totalOrder")
public class TotalOrderRestController extends HttpServlet {
	StatsDao statsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromYM = request.getParameter("fromYM");
		String toYM = request.getParameter("toYM");
		
		response.setContentType("application/json;charset=UTF-8");
		statsDao = new StatsDao();
		List<Map<String, Object>> orderList = statsDao.orderTotalCntByYM(fromYM, toYM);
		Gson gson = new Gson();
		String jsonResult = gson.toJson(orderList);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
	}
}
