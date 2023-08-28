package wishlist_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class WishlistDAO implements WishlistDAOInterface {
// 찜하기 기능의 인터페이스를 정의함!

	@Override
	public void addWish(String userId, int resId) throws Exception {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("addWish()_JDBC 드라이버 로드 성공 :-)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return; // void로 선언된 method는 return;으로 종료 가능
		} // try-catch

		Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango";
		String password = "mango";

		// 찜하기
		String addWishSQL = "INSERT INTO wish_list (user_id, res_id) VALUES (?, ?)";
		
		// restaurant의 wish_count 업데이트
		String addWishCountSQL = "UPDATE restaurant SET wish_count = wish_count + 1 WHERE res_id = ?";

		try {

			conn = DriverManager.getConnection(url, user, password);
			log.info("addWish() invoked.");

			PreparedStatement addWish_Pstmt = conn.prepareStatement(addWishSQL);
			PreparedStatement addWishCount_Pstmt = conn.prepareStatement(addWishCountSQL);

			// Insert!
			addWish_Pstmt.setString(1, userId);
			addWish_Pstmt.setInt(2, resId);

			addWish_Pstmt.executeUpdate();

			// wish_count에 업뎃하기
			addWishCount_Pstmt.setInt(1, resId);

			addWishCount_Pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception("찜하기 실패: " + e.getMessage());
		} // try-catch

	} // addWish()

//    ------------------------------------------------------------------

	// 사용자 입력값으로 res_id 얻어오기!
	@Override
	public int getResIdByRowNum(int rowNum) {

		int resId = 0;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			log.info("addgetResIdByRowNumWish()_JDBC 드라이버 로드 성공 :-)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // try-catch

		Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango";
		String password = "mango";

		String getResIdSQL = "SELECT * FROM (SELECT res_id, ROWNUM as r FROM restaurant) WHERE r =  ?";

		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(url, user, password);
			log.info("addgetResIdByRowNumWish() invoked.");

			PreparedStatement pstmt = conn.prepareStatement(getResIdSQL);
			pstmt.setInt(1, rowNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				resId = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				// isClosed() 메소드는 소켓이 닫혀있는 경우 true, 아닌 경우 false를 반환!
				// 상태가 불확실한 경우 IOException이 발생할 위험을 감수하는 것 보다 이 메소드를 사용하는 것이 좋음!
				if (conn != null && !conn.isClosed()) {
					conn.close();
				} // if

			} catch (SQLException e) {
				e.printStackTrace();
			} // try-catch

		} // try-catch-finally

		return resId;

	} // getResIdByRowNum()

} // end class