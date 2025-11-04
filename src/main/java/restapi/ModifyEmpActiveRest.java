package restapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.EmpDao;

@WebServlet("/restapi/modifyEmpActive")
public class ModifyEmpActiveRest extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======= ModifyEmpActiveRest =======");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int empCode = Integer.parseInt(request.getParameter("empCode"));
		Integer newActive = null;
		
		EmpDao empDao = new EmpDao();
		try {
			newActive = empDao.ModifyEmpActive(empCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(newActive == 0 || newActive == 1) {		// 옳은 결과가 나옴
			String json = "{\"newActive\":" + newActive + "}";
			out.write(json);
			out.flush();
		} else if(newActive == 333) {
			System.out.println("일치하는 sql 정보를 찾지 못했습니다.");
		} else if(newActive == 444) {
			System.out.println("active update에 실패하였습니다.");
		}
		out.close();
		
	}

}
