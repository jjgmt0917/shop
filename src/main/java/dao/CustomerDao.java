package dao;

import java.sql.*;

import dto.*;

public class CustomerDao {
	// 로그인
	public Customer SelectCustomerByLogin(Customer customer) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = """
					SELECT customer_code, customer_id, customer_pw, customer_name, customer_phone, customer_point
					FROM customer WHERE customer_id = ? AND customer_pw = ?
				""";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		rs = stmt.executeQuery();
		
		Customer loginCustomer = null;
		if(rs.next()) {
			loginCustomer = new Customer();
			loginCustomer.setCustomerCode(rs.getInt("customer_code"));
			loginCustomer.setCustomerId(rs.getString("customer_id"));
			loginCustomer.setCustomerPw(rs.getString("customer_pw"));
			loginCustomer.setCustomerName(rs.getString("customer_name"));
			loginCustomer.setCustomerPhone(rs.getString("customer_phone"));
			loginCustomer.setPoint(rs.getInt("customer_point"));
		}
		return loginCustomer;
	}
	// 아이디 중복체크
	public int IdCheck(String id) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = """
					SELECT customer_id id FROM customer WHERE customer_id = ?
					UNION ALL
					SELECT emp_id id FROM emp WHERE emp_id = ?
					UNION ALL
					SELECT id FROM outid WHERE id = ? 
				""";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, id);
		stmt.setString(3, id);
		rs = stmt.executeQuery();
		int result = 0;
		if(rs.next()) {
			result = 1;
		}
		return result;
	}
	// 회원가입
	public int InsertCustomer(Customer cust) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		String sql = """
				INSERT INTO customer(customer_code, customer_id, customer_pw, customer_name, customer_phone, customer_point, createdate) 
				VALUES (seq_customer.nextval, ?, ?, ?, ?, 0, sysdate)
			""";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, cust.getCustomerId());
		stmt.setString(2, cust.getCustomerPw());
		stmt.setString(3, cust.getCustomerName());
		stmt.setString(4, cust.getCustomerPhone());
		
		return stmt.executeUpdate();
	}
}
