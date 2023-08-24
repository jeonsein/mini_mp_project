package restaurant_dao;

import java.util.Vector;

import restaurant_dto.RestaurantDTO;

public interface RestaurantDAOInterface {
	
	// 지역 별로 보기
	public Vector<RestaurantDTO> regionSelect(int num);
	
}