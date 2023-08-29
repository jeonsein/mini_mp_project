package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import menu_dto.MenuDTO;
import region_dao.RegionDAO;
import region_dto.RegionDTO;
import restaurant_dao.RestaurantDAO;
import restaurant_dto.RestaurantDTO;
import wishlist_dao.WishlistDAO;
import wishlist_dto.WishlistDTO;

public class RestaurantService {
	
	String loggedInUserId = "";
	
	Scanner sc = new Scanner(System.in);
		
	RestaurantDAO resDAO = new RestaurantDAO();
	RestaurantDTO resDTO = new RestaurantDTO();
	
	RegionDAO regionDAO = new RegionDAO();
	
	WishlistDAO wishlistDAO = new WishlistDAO();
	WishlistDTO wishlistDTO = new WishlistDTO();
	
	List<RestaurantDTO> Restaurantlist = new ArrayList<>();
	List<RegionDTO> regionList = new ArrayList<>();

	// 맛집 리스트 보기
	public void restaurantList() {

	    System.out.println("아이디 입력: ");
	    String userId = sc.nextLine();

	    loggedInUserId = userId;
		
		while (true) {
			System.out.println("0. 이전 페이지로 돌아가기");
			System.out.println("1. 지역별로 보기");
			System.out.println("2. 찜 많은 순으로 보기");
			System.out.print("선택 입력 : ");
			
			int select = sc.nextInt();
			
			if(select == 0) {
				break;
			} else if(select == 1) {
				regionList();
			} else if(select == 2) {
				wishBestSelect();
			}
		}
	} // end of restaurantList
	
	// 지역 리스트 보기
	public void regionList() {
		regionList = regionDAO.regionList();
		for(RegionDTO regionDTO : regionList) {
			System.out.println(regionDTO);
		}
		System.out.print("원하는 지역을 입력하세요 : ");
		int select = sc.nextInt();
		regionSelect(select);
	}
	
	// 지역 별로 보기
	public void regionSelect(int RegionNum) {
		int regionNum = RegionNum;
		Restaurantlist = resDAO.regionSelect(RegionNum);
		int num4 = 1;
		for(RestaurantDTO res : Restaurantlist) {
			System.out.println(num4 + ". " + res);
			
			num4++;
		}
		System.out.print("원하시는 식당을 선택해주세요 : ");
		int select = sc.nextInt();
		while(true) {
			int n = select;
			selectRegionRes(regionNum, select);
			System.out.println("원하시는 기능을 선택해주세요.");
			System.out.println("0. 이전 페이지로 돌아가기");
			System.out.println("1. 찜하기");
			select = sc.nextInt();
			
			if(select == 0) {
				break;
			
			} else if(select == 1) {
			    String wish_res_id = resDAO.selectRegionRes(regionNum, select).getRes_id();
			    
			    // DTO 객체를 써야할까 굳이...?
			    WishlistDTO wishlistDTO = new WishlistDTO();
			    wishlistDTO.setId(loggedInUserId);
			    wishlistDTO.setRes_id(wish_res_id);
			    
			    try {
			    	
			        if (wishlistDAO.isAlreadyWished(wishlistDTO)) {
			            System.out.println("찜 목록에 이미 등록되어 있습니다!");
			        } else {
			            wishlistDAO.addWish(loggedInUserId, wish_res_id);
			            System.out.println("찜 리스트에 추가되었습니다!");
			        } // if-else
			        
			    } catch (Exception e) {
			        System.out.println("찜 리스트 추가 실패하였습니다!" + e.getMessage());
			    } // try-catch
			} // if-else
			
			
		} // while
	} // regionSelect()
	
	// 지역 순에서 원하는 식당 보기
	public void selectRegionRes(int RegionNum, int selectNum) {
		System.out.println("------ 지역 순에서 원하는 식당 보기");
		resDTO = resDAO.selectRegionRes(RegionNum, selectNum);
		System.out.println(resDTO.getWish_count());
		System.out.println(resDTO.getRes_name());
		System.out.println(resDTO.getRes_tel());
		int num2 = 1;
		for (MenuDTO menu : resDTO.getMenuList()) {
			System.out.println(num2 + ". " + menu);
			num2++;
		}
		System.out.println(resDTO.getLocation());
		System.out.println(resDTO.getRes_info()+"\n");
	}
	
	// 찜 많은 순으로 보기
	public void wishBestSelect() {
		Restaurantlist = resDAO.wishBestSelect();
		int num3 = 1;
		for(RestaurantDTO res : Restaurantlist) {
			System.out.println(num3 + ". " + res);
			num3++;
		}
		System.out.print("원하시는 식당을 선택해주세요 : ");
		int select = sc.nextInt();
		int wish_n = select;
		while(true) {
			selectWishRes(select);
			System.out.println("원하시는 기능을 선택해주세요.");
			System.out.println("0. 이전 페이지로 돌아가기");
			System.out.println("1. 찜하기");
			select = sc.nextInt();
			if(select == 0) {
				break;
			} else if(select == 1) {
			    String wish_res_id = resDAO.selectWishRes(wish_n).getRes_id();
			    
			    WishlistDTO wishlistDTO = new WishlistDTO();
			    
			    wishlistDTO.setId(loggedInUserId);
			    wishlistDTO.setRes_id(wish_res_id);
			    
			    try {
			    	
			    	if (wishlistDAO.isAlreadyWished(wishlistDTO)) {
			            System.out.println("찜 목록에 이미 등록되어 있습니다!");
			        } else {
			            wishlistDAO.addWish(loggedInUserId, wish_res_id);
			            System.out.println("찜 리스트에 추가되었습니다!");
			        } // if-else
			    	
			    } catch (Exception e) {
			        System.out.println("찜 리스트 추가 실패하였습니다!" + e.getMessage());
			    } // try-catch
			}
			
		} // while
	} // wishBestSelect()
	
	// 찜 많은 순에서 원하는 식당 보기
	public void selectWishRes(int select) {
		resDTO = resDAO.selectWishRes(select);

		System.out.println(resDTO.getWish_count());
		System.out.println(resDTO.getRes_name());
		System.out.println(resDTO.getRes_tel());
		int num1 = 1;
		for (MenuDTO menu : resDTO.getMenuList()) {
			System.out.println(num1 + ". " + menu);
			num1++;
		}
		System.out.println(resDTO.getLocation());
		System.out.println(resDTO.getRes_info()+"\n");
	}

	public static void main(String[] args) {
		
		RestaurantService ResSer = new RestaurantService();
		
		ResSer.restaurantList();
		
	}
	
}