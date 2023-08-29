package wishlist_dao;

import wishlist_dto.WishlistDTO;

public interface WishlistDAOInterface {
// WishlistDAOInterface를 구현하여 실제 데이터베이스 작업을 수행!
	
	// 사용자가 원하는 식당을 위시리스트에 추가하는 기능
	void addWish(String userId, String resId) throws Exception;
	
	// 위시리스트에 이미 찜해놓은 식당인지 아닌지를 확인
	boolean isAlreadyWished(WishlistDTO wishlistDTO) throws Exception;
	
} // end interface
