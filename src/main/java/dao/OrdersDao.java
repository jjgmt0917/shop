package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dto.*;

public class OrdersDao {
	public List<Map<String, Object>> SelectOrdersList(int beginRow, int rowPerPage) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = """
					select o.order_code orderCode, o.goods_code goodsCode, o.customer_code customerCode
						, a.address address, o.order_quantity orderQuantity, o.order_state state
						, o.createdate createdate, g.goods_name goodsName, g.goods_price goodsPrice
						, c.customer_name customerName, c.customer_phone customerPhone
					from orders o inner join goods g on o.goods_code = g.goods_code
					    inner join customer c on o.customer_code = c.customer_code
					        inner join address a on o.address_code = a.address_code
					order by o.order_code desc offset ? rows fetch next ? rows only
				""";
		
		conn = DBConnection.getConn();
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("customerCode", rs.getInt("customerCode"));
			m.put("address", rs.getString("address"));
			m.put("orderQuantity", rs.getInt("orderQuantity"));
			m.put("state", rs.getString("state"));
			m.put("createdate", rs.getString("createdate"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("customerName", rs.getString("customerName"));
			m.put("customerPhone", rs.getString("customerPhone"));
			list.add(m);
		}
		
		return list;
	}
	
	// goodsOne -> 주문완료
	public int insertOrders(Orders o) {
		int row = 0;
		Connection conn =null;
		PreparedStatement stmt = null;
		String sql = """
					insert into orders(
						order_code, goods_code, customer_code, address_code, order_quantity, order_price, order_strate, createdate
					) values(
						seq_order.nextval, ?, ?, ?, ?, ?, '주문완료', sysdate
					)
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, o.getGoodscode());
			stmt.setInt(2, o.getCustomerCode());
			stmt.setInt(3, o.getAddressCode());
			stmt.setInt(4, o.getOrderQuantity());
			stmt.setInt(5, o.getOrderPrice());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	// cartList -> 주문완료
}
