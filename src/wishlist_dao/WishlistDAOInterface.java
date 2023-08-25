package wishlist_dao;


public interface WishlistDAOInterface {

	// 찜하기
	@Override
	public String addWish(String storeId, String res_id);
	
	// RowNum으로 RES_ID랑 RES_NAME 불러오기
	@Override
	public String getResInfoByRowNum(int rowNumber);
	/*
	 	SELECT * FROM
			(SELECT ROWNUM as rn, RES_NAME, RES_ID FROM restaurant)
		WHERE rn = ?;
	 */
	
	
} // end interface
