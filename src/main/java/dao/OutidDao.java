package dao;

import java.sql.*;
import dto.*;

public class OutidDao {
	public int CustomerWithrawal(Outid outid) throws SQLException {
		Connection conn = DBConnection.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		// outid에 데이터 추가
		sql = """
				INSERT INTO outid(id, memo, createdate) VALUES(?, ?, sysdate)
			""";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, outid.getId());
		stmt.setString(2, outid.getMemo());
		int outidInsert = stmt.executeUpdate();
		
		// customer date 삭제
		if(outidInsert == 1) {
			
		}
		return 0;
	}
}
