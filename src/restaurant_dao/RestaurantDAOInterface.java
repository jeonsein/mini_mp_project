package restaurant_dao;

import java.util.Vector;

import restaurant_dto.RestaurantDTO;

public interface RestaurantDAOInterface {
	
	// 지역 별로 보기
	public Vector<Object> regionSelect(int num);
	
	// 찜 많은 순으로 보기
	public Vector<Object> wishBestSelect();
	
	// 찜 많은 순에서 원하는 식당 보기
	public Object selectWishRes(int num);

	// 지역 순에서 원하는 식당 보기
	public Object selectRegionRes(int num);
	
}