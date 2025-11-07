package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import dto.*;

public class NoticeDao {
	// /emp/noticeList
	public int selectCount() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
					SELECT COUNT(*) FROM notice
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch(Exception e) {
//			conn.rollback();
			e.printStackTrace();	// 콘솔에 에러 메세지 출력
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		System.out.println("NoticeDao ======> cnt: " + cnt);
		return cnt;
	}
	
	public List<Notice> selectNoticeList(int beginRow, int rowPerPage) {
		List<Notice> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
					SELECT notice_code, notice_title, emp_code, createdate 
					FROM notice ORDER BY createdate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Notice n = new Notice();
				n.setNoticeCode(rs.getInt("notice_code"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setEmpCode(rs.getInt("emp_code"));
				n.setCreatedate(rs.getString("createdate"));
				list.add(n);
			}
		} catch(Exception e) {
//			conn.rollback();
			e.printStackTrace();	// 콘솔에 에러 메세지 출력
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	// emp/addNotice
	public int insertNotice(Notice n) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = """
					
				""";
		try {
			
		} catch(Exception e) {
//			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return row;
	}
	// /emp/noticeOne
	public Map<String, Object> selectNoticeOne(int noticeCode) {
		Map<String, Object> m = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = """
					SELECT n.notice_code code, n.notice_title title, n.notice_content content, e.emp_name empName, n.createdate createdate
					FROM notice n INNER JOIN emp e ON n.emp_code = e.emp_code
					WHERE n.notice_code = ?
				""";
		try {
			conn = DBConnection.getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeCode);
			rs = stmt.executeQuery();
			if(rs.next()) {
				m = new HashMap<String, Object>();
				m.put("code", rs.getInt("code"));
				m.put("title", rs.getString("title"));
				m.put("content", rs.getString("content"));
				m.put("empName", rs.getString("empName"));
				m.put("createdate", rs.getString("createdate"));
			}
		} catch(Exception e) {
//			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return m;
		
	}
	
	// modify
	
	// delete
}
