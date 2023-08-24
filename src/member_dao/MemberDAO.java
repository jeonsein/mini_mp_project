package member_dao;

import java.sql.Connection;
	
public class MemberDAO {
	 public static void WishList() {
			 
		 try {
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("JDBC드라이버 로드성공");
			 } catch (ClassNotFoundException e) {
				e.printStackTrace();
				return;
			 }
		//3.db와 연결
			 Connection conn=null;
			 String url ="";
			 String user="mango";
			 String password="mango"; 
			 
			 
	 
	
	 
	 
	 
	 }	 
}
