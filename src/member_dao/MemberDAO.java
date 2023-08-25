package member_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnect;
	
public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt = null;
	
	public MemberDAO() {
		DBConnect dbConn = DBConnect.getInstance();
		conn = dbConn.getConnection();
	}
	
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
	//회원정보를 불러오는 메소드
	
	public void ShowMember(String id) { 
		ResultSet rs=null;
		
		String SQL ="SELECT ID,TEL,NAME,REGION_CODE"
				+ "FROM MEMBER"
				+ "WHERE ID= ? ";
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
	
	//회원 탈퇴 메소드
	public void DeleteMember(String id) {
			 
		String SQL = "DELETE MEMBER WHERE ID=? ";
		
		try {
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
			return;
		}	 
		
	
	
	//찜한 목록 불러오기 
	public void ShowWishList(String id) {
		ResultSet rs=null;
		
		String SQL = "SELECT m.id \"member의 id\", w.res_id \"wish_list의 식당번호\", r.res_name \"restaurant의 식당이름\", r.res_id \"restaurant의 식당번호\"\r\n"
				+ "FROM member m JOIN wish_list w ON (m.id = w.id)\r\n"
				+ "JOIN restaurant r ON (w.res_id = r.res_id)\r\n"
				+ "WHERE m.id = ?" ; 
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//비밀번호 변경  메소드 
	public void UpdatePwd(String id,String pwd){
		
		String SQL="UPDATE MEMBER SET PWD=? WHERE ID=?";
				
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	//이름 변경 메소드
	public void UpdateName(String id, String name) {
		
		String SQL="UPDATE MEMBER SET NAME=? WHERE ID=?";
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,id);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//번호 변경 메소드
	public void UpdateTel(String id, String region_code) {
		
		String SQL="UPDATE MEMBER SET REGION_ID=? WHERE ID=?"; 
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1,id);
			pstmt.setString(2,region_code);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

}
