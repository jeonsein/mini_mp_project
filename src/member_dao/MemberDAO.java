package member_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
	
public class MemberDAO {
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
	
	public void ShowMember(String id) {
		ResultSet rs=null;
		
		String SQL ="SELECT ID,TEL,NAME,REGION_CODE"
				+ "FROM MEMBER"
				+ "WHERE ID= '?' ";
		try {
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return;
	}
	
	
	public void DeleteMember(String id) {
			 
	ResultSet rs=null;
	
	String SQL = "DELETE MEMBER WHERE ID='?'; ";
	
	try {
		pstmt =conn.prepareStatement(SQL);
		pstmt.setString(1, SQL);
		rs=pstmt.executeQuery();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		
	}
		return;
	
	}	 
	
	public void ShowWishList() {
		ResultSet rs=null;
		
		String SQL = "SELECT m.id \"member의 id\", w.res_id \"wish_list의 식당번호\", r.res_name \"restaurant의 식당이름\", r.res_id \"restaurant의 식당번호\"\r\n"
				+ "FROM member m JOIN wish_list w ON (m.id = w.id)\r\n"
				+ "JOIN restaurant r ON (w.res_id = r.res_id)\r\n"
				+ "WHERE m.id = '?'";
	}



}
