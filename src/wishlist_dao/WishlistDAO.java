package wishlist_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class WishlistDAO implements WishlistDAOInterface {
// 찜하기 기능의 인터페이스를 정의함!
	
    @Override
    public void addWish(String userId, int resId) throws Exception {
        
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		System.out.println("JDBC 드라이버 로드 성공 :-)");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    		return; // void로 선언된 method는 return;으로 종료 가능
    	} // try-catch
    	
    	Connection conn = null;
    	
    	String url ="jdbc:oracle:thin:@localhost:1521:xe";
    	String user ="hr";
    	String password = "hr";
    	
    	// 찜하기
    	String addWishSQL = "INSERT INTO wish_list (user_id, res_id) VALUES (?, ?)";
        // restaurant의 wish_count 업데이트
    	String addWishCountSQL = "UPDATE restaurant SET wish_count = wish_count + 1 WHERE res_id = ?";

        try {

        	conn = DriverManager.getConnection(url, user, password);
        	System.out.println("DB와 연결 성공 :-)");
        	
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
        
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    		System.out.println("JDBC 드라이버 로드 성공 :-)");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} // try-catch
    	
    	Connection conn = null;
    	
    	String url ="jdbc:oracle:thin:@localhost:1521:xe";
    	String user ="hr";
    	String password = "hr";
    	
    	String getResIdSQL = "SELECT res_id FROM (SELECT res_id, ROWNUM as r FROM restaurant) WHERE r = ?";

        try {

        	conn = DriverManager.getConnection(url, user, password);
        	System.out.println("DB와 연결 성공 :-)");
    	
        	int resId = -1;
        
            PreparedStatement pstmt = conn.prepareStatement(getResIdSQL);

            pstmt.setInt(1, rowNum);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    resId = rs.getInt("res_id");
                } // if
            } // inner-try
            
        } catch (Exception e) {
            e.printStackTrace();
        } // try-catch

        return resId;
        
    } // getResIdByRowNum()

} // end class