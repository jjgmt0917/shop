package dao;

import java.sql.*;
import dto.*;
public class AddressDao {
	public void insertAddress(Address address) {
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs1 = null;
		
		String aql1 = """
					select count(*) from address where customer_code = ?
				""";
		String sql2 = """
					DELETE FROM address 
					WHERE address_code = (SELECT MIN(address_code) FROM address where customer_code = ?)
				""";
		String sql3 = """
					insert into address(address_code, customer_code, address, createdate)
					values(seq_address.nextval, ?, ?, sysdate)
				""";
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			stmt1 = conn.prepareStatement(aql1);
			stmt1.setInt(1, address.getCustomerCode());
			rs1 = stmt1.executeQuery();
			rs1.next();
			int cnt = rs1.getInt(1);
			if(cnt > 4) {	// 5개면 가장 오래된 주소 삭제 후 입력
				stmt2 = conn.prepareStatement(sql2);
				stmt2.setInt(1, address.getCustomerCode());
				stmt2.executeUpdate();
			}
			// 추가
			stmt3 = conn.prepareStatement(sql3);
			stmt3.setInt(1, address.getCustomerCode());
			stmt3.setString(2, address.getAddress());
			stmt3.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stmt1 != null) stmt1.close();
				if(stmt2 != null) stmt2.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
