package restapi;

import java.io.IOException;
import java.io.PrintWriter;

import dao.CustomerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchId
 */
@WebServlet("/restapi/searchId")
public class SearchIdRest extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// html을 응답하는 용도 -> XML or JSON 문자열 응답으로 변경
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		System.out.println("SearchIdRest / searchId 요청");
		String searchId = request.getParameter("searchId");
		System.out.println("searchId: " + searchId);
		
		// DAO 호출
		CustomerDao custDao = new CustomerDao();
		int result = 2;
		try {
			result = custDao.IdCheck(searchId);
			System.out.println("result: " + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print(result);
		out.flush();
		out.close();

	}

}
