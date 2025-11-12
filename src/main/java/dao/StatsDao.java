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
	
	public List<Map<String, Object>> orderCntByYM(String fromYM, String toYM) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select to_char(createdate, 'YYYY-MM') ym, count(*) orderCnt
				    from orders
				    where createdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')
				    group by to_char(createdate, 'YYYY-MM')
				    order by ym asc
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
				m.put("orderCnt",rs.getInt("orderCnt"));
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
	
	public List<Map<String, Object>> orderPriceByYM(String fromYM, String toYM) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select to_char(createdate, 'YYYY-MM') ym, sum(order_price) price
					    from orders
					    where createdate between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD')
					    group by to_char(createdate, 'YYYY-MM')
					    order by to_char(createdate, 'YYYY-MM') asc
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
				m.put("price",rs.getInt("price"));
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
	
	public List<Map<String, Object>> customerOrderRank() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select o.ranking ranking, o.customer_code customerCode, o.cnt cnt
					from (select customer_code, count(*) cnt, rank() over(order by count(*) desc) ranking
					        from orders
					        group by customer_code) o
					where o.ranking <= 10
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ranking",rs.getInt("ranking"));
				m.put("customerCode",rs.getInt("customerCode"));
				m.put("cnt",rs.getInt("cnt"));
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
	
	public List<Map<String, Object>> customerPriceRank() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select o.ranking ranking, o.customer_code customerCode, o.price price
					from (select customer_code, sum(order_price) price, rank() over(order by sum(order_price) desc) ranking
					        from orders
					        group by customer_code) o
					where o.ranking <= 10
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ranking",rs.getInt("ranking"));
				m.put("customerCode",rs.getInt("customerCode"));
				m.put("price",rs.getInt("price"));
				list.add(m);
			}
		} catch (SQLException e) {
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
	
	public List<Map<String, Object>> goodsOrderRank() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select o.ranking ranking, o.goods_code goodsCode, o.cnt cnt
					from (select goods_code, count(*) cnt, rank() over(order by count(*) desc) ranking
					        from orders
					        group by goods_code) o
					where o.ranking <= 10
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ranking",rs.getInt("ranking"));
				m.put("goodsCode",rs.getInt("goodsCode"));
				m.put("cnt",rs.getInt("cnt"));
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
	
	public List<Map<String, Object>> goodsPriceRank() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select o.ranking ranking, o.goods_code goodsCode, o.price price
					from (select goods_code, sum(order_price) price, rank() over(order by sum(order_price) desc) ranking
					        from orders
					        group by goods_code) o
					where o.ranking <= 10
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ranking",rs.getInt("ranking"));
				m.put("goodsCode",rs.getInt("goodsCode"));
				m.put("price",rs.getInt("price"));
				list.add(m);
			}
		} catch (SQLException e) {
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
	
	public List<Map<String, Object>> reviewAvgRank() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select t.ranking ranking, t.goods_code goodsCode, t.average average
					from (select round(avg(r.score), 2) average, o.goods_code, rank() over(order by avg(r.score) desc) ranking
					    from orders o inner join review r on o.order_code = r.order_code
					    group by o.goods_code) t
					where ranking <= 10
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("ranking",rs.getInt("ranking"));
				m.put("goodsCode",rs.getInt("goodsCode"));
				m.put("average",rs.getDouble("average"));
				list.add(m);
			}
		} catch (SQLException e) {
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
	
	public List<Map<String, Object>> genderOrderCnt() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select t.g gender, count(*) cnt
					from (select c.gender g, o.order_code oc
					from customer c inner join orders o 
					on c.customer_code = o.customer_code) t
					group by t.g
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("gender",rs.getString("gender"));
				m.put("cnt",rs.getInt("cnt"));
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
	
	public List<Map<String, Object>> genderPriceCnt() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select t.g gender, sum(t.price) genderPrice
					from (select c.gender g, o.order_code oc, o.order_price price
					from customer c inner join orders o 
					on c.customer_code = o.customer_code) t
					group by t.g
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("gender",rs.getString("gender"));
				m.put("genderPrice",rs.getInt("genderPrice"));
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
