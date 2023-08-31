package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.RegionDAO;
import dao.RestaurantDAO;
import dao.WishlistDAO;
import dto.MemberDTO;
import dto.MenuDTO;
import dto.RegionDTO;
import dto.RestaurantDTO;
import dto.WishlistDTO;

public class RestaurantService {
	
	Scanner sc = new Scanner(System.in);
	
	// DAO -> 데이터베이스와 상호작용을 담당
	RestaurantDAO resDAO = new RestaurantDAO();
	RegionDAO regionDAO = new RegionDAO();
	WishlistDAO wishlistDAO;
	
	// 로그인한 사용자의 정보를 저장
	MemberDTO memberDTO;
	
	// DTO -> 데이터를 전달하는 데 사용
	RestaurantDTO resDTO = new RestaurantDTO();
	WishlistDTO wishlistDTO = new WishlistDTO();
	
	// 식당 & 지역 정보를 담는 리스트
	List<RestaurantDTO> Restaurantlist = new ArrayList<>();
	List<RegionDTO> regionList = new ArrayList<>();

	// 생성자로 로그인한 사용자의 정보를 받아와 초기화
    public RestaurantService(MemberDTO loggedInUserId) {
        this.memberDTO = loggedInUserId;
        this.wishlistDAO = new WishlistDAO(loggedInUserId);  // WishlistDAO에 로그인한 사용자 정보 전달
    }
	
	public static void printSpace() {
		for (int i = 0; i < 60; i++) {
			System.out.println("");
		}
	}

//	----------------------------------------------------
	
