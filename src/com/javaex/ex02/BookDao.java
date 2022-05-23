package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	//필드
	//생성자
	//메소드 gs
	//메소드 일반
	
	/////책 입력하기 메소드/////
	public void bookInsert(String title, String pubs, String pubDate, int authorId) {
		
		int count = -1;
		
		// 0. import java.sql.*;
			Connection conn = null;
			PreparedStatement pstmt = null;
			//ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " insert into book ";
			query += " values(SEQ_BOOK_ID.nextval, ?, ?, ?, ?) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			//실행
			count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
//			if (rs != null) {
//				rs.close();
//			} 
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
	
	/////책 삭제하기 메소드/////
	// 0. import java.sql.*;
	public int bookDelete(int bookId) {
		int count = -1;
	
			Connection conn = null;
			PreparedStatement pstmt = null;
			//ResultSet rs = null;
			try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//SQL문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			//실행
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");
			
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} finally {
				// 5. 자원정리
				try {
					/*
					 * if (rs != null) { rs.close(); }
					 */
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
		return count;
	}
	/////책 수정하기 메소드/////
	public int bookUpdate(String title, String pubs, String pubDate, int authorId, int bookId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "";
			query += " update book ";
			query += " set title = ?, ";
			query += " 	   pubs = ?, ";
			query += "     pub_date = ?, ";
			query += "     author_id = ? ";
			query += " where book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 업데이트되었습니다.");
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
		return count;
	}
	
	/////책 전체리스트가져오기 메소드/////
	@SuppressWarnings("finally")
	public List<BookVo> bookSelect(){
		//리스트로 만들기
		List<BookVo> bookList = new ArrayList<BookVo>();
		// 0. import java.sql.*;
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
		//SQL문 준비
		String query = "";
		
		query += " select	book_id,";
		query += " 			title,";
		query += " 			pubs,";
		query += " 			to_char(pub_date, ?),";
		query += " 			b.author_id,";
		query += " 			author_name,";
		query += " 			author_desc";
		query += " from book b left outer join author a";
		query += " on b.author_id = a.author_id";
		query += " order by book_id";
		
		//바인딩
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "YY/MM/DD");
		
		//실행
		//RsultSet가져오기
		rs = pstmt.executeQuery();
		
		// 4.결과처리
		//반복문으로 Vo만들기 list에 추가
		while(rs.next()) {
			int bookId = rs.getInt(1);
			String title = rs.getString(2);
			String pubs = rs.getString(3);
			String pubDate = rs.getString(4);
			int authorId = rs.getInt(5);
			String authorName = rs.getString(6);
			String authorDesc = rs.getString(7);
			
			BookVo allVo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
			
			bookList.add(allVo);
		}
		
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
		
			return bookList;
		}
		

	}


























}	
