package dao;
import java.sql.*;
import java.util.*;
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
			loginEmp.setActive(rs.getInt("active"));
		}
		return loginEmp;
	}
	
	// 사원 목록
	public List<Emp> selectEmpListByPage(int beginRow, int rowPerPage) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = """
					SELECT emp_code, emp_id, emp_name, active, createdate
					FROM emp ORDER BY emp_code
					OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";
		
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		rs = stmt.executeQuery();
		List<Emp> list = new ArrayList<Emp>();
		while(rs.next()) {
			Emp emp = new Emp();
			emp.setEmpCode(rs.getInt("emp_code"));
			emp.setEmpId(rs.getString("emp_id"));
			emp.setEmpName(rs.getString("emp_name"));
			emp.setActive(rs.getInt("active"));
			emp.setCreatedate(rs.getString("createdate"));
			list.add(emp);
		}
		return list;
	}
}
