package restapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/restapi/ModifyGoodsSoldout")
public class ModifyGoodsSoldoutRest extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======= ModifyEmpActiveRest =======");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String newSoldout = null;
		
		
		
	}

}
