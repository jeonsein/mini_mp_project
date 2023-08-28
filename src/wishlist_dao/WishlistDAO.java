package wishlist_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			throw new Exception("찜 리스트에 추가하지 못했습니다.: " + e.getMessage());
		} // try-catch

	} // addWish()
	
//	--------------------------------------------------------------------------------
	
	// 위시리스트에 이미 추가되어있는지 아닌지를 확인하는 메소드
	@Override
	public boolean isAlreadyWished(String userId, String resId) throws Exception {
	    
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango";
		String password = "mango";
	    
	    String checkSQL = "SELECT COUNT(*) FROM wish_list WHERE id = ? AND res_id = ?";
	    
	    try {
	        conn = DriverManager.getConnection(url, user, password);
	        
	        pstmt = conn.prepareStatement(checkSQL);
	        pstmt.setString(1, userId);
	        pstmt.setString(2, resId);
	        
	        rs = pstmt.executeQuery();
	        
	        // 이미 wish_list에 있다면 true, 아니라면 false 반환
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            
	            return count > 0;
	        }
	        
	    } catch (Exception e) {
	        throw new Exception("찜 리스트 조회를 실패하였습니다.: " + e.getMessage());
	    } finally {
	    	rs.close();
	    	pstmt.close();
	    	conn.close();
	    } // try-catch-finally
	    
	    return false;
	}

} // end class