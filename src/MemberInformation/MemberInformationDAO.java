package MemberInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnect;

public class MemberInformationDAO {
	//커밋
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

	public MemberInformationDAO() {
		DBConnect dbconn = DBConnect.getInstance();
		conn = dbconn.getConnection();
	}
	
	public Connection getConn() {
		return conn;
	}
	
//회원정보를 불러오는 메소드

	public void ShowMember(String id) { 
		
		ResultSet rs = null; 
		
		String selectSQL = "SELECT ID,TEL,NAME,REGION_CODE\r\n "
				+ "FROM MEMBER WHERE member.id=? ";
		try {
			pstmt= conn.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String ID = rs.getString("ID");
				String TEL = rs.getString("TEL");
				String NAME= rs.getString("NAME");
				int REGION_CODE= rs.getInt("REGION_CODE"); 
				   System.out.println("아이디: " + ID);
		           System.out.println("전화번호: " + TEL);
		           System.out.println("이름: " + NAME);
		           System.out.println("지역코드: " + REGION_CODE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		
		String SQL = "SELECT m.id, w.res_id , r.res_name, r.res_id "
					+"FROM member m JOIN wish_list w ON (m.id = w.id) " 
					+"JOIN restaurant r ON (w.res_id = r.res_id) "
					+"WHERE m.id =?";
		
		try {
			pstmt= conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String  M_ID = rs.getString("id");
				String  W_ID = rs.getString("res_id");
				String  R_NAME= rs.getString("res_name");
				String  R_ID= rs.getString("res_id"); 
			    System.out.println("아이디: " + M_ID); 
	            System.out.println("위시리스트 아이디: " +  W_ID); 
	            System.out.println("레스토랑 이름: " +  R_NAME); 
	            System.out.println("레스토랑 아이디: " + R_ID); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	//비밀번호 변경  메소드 
	public void UpdatePwd(int pwd,String id){
		 
		
//		String SQL="UPDATE MEMBER SET PWD=? WHERE ID=?";
		
		 
		
		String SQL="UPDATE MEMBER \r\n"
				+ "SET PWD= ?\r\n"
				+ "where ID= ?";
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, pwd);
			pstmt.setString(2, id);
			int num = pstmt.executeUpdate();
			System.out.println(num + "건 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return;
	}

	//이름 변경 메소드
	public void UpdateName(String id, String name) {
		
		String SQL="UPDATE MEMBER SET NAME=? WHERE ID=? ";
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	//지역 변경 메소드
	public void UpdateReg(int region_code,String id) {
		 
		
		String SQL="UPDATE MEMBER \r\n"
				+ "SET REGION_CODE= ?\r\n"
				+ "where ID= ?"; 
			
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1,region_code);
			pstmt.setString(2,id);
			int num = pstmt.executeUpdate();
			System.out.println(num + "건 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return;
	}

	//번호 변경 메소드 
	public void UpdateTel(String id,String Tel) {
		
		String SQL="UPDATE MEMBER SET TEL=? WHERE ID=?"; 
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, Tel);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	//찜삭제
	public void DeleteWishList(String res_id,String id ) {
		
		String SQL="DELETE FROM WISH_LIST WHERE res_id=? AND id= ?";
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, res_id);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
