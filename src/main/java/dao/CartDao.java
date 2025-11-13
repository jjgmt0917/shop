package dao;
import java.sql.*;
import java.util.*;
import dto.*;
public class CartDao {
	// cartCode -> 주문완료 페이지에 출력 하나의 상품 노출
	public Map<String, Object> selectCartListByKey(int cartCode) {
		Map<String, Object> m = new HashMap<>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		String sql = """
				select gi.filename filename
				    , g.goods_code goodsCode
				    , g.goods_name goodsName
				    , g.goods_price goodsPrice
				    , g.point_rate pointRate
				    , c.goods_quantity goodsQuantity
				from cart c inner join goods g
				on c.goods_code = g.goods_code
				    inner join goods_img gi on c.goods_code = gi.goods_code
				where c.cart_code = ?
			""";
		
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cartCode);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				m.put("filename",rs.getString("filename"));
				m.put("goodsCode",rs.getInt("goodsCode"));
				m.put("goodsName",rs.getString("goodsName"));
				m.put("goodsPrice",rs.getInt("goodsPrice"));
				m.put("pointRate",rs.getDouble("pointRate"));
				m.put("goodsQuantity",rs.getInt("goodsQuantity"));
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
		
		return m;
	}
	
	public List<Map<String, Object>> selectCartList(int customerCode) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
					select c.cart_code cartCode, c.goods_quantity goodsQuantity, g.goods_name goodsName, g.goods_price goodsPrice
						, nvl(g.soldout, ' ') soldout, g.goods_price * c.goods_quantity totalPrice
					from cart c inner join goods g on c.goods_code = g.goods_code
					where c.customer_code = ?
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customerCode);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("cartCode", rs.getInt("cartCode"));
				m.put("goodsQuantity", rs.getInt("goodsQuantity"));
				m.put("goodsName", rs.getString("goodsName"));
				m.put("goodsPrice", rs.getInt("goodsPrice"));
				m.put("soldout", rs.getString("soldout"));
				m.put("totalPrice", rs.getInt("totalPrice"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) stmt.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int insertCart(Cart c) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = """
					
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) stmt.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return row;
	}
}
