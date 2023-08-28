package wishlist_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import lombok.extern.log4j.Log4j2;
import wishlist_dto.WishlistDTO;


@Log4j2
public class WishlistDAO implements WishlistDAOInterface {
	// 찜하기 기능의 인터페이스를 정의함!

	@Override
	public void addWish(String userId, String resId) throws Exception {

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

		WishlistDTO wishlistDTO = new WishlistDTO();
		
		// 찜하기
		String addWishSQL = "INSERT INTO wish_list (id, res_id) VALUES (?, ?)";

		// restaurant의 wish_count 업데이트
		String addWishCountSQL = "UPDATE restaurant SET wish_count = wish_count + 1 WHERE res_id = ?";

		try {

			conn = DriverManager.getConnection(url, user, password);
			log.info("addWish() invoked.");

			PreparedStatement addWish_Pstmt = conn.prepareStatement(addWishSQL);
			PreparedStatement addWishCount_Pstmt = conn.prepareStatement(addWishCountSQL);

			// #1
			// wish_list에 사용자의 id랑 식당의 res_id 저장!
            addWish_Pstmt.setString(1, wishlistDTO.getId());
            addWish_Pstmt.setString(2, wishlistDTO.getRes_id());

            // 실행
			addWish_Pstmt.executeUpdate();

			// #2
			// wish_count에 업뎃하기
			addWishCount_Pstmt.setString(1, wishlistDTO.getRes_id());

			// 실행
			addWishCount_Pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception("찜하기 실패: " + e.getMessage());
		} // try-catch

	} // addWish()

} // end class