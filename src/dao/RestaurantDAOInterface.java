package dao;

import java.util.List;
import java.util.Vector;

import dto.RestaurantDTO;

public interface RestaurantDAOInterface {
	
	// 지역 별로 보기
	public List<RestaurantDTO> regionSelect(int num);
	
	// 찜 많은 순으로 보기
	public List<RestaurantDTO> wishBestSelect();
	
	// 지역 순에서 원하는 식당 보기
	public RestaurantDTO selectRegionRes(int RegionNum, int selectNum);
	
	// 찜 많은 순에서 원하는 식당 보기
	public RestaurantDTO selectWishRes(int num);
	
	// 지역 순에서 찜 선택 후 원하는 식당 보기
	public RestaurantDTO selectAfterRegionRes(int RegionNum, String selectNum);
	
	// 찜 많은 순에서 찜 선택 후 선택 식당 보기
	public RestaurantDTO selectAfterWishRes(String num);
}