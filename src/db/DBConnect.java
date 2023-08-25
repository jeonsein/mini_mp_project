package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	private static DBConnect db = new DBConnect();
	private Connection conn = null;
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "mango";
	String password = "mango";
	
	private DBConnect() {
		
		// 드라이버클래스 JVM에 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC드라이버 로드성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		// DB와 연결
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공");
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
