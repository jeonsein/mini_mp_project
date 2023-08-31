
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	//커밋
	private static DBConnect db = new DBConnect();
	private Connection conn = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "mango";
	String password = "mango";
	
	private DBConnect() {
		
		// 드라이버 클래스 JVM에 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		// DB와 연결
		try {
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public static DBConnect getInstance() {
		return db;
	}

	public Connection getConnection() {
		return conn;
	}
}
