import java.util.Scanner;

import lombok.extern.log4j.Log4j2;
import member_dto.MemberDTO;
import wishlist_dao.WishlistDAO;


@Log4j2
public class TestMain {
	
	private MemberDTO loginedMember;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		log.info("id입력: ");
		String id = sc.nextLine();
		
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);

		
//		MemberDTO mdto = new MemberDTO("user1", 5555);
//		String storeid = mdto.getId();
		
		log.info("storeid" + storeid);

		WishlistDAO wd = new WishlistDAO();
		wd.addWish("mango", 9);
		
		/*
		Scanner sc = new Scanner(System.in);
		
        System.out.println("id 입력:");
        String userId = sc.nextLine();
        // 로그인 로직 (생략)
        
        System.out.println("조회할 식당 번호 입력:");
        int rowNum = sc.nextInt();
        
        // 해당 rowNum으로 res_id 조회 로직 (생략)
        int resId = ...; // 조회된 resId
        
        WishlistDAO wishlistDAO = new WishlistDAO();
        
        if (wishlistDAO.addWish(userId, resId)) {
            System.out.println("찜 성공!");
        } else {
            System.out.println("찜 실패");
        }
        
		*/
		
		
	} // end main

} // end class