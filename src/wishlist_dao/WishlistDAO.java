package wishlist_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db.DBConnect;

public class WishlistDAO {

	private Connection conn;
	private PreparedStatement pstmt = null;
	
	public void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		
		if(pstmt !=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public WishlistDAO() {
		DBConnect dbconn = DBConnect.getInstance();
		conn = dbconn.getConnection();
	}
	
	public Connection getConn() {
		return conn;
	}
	

	// 찜하기
	public static void addWish() {
		
	}

} // end class
