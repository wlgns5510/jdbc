package com.javaex.ex01;

//0. import java.sql.*;
import java.sql.*;

public class BookUpdate {
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//준비
            String query = "";
            query += " update book";
            query += " set title = ?,";
            query += "     pubs = ?,";
            query += "     pub_date = ?";
            query += " where book_id= ?";
            
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "1984");
			pstmt.setString(2, "1984");
			pstmt.setString(3, "1984-04-29");
			pstmt.setInt(4, 2);
			
			//실행
			int count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count + " 행 이(가) 업데이트되었습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
}