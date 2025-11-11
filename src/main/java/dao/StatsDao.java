package dao;

import java.sql.*;
import java.util.*;

public class StatsDao {
	// 11개 chart 메서드
	public List<Map<String, Object>> orderTotalCntByYM(String fromYM, String toYM) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select t.ym ym, sum(t.cnt) over(order by t.ym asc) totalOrder
					from (select to_char(createdate, 'YYYY-MM') ym, count(*) cnt
					    	from orders
					    	where createdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')
					    	group by to_char(createdate, 'YYYY-MM')) t
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fromYM);
			stmt.setString(2, toYM);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ym",rs.getString("ym"));
				m.put("totalOrder",rs.getInt("totalOrder"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public List<Map<String, Object>> orderTotalPriceByYM(String fromYM, String toYM) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select t.ym ym, sum(t.total) over(order by t.ym asc) totalPrice
					from
					    (select to_char(createdate, 'YYYY-MM') ym, sum(order_price) total
					    from orders
					    where createdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')
					    group by to_char(createdate, 'YYYY-MM')) t
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fromYM);
			stmt.setString(2, toYM);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ym",rs.getString("ym"));
				m.put("totalPrice",rs.getInt("totalPrice"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}
