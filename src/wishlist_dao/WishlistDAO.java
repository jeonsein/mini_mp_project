package wishlist_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnect;


public class WishlistDAO implements WishlistDAOInterface {

	private Connection conn;
	private PreparedStatement pstmt = null;
	
	public WishlistDAO() {
		DBConnect dbconn = DBConnect.getInstance();
		conn = dbconn.getConnection();
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public void close(ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		} // if#1
		
		if(pstmt !=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		} // if#2
		
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		} // if#3
		
	} // close()
	
//	--------------------------------------------------
	
	// 찜하기
    @Override
    public void addWish(String userId, int resId) {
        // 1. wish_list에 이미 해당 userId와 resId로 저장된 데이터가 있는지 확인 -> 목록 확인 XX
        // 2. 없다면 wish_list에 저장 -> XX
    	
    	// 1. addWish()의 인자값으로 userId랑 res_id를 받아와서
    	// 2. insert into ~ 구문 실행시키고
        // 3. restaurant의 wish_count를 1 증가
    	
    	ResultSet rs = null;
    	
    	// 찜 리스트 추가
    	String insertSql = "INSERT INTO wish_list (res_id, id) values (?, ?)";
    	
    	try {
			pstmt = conn.prepareStatement(insertSql);
			pstmt.setInt(1, 9);
			pstmt.setString(2, "mango");
			
			rs = pstmt.executeQuery();
    
			System.out.println("찜 리스트 추가 성공~");	
			
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    
    } // addWish()

} // end class

// -----------------------

/*
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
*/