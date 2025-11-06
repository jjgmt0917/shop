package controller;

import java.io.IOException;
import java.sql.SQLException;

import dao.EmpDao;
import dto.Emp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/emp/addEmp")
public class AddEmpController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/emp/addEmp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("id");
		String empPw = request.getParameter("pw");
		String empName = request.getParameter("name");
		
		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setEmpPw(empPw);
		emp.setEmpName(empName);
		
		EmpDao empDao = new EmpDao();
		
		int row = 0;
		try {
			row = empDao.InsertEmp(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (row == 1) {
			System.out.println("직원 추가 성공 : 직원 Id -> ");
			response.sendRedirect(request.getContextPath() + "/emp/empList");
		} else {
			System.out.println("직원 추가 실패 : row -> " + row);
			response.sendRedirect(request.getContextPath() + "/emp/addEmp");
		}
	}
}
