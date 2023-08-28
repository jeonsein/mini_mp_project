package wishlist_dao;


public interface WishlistDAOInterface {
// WishlistDAOInterface를 구현하여 실제 데이터베이스 작업을 수행!
	
	// 찜하기 기능
	void addWish(String userId, String resId) throws Exception;
	
} // end interface
