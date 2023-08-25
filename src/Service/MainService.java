package Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DTO.RegionDTO;
import member.Member_DAO;
import member.Member_DTO;

public class MainService {

	/**
	 * 프로그램 시작 메서드
	 */
	public void start() {
		Scanner s = new Scanner(System.in);
		int userInput = 0; // 사용자의 입력을 저장할 변수
//		Member_DAO member_DAO = new Member_DAO();

		while (userInput != 3) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");

			try {
				userInput = s.nextInt();
				// 사용자가 123이 아닌 문자를 입력했거나 다른걸 입력했으면 다시 메뉴 화면 띄우기
				if (userInput == 1) {
					// 로그인 기능
				} else if (userInput == 2) {
					signIn();

				} else if (userInput == 3) {
					System.exit(0); // 3번을 누르면 프로그램 종료
				}
			} catch (InputMismatchException e) {
				System.out.println("잘못 누르셨습니다.");
				System.out.println("1,2,3 메뉴중 숫자를 선택하세요.");
				start();
			}

		}
	}

	/**
	 * 회원가입 메서드
	 */
	public Member_DTO signIn() {
		Member_DTO member_DTO = new Member_DTO(); // 사용자가 입력한 정보들을 모아 놓을 DTO 클래스
		String user_id; // id
		int pwd;		// pwd
		String user_name; // name
		int region_number; // 사용자 지역 코드
		String user_tel; // 입력받은 사용자 전화번호
		
		
		System.out.println(">>> 회원 가입 <<<");
		System.out.println("정보를 입력해 주세요.");
		user_id = check_Id(); //검증받은 사용자 ID 대입
		pwd = check_pwd();
		user_name = checkName();
		region_number = checkRegion();
		user_tel = checkTel();
		
		
		member_DTO.setId(user_id); //MEMBER_DTO 객체에 대입
		member_DTO.setPwd(pwd);
		member_DTO.setName(user_name);
		member_DTO.setRegion_name(region_number);
		member_DTO.setTel(user_tel);
		
		return member_DTO;
	}

	// ID가 중복되었는지 찾으면서 ID 값을 리턴하게 한다. (String 타입)
	public String check_Id() {
		Member_DAO member_DAO = new Member_DAO();

		Scanner s = new Scanner(System.in);
		String user_id; // 사용자가 입력한 ID를 저장할 변수

		System.out.print("아이디 : ");
		user_id = s.nextLine();
		int check_id = member_DAO.Id_duplicate_check(user_id); // id 중복검사 리턴은 1,2를 하는데 1 은 id 있음, 2는 id 없음
		if (check_id == 1) {
			System.out.println("아이디가 존재합니다. 다시 입력해 주세요");
			check_Id();
		} else if (check_id == 2) {
			System.out.println("사용 가능한 아이디입니다.");

		}
		return user_id;

	}

	// 숫자로 된 9자리의 비밀번호만 들어갈 수 있는지 검증 비밀번호를 리턴한다.
	// 사용자는 9자리 이상의 숫자도 입력할 수 있다. DAO에서 처리
	// 사용자가 문자로 입력할 수도 있다 이에 대한 예외 처리를 진행하자.
	public int check_pwd() {

		int user_pwd = 0; // 사용자가 입력한 pwd를 저장할 변수
		Scanner s = new Scanner(System.in);
		try {
			System.out.print("비밀번호 : ");
			user_pwd = s.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("9자리이하 숫자만 입력 가능합니다.");
			check_pwd();
		} catch (ArithmeticException e) {
			System.out.println("9자리이하 숫자만 입력 가능합니다.");
			check_pwd();
		}

		return user_pwd;

	}

	// 이름 입력 검증 메서드
	public String checkName() {
		Scanner s = new Scanner(System.in);
		String user_name = null;
		try {
			System.out.print("이름 : ");
			user_name = s.nextLine();
			if(user_name.length() > 4 )
			{
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("4자리 이하 한글만 입력하세요");
			checkName();
		}

		return user_name;
	}
	
	// 지역 코드 검증 메서드
	public int checkRegion() {
		Scanner s = new Scanner(System.in);
		Member_DAO member_DAO = new Member_DAO();
		int user_region=0;
		try {
			System.out.print("지역 번호를 선택해주세요.");
			List<RegionDTO> list = member_DAO.show_region_list();
			for (RegionDTO regionDTO : list) {
				System.out.println(regionDTO.getRegion_code() + regionDTO.getRegion_name());
			} //지역 테이블 리스트 출력
			System.out.print("선택 : ");
			user_region = s.nextInt();
			
			if(user_region > 6) throw new Exception();
			
			
		} catch (Exception e) {
			System.out.println("범위에 맞는 숫자만 입력하십시오");
			checkRegion();
		}
		return user_region;
	}
	
	//전화번호 검증 메서드
	/**
	 * 전화번호는 VARCHAR2(20 BYTE)
	 * 숫자로만 입력하도록 사용자한테 말해야한다.
	 * 문자가 입력되면 다시 입력받도록 한다.
	 */
	public String checkTel() {
		Scanner s = new Scanner(System.in);
		String user_tel = null;
		
		try {
			System.out.print("전화번호 : ");
			 user_tel = s.nextLine();
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요");
			checkTel();
		}
		return user_tel;
	}
	
	

}
