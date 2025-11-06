package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.*;

public class GoodsDao {
	// 상품등록 + 이미지등록
	// 반환값은 실패시 false
	public boolean insertGoodsAndImg(Goods g, GoodsImg gi) {
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
}
