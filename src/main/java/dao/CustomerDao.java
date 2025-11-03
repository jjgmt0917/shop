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
			loginCustomer.setCustomerPoint(rs.getInt("customer_point"));
		}
		return loginCustomer;
	}
}
