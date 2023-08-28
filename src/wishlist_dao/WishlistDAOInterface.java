package wishlist_dao;


public interface WishlistDAOInterface {
// WishlistDAOInterface를 구현하여 실제 데이터베이스 작업을 수행!
	
	// 찜하기 기능
	void addWish(String userId, int resId) throws Exception;
    
	// 사용자 입력값(= rowNum) 받아서 res_id 가지고 오기@
    int getResIdByRowNum(int rowNum);
	
} // end interface
