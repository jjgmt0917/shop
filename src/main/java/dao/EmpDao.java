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
	
	// 총 사원 수 확인
	public int EmpTotalCount() throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
				SELECT COUNT(emp_code) FROM emp
			""";
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	// 직원 active 변경
	public int ModifyEmpActive(int empCode) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
				SELECT active FROM emp WHERE emp_code = ?
			""";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, empCode);
		rs = stmt.executeQuery();
		Integer active = null;
		
		if(rs.next()) active = rs.getInt("active");
		else return 333;
		
		int changeActive = active == 1 ? 0 : 1;
		sql = """
				UPDATE emp SET active = ? WHERE emp_code = ?
			""";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, changeActive);
		stmt.setInt(2, empCode);
		
		int row = stmt.executeUpdate();
		if(row == 1) return changeActive;
		else return 444;
	}
	
	// 사원 추가
	public int InsertEmp(Emp emp) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		String sql = """
				INSERT INTO emp(emp_code, emp_id, emp_pw, emp_name, active, createdate) 
				VALUES (seq_emp.nextval, ?, ?, ?, 1, sysdate)
			""";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		stmt.setString(3, emp.getEmpName());
		
		return stmt.executeUpdate();
	}
}
