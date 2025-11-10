package dao;

import java.sql.*;
import java.util.*;
import dto.*;

public class GoodsDao {
	// 상품등록 + 이미지등록
	// 반환값은 실패시 false
	public boolean InsertGoodsAndImg(Goods g, GoodsImg gi) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement stmtSeq = null;	// select
		PreparedStatement stmtGoods = null;	// insert
		PreparedStatement stmtImg = null;	// insert
		ResultSet rs = null;
		String sqlSeq = """
					select seq_goods.nextval from dual
				""";
		String sqlGoods = """
					insert into goods(goods_code, goods_name, goods_price, soldout, emp_code, point_rate, createdate)
					values(?, ?, ?, null, ?, ?, sysdate)
				""";
		String sqlImg = """
					insert into goods_img(goods_code, filename, origin_name, content_type, filesize, createdate)
					values(?, ?, ?, ?, ?, sysdate)
				""";
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false); // 단일 트랜잭션안에서 시퀀스 생성 -> 상품입력 -> 이미지 입력
			
			// 1) seq_goods.nextval값을 먼저 생성 후 사용
			stmtSeq = conn.prepareStatement(sqlSeq);
			rs = stmtSeq.executeQuery();
			rs.next();
			int goodsCode = rs.getInt(1);
			
			// 2) goods 입력
			stmtGoods = conn.prepareStatement(sqlGoods);
			stmtGoods.setInt(1, goodsCode);
			stmtGoods.setString(2, g.getGoodsName());
			stmtGoods.setInt(3, g.getGoodsPrice());
			stmtGoods.setInt(4, g.getEmpCode());
			stmtGoods.setDouble(5, g.getPointRate());
			int row = stmtGoods.executeUpdate();
			
			// 상품 입력에 실패하면
			if(row != 1) {
				conn.rollback(); 
				return false;
			}
			
			// 3) img입력
			stmtImg = conn.prepareStatement(sqlImg);
			stmtImg.setInt(1, goodsCode);
			stmtImg.setString(2, gi.getFilename());
			stmtImg.setString(3, gi.getOriginName());
			stmtImg.setString(4, gi.getContentType());
			stmtImg.setLong(5, gi.getFilesize());
			int row2 = stmtImg.executeUpdate();
			
			if(row2 != 1) {	// 입력실패
				// conn.rollback(); return false;
				throw new SQLException();
			}
			
			result = true;	// 상품 & 이미지 입력 성공
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<Goods> SelectGoodsList(int beginRow, int rowPerPage) {
		List<Goods> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					SELECT goods_code, goods_name, goods_price, soldout, emp_code, point_rate, createdate 
					FROM goods
					OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Goods g = new Goods();
				g.setGoodsCode(rs.getInt("goods_code"));
				g.setGoodsName(rs.getString("goods_name"));
				g.setGoodsPrice(rs.getInt("goods_price"));
				g.setSoldout(rs.getString("soldout"));
				g.setEmpCode(rs.getInt("emp_code"));
				g.setPointRate(rs.getDouble("point_rate"));
				g.setCreatdate(rs.getString("createdate"));
				list.add(g);
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
	
	public List<Map<String, Object>> selectGoodsListForCust(int beginRow, int rowPerPage) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select gi.filename filename, g.goods_code goodsCode, g.goods_name goodsName
							, g.goods_price goodsPrice, g.soldout soldout, g.emp_code empCode
							, g.point_rate pointRate
					FROM goods g inner join goods_img gi on g.goods_code = gi.goods_code
					where g.soldout is null
					order by g.goods_code desc
					OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("filename",rs.getString("filename"));
				m.put("goodsCode",rs.getInt("goodsCode"));
				m.put("goodsName",rs.getString("goodsName"));
				m.put("goodsPrice",rs.getInt("goodsPrice"));
				m.put("soldout",rs.getString("soldout"));
				m.put("empCode",rs.getInt("empCode"));
				m.put("pointRate",rs.getDouble("pointRate"));
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
	
	public List<Map<String, Object>> bestGoodsList() {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;	// insert
		ResultSet rs = null;
		
		String sql = """
					select gi.filename filename, g.goods_code goodsCode, g.goods_name goodsName
							, g.goods_price goodsPrice,
					FROM goods g inner join goods_img gi on g.goods_code = gi.goods_code
					where g.soldout is null
					order by g.goods_code desc
					OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("filename",rs.getString("filename"));
				m.put("goodsCode",rs.getInt("goodsCode"));
				m.put("goodsName",rs.getString("goodsName"));
				m.put("goodsPrice",rs.getInt("goodsPrice"));
				m.put("soldout",rs.getString("soldout"));
				m.put("empCode",rs.getInt("empCode"));
				m.put("pointRate",rs.getDouble("pointRate"));
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
	
	// 총 제품 수 확인
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
	
	// 제품 soldout 변경
	public String ModifyGoodsSoldout(int goodsCode) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement selectStmt = null;
		PreparedStatement updateStmt = null;
		ResultSet rs = null;
		String selectSql = """
				SELECT soldout FROM goods WHERE goods_code = ?
			""";
		String updateSql = """
				UPDATE goods SET soldout = ? WHERE goods_code = ?
			""";
		// 제품의 soldout 상태 먼저 받아오기
		selectStmt = conn.prepareStatement(selectSql);
		selectStmt.setInt(1, goodsCode);
		rs = selectStmt.executeQuery();
		rs.next();
		String currentSO = rs.getString(1);
		System.out.println("GoodsDao ========> crrSO: " + currentSO);
		String newSO = (currentSO == "N") ? null : "N";
		
		// 제품 상태 업데이트
		updateStmt = conn.prepareStatement(updateSql);
		updateStmt.setString(1, newSO);
		updateStmt.setInt(2, goodsCode);
		int row = updateStmt.executeUpdate();
		
		if( row == 1) return newSO;
		else return "sql update 실패";
	}
}
