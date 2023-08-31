package dao;

import java.util.List;

import dto.MenuDTO;

public interface MenuDAOInterface {
	
	// 찜 많은 순에서 선택 식당 메뉴 보기
	public List<MenuDTO> selectWishMenu(int num);

}