	/*
	 * 사용자에게 맛집 리스트를 어떻게 보고 싶은지 선택하게 함.
	 * 지역별로 보기 선택 시 regionList() 메소드를 호출!
	 * 찜 많은 순으로 보기 선택 시 wishBestSelect() 메소드를 호출!
	 */
	// 맛집 리스트 보기
	public void restaurantList() {
		
		while (true) {
			printSpace();
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
	
//	----------------------------------------------------
	
	/*
	 * 사용자에게 지역 목록을 보여주고, 원하는 지역을 선택하게 한 후 regionSelect(int RegionNum) 메소드를 호출.
	 */
	// 지역 리스트 보기
	public void regionList() {
		printSpace();
		regionList = regionDAO.regionList();
		for(RegionDTO regionDTO : regionList) {
			System.out.println(regionDTO);
		}
		System.out.print("원하는 지역을 입력하세요 : ");
		int select = sc.nextInt();
		regionSelect(select);
	}
	
	/*
	 * 선택한 지역의 식당 목록을 보여줌.
	 * 사용자가 원하는 식당을 선택하면 selectRegionRes() 메소드를 호출하고, 찜하기를 선택하면 Wish_list에 추가!
	 */
	// 지역 별로 보기
	public void regionSelect(int RegionNum) {
		printSpace();
		int regionNum = RegionNum;
		Restaurantlist = resDAO.regionSelect(RegionNum);
		int num4 = 1;
		for(RestaurantDTO res : Restaurantlist) {
			System.out.println(num4 + ". " + res);
			
			num4++;
		}
		System.out.print("원하시는 식당을 선택해주세요 : ");
		int select = sc.nextInt();
		// 사용자가 지역별로 정렬된 식당 목록에서 하나를 선택 -> 해당 입력값은 변수 n에 저장
		int n = select;
		String res_id = resDAO.selectRegionRes(regionNum, n).getRes_id();
		while(true) {
			printSpace();
			// selectRegionRes() 메소드 호출 -> 선택한 식당의 정보와 메뉴를 출력
			selectAfterRegionRes(regionNum, res_id);
			System.out.println("원하시는 기능을 선택해주세요.");
			System.out.println("0. 이전 페이지로 돌아가기");
			System.out.println("1. 찜하기");
			select = sc.nextInt();
			
			if(select == 0) {
				break;
			
			} else if(select == 1) {
				
				// 선택한 식당의 아이디를 가져옴!
			    String wish_res_id = resDAO.selectRegionRes(regionNum, n).getRes_id();
			    
			    WishlistDTO wishlistDTO = new WishlistDTO();
			    
			    // // 로그인한 사용자의 id & 식당의 res_id를 wishlistDTO에 설정!
			    wishlistDTO.setId(memberDTO.getId());
			    wishlistDTO.setRes_id(wish_res_id);
			
			    try {
			    	
			    	// 사용자가 이미 찜한 식당인지 확인하는 메소드.
			    	// 이미 찜한 경우, "찜 목록에 이미 등록되어 있습니다!" 메시지 출력!
			    	// 아니면, wishlistDAO.addWish(memberDTO.getId(), wish_res_id);를 호출 -> 찜 리스트에 추가
			        if (wishlistDAO.isAlreadyWished(wishlistDTO)) {
			        	// isAlreadyWished()에서 true 반환할 경우
			            System.out.println("찜 목록에 이미 등록되어 있습니다!");
			        } else {
			        	// isAlreadyWished()에서 false 반환할 경우
			            wishlistDAO.addWish(memberDTO.getId(), wish_res_id);
			            System.out.println("찜 리스트에 추가되었습니다!");
			        } // if-else
			        
			    } catch (Exception e) {
			        System.out.println("찜 리스트 추가 실패하였습니다!" + e.getMessage());
			    } // try-catch
			    
			} // if-else
			
		} // while
	} // regionSelect()
	
	/*
	 * 선택한 지역과 식당에 따른 식당 정보와 메뉴를 출력.
	 */
	// 지역 순에서 원하는 식당 보기
	public void selectRegionRes(int RegionNum, int selectNum) {
		printSpace();
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
	
	/*
	 * 찜 등록 이후, 해당 음식점의 상세 정보와 메뉴를 표시
	 */
	// 지역 순에서 찜 선택 후 원하는 식당 보기
	public void selectAfterRegionRes(int RegionNum, String selectNum) {
		printSpace();
		resDTO = resDAO.selectAfterRegionRes(RegionNum, selectNum);
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
	
//	----------------------------------------------------
	
	/*
	 * 찜이 많이 된 식당순으로 보여줌
	 * 사용자가 원하는 식당을 선택하면 selectWishRes() 메소드를 호출.
	 * 찜하기를 선택하면 Wishl_ist에 추가!
	 */
	// 찜 많은 순으로 보기
	public void wishBestSelect() {
		printSpace();
		Restaurantlist = resDAO.wishBestSelect();
		int num3 = 1;
		for(RestaurantDTO res : Restaurantlist) {
			System.out.println(num3 + ". " + res);
			num3++;
		}
		System.out.print("원하시는 식당을 선택해주세요 : ");
		int select = sc.nextInt();
		// 사용자가 찜 많은 순으로 정렬된 식당 목록에서 하나를 선택 후, 해당 입력값을 wish_n에 저장함!
		int wish_n = select;
		String res_id = resDAO.selectWishRes(wish_n).getRes_id();
		while(true) {
			printSpace();
			// selectWishRes(res_id) 메소드 호출 -> 사용자가 선택한 식당의 정보와 메뉴를 출력.
			selectAfterWishRes(res_id);
			System.out.println("원하시는 기능을 선택해주세요.");
			System.out.println("0. 이전 페이지로 돌아가기");
			System.out.println("1. 찜하기");
			select = sc.nextInt();
			if(select == 0) {
				break;
			} else if(select == 1) {
				// 선택한 식당의 아이디를 가져옴!
			    String wish_res_id = resDAO.selectWishRes(wish_n).getRes_id();
			    
			    WishlistDTO wishlistDTO = new WishlistDTO();
			    
			    // 로그인한 사용자의 id & 식당의 res_id를 wishlistDTO에 설정!
			    wishlistDTO.setId(memberDTO.getId());
			    wishlistDTO.setRes_id(wish_res_id);
			    
			    try {
			    	
			    	// 사용자가 이미 찜한 식당인지 확인하는 메소드.
			    	// 이미 찜한 경우, "찜 목록에 이미 등록되어 있습니다!" 메시지 출력!
			    	// 아니면, wishlistDAO.addWish(memberDTO.getId(), wish_res_id);를 호출 -> 찜 리스트에 추가
			    	if (wishlistDAO.isAlreadyWished(wishlistDTO)) {
			            System.out.println("찜 목록에 이미 등록되어 있습니다!");
			        } else {
			            wishlistDAO.addWish(memberDTO.getId(), wish_res_id);
			            System.out.println("찜 리스트에 추가되었습니다!");
			        } // if-else
			    	
			    } catch (Exception e) {
			        System.out.println("찜 리스트 추가 실패하였습니다!" + e.getMessage());
			    } // try-catch
			    
			} // if-else
			
		} // while
	} // wishBestSelect()
	
	/*
	 * 찜이 많이 된 순서에 따라 선택한 식당의 정보와 메뉴를 출력!
	 */
	// 찜 많은 순에서 원하는 식당 보기
	public void selectWishRes(int select) {
		printSpace();
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
	
	/*
	 * 찜 등록 이후, 해당 음식점의 상세 정보와 메뉴를 표시
	 */
	// 찜 많은 순에서 찜 선택 후 선택 식당 보기
	public void selectAfterWishRes(String select) {
		printSpace();
		resDTO = resDAO.selectAfterWishRes(select);
		
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
}
