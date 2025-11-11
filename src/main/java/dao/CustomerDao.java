package dao;

import java.sql.*;
import java.util.*;
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
	
	// 직원에 의한 강퇴
	public void deleteCustByEmp(Outid outid) {
		Connection conn = null;
		PreparedStatement custStmt = null;
		PreparedStatement outidStmt = null;
		String custSql = """
					delete from customer where customer_id = ?
				""";
		String outidSql = """
					insert into outid(id, memo, createdate)
					values(?, ?, ?)
				""";
		
		// JDBC Connection의 기본 Commit설정값 auto commit = true : false 변경 후 transaction 적용
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);	// 개발자가 commit / rollback 직접 구현이 필요
			custStmt = conn.prepareStatement(custSql);
			
			// param 설정 ? : outid.getId()
			
			int row = custStmt.executeUpdate();	// customer 삭제
			if(row == 1) {
				outidStmt = conn.prepareStatement(outidSql);
				
				// param 설정 : ? outid.getId() ? outid.getMemo() ? sysdate
				
				outidStmt.executeUpdate();	// outid 입력
				
			} else {
				throw new SQLException();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				custStmt.close();
				outidStmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// 전체 고객 리스트 확인
	public List<Customer> selectCustList(int beginRow, int rowperPage) {
		List<Customer> list = new ArrayList<>();
		
		return list;
	}
}
