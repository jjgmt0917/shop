package restapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.GoodsDao;

@WebServlet("/restapi/modifyGoodsSoldout")
public class ModifyGoodsSoldoutRest extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======= ModifyGoodsSoldoutRest =======");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println("ModifyGoodsSoldoutRest =======> goodsCode: " + goodsCode);
		String newSoldout = null;
		
		GoodsDao goodsDao = new GoodsDao();
		try {
			newSoldout = goodsDao.ModifyGoodsSoldout(goodsCode);
			System.out.println("ModifyGoodsSoldoutRest ======> newSoldout: " + newSoldout);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(newSoldout == null || newSoldout == "N") {		// 옳은 결과가 나옴
			String json = "{\"newSoldout\":" + newSoldout + "}";
			out.write(json);
			out.flush();
		} else {
			System.out.println("ModifyGoodsSoldoutRest ======> " + newSoldout);
		}
		
		out.close();
	}

}
