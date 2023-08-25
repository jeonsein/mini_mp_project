package sevice;

import java.util.Scanner;
import lombok.extern.log4j.Log4j2;
import restaurant_dto.RestaurantDTO;

@Log4j2
public class RestaurantSevice {

	Scanner sc = new Scanner(System.in);
	RestaurantDTO  rs = new RestaurantDTO();

	// 맛집 리스트 보기
	public void restaurantList() {
		while (true) {
			System.out.println("1. 지역별로 보기");
			System.out.println("2. 찜 많은 순으로 보기");
			System.out.println("3. 뒤로가기");
			System.out.println("안녕");
			log.info("안녕하세요" + rs);
			System.out.print("선택 입력 : ");
			
			int select = sc.nextInt();
			
			if(select == 3) {
				break;
			} else if(select == 1) {
				
			} else if(select == 2) {
			
			}
		}
	} // end of restaurantList

}
