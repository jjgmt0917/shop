package dao;
import java.sql.*;

import dto.*;
public class EmpDao {
	// 로그인
	public Emp SelectEmpByLogin(Emp emp) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = """
					SELECT emp_code, emp_id, emp_pw, emp_name, active
					FROM emp WHERE emp_id = ? AND emp_pw = ? AND active > 0
				""";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		rs = stmt.executeQuery();
		Emp loginEmp = null;
		if(rs.next()) {
			loginEmp = new Emp();
			loginEmp.setEmpCode(rs.getInt("emp_code"));
			loginEmp.setEmpId(rs.getString("emp_id"));
			loginEmp.setEmpPw(rs.getString("emp_pw"));
			loginEmp.setEmpName(rs.getString("emp_name"));
			loginEmp.setActive(rs.getString("active"));
		}
		return loginEmp;
	}
}
