import java.util.Scanner;

import lombok.extern.log4j.Log4j2;
import wishlist_dao.WishlistDAO;


@Log4j2
public class TestMain {
// WishlistDAO의 메소드를 호출하는 메인 클래스	
	
    private static String loggedInUserId;

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        System.out.println("아이디 입력: ");
        String userId = sc.nextLine();

        // 로그인 로직 ~~
        loggedInUserId = userId;

        System.out.println("식당의 번호를 선택해주세요.");
        int rowNum = sc.nextInt();

        WishlistDAO wishlistDAO = new WishlistDAO();
       
        int resId = wishlistDAO.getResIdByRowNum(rowNum);

        if (resId == -1) {
        	log.info("해당 식당에 대한 정보를 찾는데 실패했습니다.");
        	
            return;
        } else if(resId == 12) {
            System.out.println("찜 목록 추가 성공하였습니다!");
        } // if-else

        try {
            wishlistDAO.addWish(loggedInUserId, resId);
            log.info("찜 목록 추가 성공하였습니다!");
        } catch (Exception e) {
        	log.info("찜 목록 추가 실패하였습니다. " + e.getMessage());
        } // try-catch
    
    } // end main

} // end class