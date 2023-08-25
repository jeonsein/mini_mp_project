package wishlist_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import db.DBConnect;
import member_dto.MemberDTO;
import restaurant_dto.RestaurantDTO;


public class WishlistDAO implements WishlistDAOInterface {

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
	
	public WishlistDAO() {
		DBConnect dbconn = DBConnect.getInstance();
		conn = dbconn.getConnection();
	}
	
	public Connection getConn() {
		return conn;
	}
	
//	--------------------------------------------------

	// 찜하기
	@Override
	public static void addWish(int rowNumber) {
		
        Scanner sc = new Scanner(System.in);

        // 사용자 입력을 받아 MemberDTO 클래스에 저장
        MemberDTO id = new MemberDTO();

        System.out.print("사용자 ID를 입력하세요: ");
        String userId = sc.nextLine();

        // MemberDTO 객체에 사용자 아이디 설정
        id.setId(userId);

        System.out.print("원하는 식당의 번호를 입력하세요: ");
        
        int rowNumber = sc.nextInt();
        sc.nextLine(); // 개행 문자 제거

        // 입력된  값을 사용하여(식당 키값) 식당명 조회
        String getRes_idByRowNum = getResInfoByRowNum(rowNumber);
        
        System.out.println("0. 이전 페이지로 1. 찜하기");
        
        int selectedNum = sc.nextInt();
        
        
        if (selectedNum == 1) {

            // 좋아요 추가 여부 확인
            System.out.print("이 식당을 찜 하시겠습니까? ( y / n): ");
            
            String choice = sc.nextLine();
            
            if ("y".equalsIgnoreCase(choice)) {
            	
                // 사용자가 입력한 id를 사용하여 좋아요 추가
                String memberId = id.getId();
                
                if (WishlistDAOInterface.addWish(res_id, memberId)) {
                    System.out.println("찜 추가 성공");
                } else {
                    System.out.println("찜 추가 실패");
                } // if-else
                
            } // if
            
        } else {
            System.out.println("해당 식당이 없습니다.");
        } // if-else

	    public static String getResInfoByRowNum(int rowNumber) {

	        String sql = "SELECT * FROM (SELECT ROWNUM AS rn, RES_NAME, RES_ID FROM restaurant) WHERE rn = ?";
	        
	        try {
	        	pstmt = conn.prepareStatement(sql); // 송신
	        	
	        	statement.setInt(1, rowNumber);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	                return resultSet.getString("res_id");
	            } else {
	                return null;
	            } // if-else
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        } // try-catch
	    }
	    
        // Scanner 닫기
        sc.close();
        
    } // addWish()
	
	// 찜 기능 구현
	@Override
	public String addWish() 
	
	// 지역별로 맛집 목록 가져오기
	@Override
	public Vector<RestaurantDTO> regionSelect(int num) {
		
		Vector<RestaurantDTO> v = new Vector<RestaurantDTO>();
		
		ResultSet rs = null;
		
		String selectSQL = "SELECT RES_NAME FROM RESTAURANT\r\n"
				+ "WHERE REGION_CODE = ?";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, 2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				v.add(new RestaurantDTO(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		
		
		return v;
	}


} // end class
