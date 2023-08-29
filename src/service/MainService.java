package service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.Member_DAO;
import dto.MemberDTO;
import dto.RegionDTO;

public class MainService {

	Scanner s = new Scanner(System.in);

	public static MemberDTO loginedMember; // 로그인된 사용자 아이디를 기억시킬 전역변수
	
	RestaurantService resService = new RestaurantService(loginedMember);

	String user_id;

	pro Pro = new pro();

	/**
	 * 프로그램 시작 메서드
	 */
	public void start() {

		Scanner s = new Scanner(System.in);
		int userInput = 100;

		while (userInput != 0) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");

			userInput = Integer.parseInt(s.nextLine());

			try {
				if (userInput == 1) {

					while (true) {
						int temp_login = user_login();
						if (temp_login != 0) { // 0이면 반복 그외에는 탈출
							break;
						}
					}

					System.out.println("1. 맛집리스트 보기 ");
					System.out.println("2. my page 보기 ");
					userInput = Integer.parseInt(s.nextLine());
					if (userInput == 1) {

						resService.restaurantList();

					} else if (userInput == 2) {
						// 준택이형 코드
						while (true) {

							int temp;
							temp = Pro.memberInformation(loginedMember);
							if (temp == 1) {
								break;
							}
						}
					}

				} else if (userInput == 2) {
					signIn();
				} else if (userInput == 3) {
					System.exit(0);
				}
			} catch (Exception e) {
				System.out.println("잘못 누르셨습니다.");
				System.out.println("1,2,3 메뉴중 숫자를 선택하세요.");
				start();
			}

		}

	}

	/**
	 * 회원가입 메서드
	 */
	public void signIn() {
		MemberDTO member_DTO = new MemberDTO(); // 사용자가 입력한 정보들을 모아 놓을 DTO 클래스
		Member_DAO member_DAO = new Member_DAO();

		int pwd = 0; // pwd
		String user_name = null; // name
		int region_number = 0; // 사용자 지역 코드
		String user_tel = null; // 입력받은 사용자 전화번호

		System.out.println(">>> 회원 가입 <<<");
		System.out.println("정보를 입력해 주세요.");
		while (true) { // id 가 중복되면
			user_id = check_Id(); // 검증받은 사용자 ID 대입 [id가 중복되면 null을 리턴받는다.] id가 중복되지 않으면 2를 리턴받음

			if (user_id != null) {
				break;
			}

		}

		while (true) {
			pwd = check_pwd(); // 이상하게 입력하면 0을 리턴한다. 정상입력하면 숫자가 리턴됨
			if (pwd != 0) {
				break;
			}
		}

		while (true) {
			user_name = checkName();
			if (user_name != null) {
				break;
			}

		}
		while (true) {
			region_number = checkRegion();
			if (region_number != 0) {
				break;
			}
		}
		while (true) {
			user_tel = checkTel(); // 전화번호가 중복되면 null , 그 외에는 값을 받음
			if (user_tel != null) {
				break;
			}

		}

		member_DTO.setId(user_id); // MEMBER_DTO 객체에 대입
		member_DTO.setPwd(pwd);
		member_DTO.setName(user_name);
		member_DTO.setRegion_code(region_number);
		member_DTO.setTel(user_tel);

		member_DAO.Sign_user_in_table(member_DTO); // DAO에 회원가입 요청
	}

	// ID가 중복되었는지 찾으면서 ID 값을 리턴하게 한다. (String 타입)
	public String check_Id() {
		Member_DAO member_DAO = new Member_DAO();
		System.out.print("아이디 : ");
		user_id = s.nextLine();
		int check_id = member_DAO.Id_duplicate_check(user_id); // id 중복검사 리턴은 1,2를 하는데 1 은 id 있음, 2는 id 없음
		if (check_id == 1) {
			System.out.println("아이디가 존재합니다. 다시 입력해 주세요");

			return null; // id가 중복되면 null을 리턴한다.
		} else {
			System.out.println("사용 가능한 아이디입니다.");
			System.out.println("리턴된 user_id" + user_id);
			return user_id;
		}

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
			return 0;
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
			if (user_name.length() > 4) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("4자리 이하 한글만 입력하세요");
//			checkName();
			return null;
		}

		return user_name;
	}

	// 지역 코드 검증 메서드
	public int checkRegion() {
		Scanner s = new Scanner(System.in);
		Member_DAO member_DAO = new Member_DAO();
		int user_region = 0;
		try {
			System.out.println("지역 번호를 선택해주세요.");
			List<RegionDTO> list = member_DAO.show_region_list();
			for (RegionDTO regionDTO : list) {
				System.out.println(regionDTO.getRegion_code() + regionDTO.getRegion_name());
			} // 지역 테이블 리스트 출력
			System.out.print("선택 : ");
			user_region = s.nextInt();

			if (user_region > 6)
				throw new Exception();

		} catch (Exception e) {
			System.out.println("범위에 맞는 숫자만 입력하십시오");
			return 0;
		}
		return user_region;
	}

	// 전화번호 검증 메서드
	/**
	 * 전화번호는 VARCHAR2(20 BYTE) 숫자로만 입력하도록 사용자한테 말해야한다. 문자가 입력되면 다시 입력받도록 한다.
	 */
	public String checkTel() {
		Member_DAO member_DAO = new Member_DAO();
		Scanner s = new Scanner(System.in);
		String user_tel = null;
		int checkTel = 0;

		System.out.print("전화번호(숫자로만 입력하세요) : ");
		user_tel = s.nextLine();
		checkTel = member_DAO.Tel_duplicate_check(user_tel);

		if (checkTel == 1) {
			System.out.println("전화번호가 중복됩니다. 다시입력하세요");

			return null;
		} else if (checkTel == 2) {
			System.out.println("유효한 전화번호 입니다.");
		}

		return user_tel;

	}

	/**
	 * 로그인 메서드
	 */
	public int user_login() {
		Scanner s = new Scanner(System.in);
		Member_DAO member_DAO = new Member_DAO();
		String user_id; // 사용자에게 입력을 받을 ID
		int check_id = 0; // 테이블에 있는 아이디인지 검사
		int user_pwd = 0; // 사용자에게 입력을 받을 pwd
		int checkPwd = 0; // 비밀번호 일치하는지 안하는지 기억시킬 임시변수

		// 로그인에 따른 정수값 리턴
		// 로그인 성공 1
		// 로그인 실패 0
		int checkLogin;

		System.out.println("로그인 화면입니다.");
		System.out.println();
		System.out.print("아이디 : ");
		user_id = s.nextLine();
		check_id = member_DAO.Id_duplicate_check(user_id); // 테이블에 있는 아이디인지 검사
		if (check_id == 1) {

			user_pwd = check_pwd();
			checkPwd = member_DAO.CheckPwd(user_pwd); // 비밀번호가 일치하지 않다면 1을 리턴, 비밀번호가 일치한다면 2를 리턴

			if (checkPwd == 1) {
				System.out.println("로그인 성공");
				loginedMember = new MemberDTO();
				loginedMember.setId(user_id);
				System.out.println(loginedMember.getId() + "님 환영합니다");
				return 1;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다!. 다시 입력해 주세요");
				return 0;
			}

		} else {
			System.out.println("아이디가 존재하지 않습니다. 다시 입력해 주세요");
			return 0;

		}

	}
}
