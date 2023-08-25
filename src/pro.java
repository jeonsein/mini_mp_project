import java.util.Scanner;
import member_dao.MemberDAO;
public class pro {
	
	public static void main(String[] args) {

		// 준택
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("회원 정보 수정");
	
		while (true) {	
			
			System.out.println("작업 선택하세요 : 뒤로가기 - 0, 비밀번호 변경 - 1, 이름 변경 - 2, 전화번호 변경 - 3, 주소 변경 - 4,");
			String opt = sc.nextLine();
			
			//뒤로 가기 
			if (opt.equals("0")) {
				 break;
				
			// 이름 변경
			} else if (opt.equals("1")) {
			 
				
			// 전화번호 변경	
			} else if (opt.equals("2")) {
				
				
			// 주소변경(시/도 변경만 가능)	
			} else if (opt.equals("3")) {
			
				 
			} else if (opt.equals("4")) {
		
				
			}
			break;
		}
		 
		System.out.println("회원탈퇴");
	
		while (true) {	
			System.out.println("정말 탈퇴하시겠습니까? 정보 복구가 불가능합니다"
					+ ": 네 - 1, 아니요 - 2 ");
			String opt1 = sc.nextLine();
			// 정보 삭제되면서, 처음화면으로
			if (opt1.equals("1")) {
				 
				
				return ; 
			
			// my page 이동
			} else if (opt1.equals("2")) {
			  
		 
			}
			break;
		}
	
		System.out.println("회원 정보 수정");
	
		while (true) {	
			System.out.println("찜한 식당 확인하기 : 뒤로가기 - 0, 찜 삭제 - 1");
		
			String opt2 = sc.nextLine();
			// 비번 변경 
			if (opt2.equals("0")) {
			 
				// 찜삭제
			} else if (opt2.equals("1")) {
			 
	 
			} 
			sc.close();
		}
		 
	}
}
