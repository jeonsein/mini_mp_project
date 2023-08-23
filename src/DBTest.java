import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DBTest {

	public static void selectTest() {
		
		// 1. JDBC 드라이버 설치
		// maven repository가서 jar 다운!!
		
//		-------------------------------------
		
		// 2. 드라이버 클래스들 JVM에 로드 
		// (프로젝트 우클릭 후 BuildPath - add external 어쩌구 설정해서 jar 추가해주기)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC 드라이버 로드 성공 :-)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return; // void로 선언된 method는 return;으로 종료 가능
		} // try-catch
		
//		-------------------------------------
		
		// 3. DB와 연결
		Connection conn = null;
		
		// 프로토콜:사용할DB:드라이버종류:@접속할아이피:포트번호:서비스아이디
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user ="hr";
		String password = "hr";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공 :-)");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} // try-catch
		
//		-------------------------------------
		
		/*
		// No_1) Statement 사용
		
		// 4. SQL문 DB 서버로 송신
		Statement stmt = null;
		
//		try {
//			stmt = conn.createStatement();
//			
//			int dId = 60; // 부서 번호
//			String fn = "D";
//			
//			String selectSQL = 
//					"SELECT employee_id, first_name, hire_date, salary "
//					+ "FROM employees "
//					+ "WHERE department_id = " + dId
//					+ "AND SUBSTR(first_name, 1, 1) = '" + fn + "'";
//				// JAVA에서 SQL문 사용할 때 끝에 세미콜론 붙이지 않음!
//			
//			stmt.executeQuery(selectSQL); // 송신
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} // try-catch
		
		// 5. SQL문 결과 수신하기
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			
			int dId = 60; // 부서 번호
			String fn = "D";
			
			String selectSQL = 
					"SELECT employee_id, first_name, hire_date, salary "
					+ "FROM employees "
					+ "WHERE department_id = " + dId
					+ "AND SUBSTR(first_name, 1, 1) = '" + fn + "'";
				// JAVA에서 SQL문 사용할 때 끝에 세미콜론 붙이지 않음!
		
			// rs: 결과집합수신
			rs = stmt.executeQuery(selectSQL); // 송신
			
			// 출력
			// rs.next() = 한 번 호출할 때마다 1 row씩 이동!
			// -> 결과행이 있으면 true 없으면 false 반환
			while(rs.next()) { // while(rs.next() == true)
			
				int eId =
						rs.getInt("employee_id"); 
						// employee_id 자료형 = NUMBER이지만, 
						// 자바에서는 NUMBER 자료형이 없기 때문에
						// Int로 바꿔서 읽어오기!
				
				String eName = rs.getString("first_name"); // = rs.getString(2);
				
				Date eHdt = rs.getDate("hire_date"); // = rs.getDate(3);
				
				int eSal = rs.getInt("salary");

				System.out.println(eId+ ":" + eName + ":" + eHdt + ":" + eSal);
			
			} // while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 연결 끊기!!! 중요!!
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) {} // try-catch			
			} // if
			
			if(stmt != null) {
				try { stmt.close(); } catch (SQLException e) {}	// try-catch
			} // if
			
			if(conn != null) {
				try { conn.close(); } catch (SQLException e) {}	// try-catch
			} // if
			
		} // try-catch-finally
		
		*/
		
//		-------------------------------------
		
		// No_2.PreparedStatement 사용
		
		// 4. SQL문 DB 서버로 송신
		PreparedStatement pstmt = null;
		
		// 5. SQL문 결과 수신하기
		ResultSet rs = null;
		
		// 바인드 변수 사용할 경우, 컬럼이나 테이블 명에는 사용할 수 없음!
		// 무조건 값에만 사용이 가능!
		String selectSQL = 
				"SELECT employee_id, first_name, hire_date, salary "
				+ "FROM employees "
				+ "WHERE department_id = ?"
				+ "AND SUBSTR(first_name, 1, 1) = ?";

		try {
			pstmt = conn.prepareStatement(selectSQL); // 송신
			// selectSQL의 ?에 해당하는 값들 setting
			pstmt.setInt(1, 60); 
			pstmt.setString(2, "D");
			
			// PreparedStatement에서는 executeQuery()의 매개변수 X
			rs = pstmt.executeQuery(); // ?값(바인드변수)만 보냄
			
			// 출력
			while(rs.next()) { // while(rs.next() == true)
				
				int eId =
						rs.getInt("employee_id"); 
						// employee_id 자료형 = NUMBER이지만, 
						// 자바에서는 NUMBER 자료형이 없기 때문에
						// Int로 바꿔서 읽어오기!
				
				String eName = rs.getString("first_name"); // = rs.getString(2);
				
				Date eHdt = rs.getDate("hire_date"); // = rs.getDate(3);
				
				int eSal = rs.getInt("salary");

				System.out.println(eId+ ":" + eName + ":" + eHdt + ":" + eSal);
			
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			
			// 연결 끊기!!! 중요!!
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) {} // try-catch			
			} // if
			
			if(pstmt != null) {
				try { pstmt.close(); } catch (SQLException e) {}	// try-catch
			} // if
			
			if(conn != null) {
				try { conn.close(); } catch (SQLException e) {}	// try-catch
			} // if
			
		} // try-catch-finally
		
	} // selectTest()

	public static void insertTest() {
		
		// 2. JDBC 드라이버 로드
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("JDBC 드라이버 로드 성공 :-)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		// 3. DB와 연결
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@192.168.1.93:1521:xe";
//		String url = "jdbc:oracle:thin:@192.168.1.84:1521:xe";
//		String url = "jdbc:oracle:thin:@192.168.1.22:1521:xe";
		String user = "hr";
		String password = "hr";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB와 연결 성공 :-)");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} // try-catch
		
		// 4. SQL 구문 송신
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String insertSQL = "INSERT INTO customer(id, pwd, name) VALUES (?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(insertSQL);
			
			pstmt.setString(1, "id21");
			pstmt.setString(2, "p21");
			pstmt.setString(3, "seng");
			
			int rowcnt =
					pstmt.executeUpdate(); 
				// INSERT문 사용할 경우 executeUpdate() 사용!
				// executeUpdate() = 처리된 행 수를 반환함
			
			System.out.println(rowcnt + "건이 추가되었습니다. :-)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 자원해제
			if(pstmt != null) {
				try {pstmt.close();} catch (SQLException e) {}
			}; // if
			
			if(conn != null) {
				try {conn.close();} catch (SQLException e) {}
			}; // if
			
		} // try-catch-finally
		
	} // insertTest()
	
//	----------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		selectTest();
//		insertTest();
		
	} // end main
	
} // end class