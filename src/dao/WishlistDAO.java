package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.MemberDTO;
import dto.WishlistDTO;


// 찜하기 기능의 인터페이스를 정의함!
public class WishlistDAO implements WishlistDAOInterface {

	
	// 로그인한 사용자의 정보를 저장
    MemberDTO memberDTO;

    // 로그인한 사용자의 정보 (MemberDTO)를 받아서 필드에 저장
    public WishlistDAO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }
//	해당 생성자는 WishlistDAO 클래스의 인스턴스를 생성할 때 호출됨! 
//	생성자는 MemberDTO 타입의 인자를 받아서 클래스의 필드 memberDTO에 저장! 
//	-> 클래스의 다른 메서드에서도 로그인한 사용자의 정보를 접근할 수 있음!
	
	@Override
	public void addWish(String userId, String resId) throws Exception {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
//			log.info("addWish()_JDBC 드라이버 로드 성공 :-)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return; // void로 선언된 method는 return;으로 종료 가능
		} // try-catch

		Connection conn = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango";
		String password = "mango";
		
		// 전달받은 userId와 resId를 WishlistDTO 객체에 설정! 
		// -> WishlistDTO 객체에 사용자의 id와 res_id를 저장함
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setId(userId);
		wishlistDTO.setRes_id(resId);

		// 찜하기
		String addWishSQL = "INSERT INTO wish_list (id, res_id) VALUES (?, ?)";

		// restaurant의 wish_count 업데이트
		String addWishCountSQL = "UPDATE restaurant SET wish_count = wish_count + 1 WHERE res_id = ?";

		try {

			conn = DriverManager.getConnection(url, user, password);
//			log.info("addWish() invoked.");

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
		} finally {
			
            try {
                if (pstmt != null) {
                	pstmt.close();
                } // if
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } // try-catch
            
            try {
                if (conn != null) {
                	conn.close();
                } // if
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } // try-catch
            
        } // try-catch-finally

	} // addWish()
	
//	--------------------------------------------------------------------------------
	
	// 위시리스트에 이미 추가되어있는지 아닌지를 확인하는 메소드
	@Override
	public boolean isAlreadyWished(WishlistDTO wishlistDTO) throws Exception {
	    
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "mango";
		String password = "mango";
	    
		// 카운트 함수 사용해서 해당 레코드 수 반환!!
	    String isAlreadyWishedSQL = "SELECT COUNT(*) FROM wish_list WHERE id = ? AND res_id = ?";
	    
	    try {
	        conn = DriverManager.getConnection(url, user, password);
	        
	        pstmt = conn.prepareStatement(isAlreadyWishedSQL);
	        
	        pstmt.setString(1, wishlistDTO.getId());
	        pstmt.setString(2, wishlistDTO.getRes_id());
	        
	        rs = pstmt.executeQuery();
	        
	        // 이미 wish_list에 있다면 true, 아니라면 false 반환
	        if (rs.next()) {
	        	// ResultSet의 현재 레코드에서 첫 번째 컬럼의 값 (= count(*)의 결과) 
	            int count = rs.getInt(1);
	            
	            // SQL 구문 실행 후 카운트 변수가 0보다 크면 -> 해당 데이터가 이미 db에 존재함 = 중복!
	            return count > 0;
	        } // if
	        
	    } catch (Exception e) {
	        throw new Exception("찜 리스트 조회를 실패하였습니다.: " + e.getMessage());
        } finally {
        	
            try {
                if (rs != null) {
                	rs.close();
                } // if
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } // try-catch
            
            try {
                if (pstmt != null) {
                	pstmt.close();
                } // if
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } // try-catch
            
            try {
                if (conn != null) {
                	conn.close();
                } // if
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } // try-catch
            
	    } // try-catch-finally
	    
	    return false;
	    
	} // isAlreadyWished()

} // end class