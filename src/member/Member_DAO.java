package member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB_CONNECT.DB_connect;
import DTO.MemberDTO;
import region_dto.RegionDTO;

public class Member_DAO implements Member_Interface {

	/**
	 * ID 중복 검사 메서드
	 * 
	 * ID 가 중복된다면 1을 반환하고 ID 가 없으면 2를 반환합니다.
	 * 
	 * @param temp_id
	 */
	public int Id_duplicate_check(String temp_id) {

		DB_connect db = new DB_connect();
		db.db_connection(); // 중복되는 DB 연결 코드를 따로 메서드로 실행

		// SQL문을 DB에 송신하기 위해 멤버변수로 송신 객체를 만들어 놓자.
		PreparedStatement pstmt = null;

		// SQL문을 DB에 수신받기 위해 멤버변수로 수신 객체를 만들어 놓자.
		ResultSet rs = null;

		// 쿼리문 작성
		String selectSQL = "SELECT id\r\n" + "FROM member\r\n" + "WHERE id = ?";

		try {
			pstmt = db.conn.prepareStatement(selectSQL); // jdbc를 통해 쿼리문 송싱
			// 해당 객체를 사용하여 SQL쿼리를 실행하고 결과를 가져오거나 처리 가능
			pstmt.setString(1, temp_id); // 첫번쨰 물음표 값에 nam363

			rs = pstmt.executeQuery(); // 쿼리문을 실행하여 rs 객체에 수신받기

			while (rs.next()) {
				String id = rs.getString(1);
				if (id != null) {
					return 1; // 아이디 값이 있으면 1을 리턴
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (db.conn != null) {
				try {
					db.conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return 2; // 아이디 값이 없으면 2를 리턴
	}

	/**
	 * tel (전화번호 중복 검사 메서드) tel 이 중복된다면 1을 반환하고 tel이 중복되는게 없다면 2를 반환합니다.
	 */
	public int Tel_duplicate_check(String tel) {
		DB_connect db = new DB_connect();
		db.db_connection(); // 중복되는 DB 연결 코드를 따로 메서드로 실행

		// SQL문을 DB에 송신하기 위해 멤버변수로 송신 객체를 만들어 놓자.
		PreparedStatement pstmt = null;
		// SQL문을 DB에 수신받기 위해 멤버변수로 수신 객체를 만들어 놓자.
		ResultSet rs = null;

		// 쿼리문 작성
		String selectSQL = "SELECT tel\r\n" + "FROM member\r\n" + "WHERE tel = ?";

		try {
			pstmt = db.conn.prepareStatement(selectSQL); // jdbc를 통해 쿼리문 송싱
			// 해당 객체를 사용하여 SQL쿼리를 실행하고 결과를 가져오거나 처리 가능
			pstmt.setString(1, tel); // 사용자의 비밀번호 세팅

			rs = pstmt.executeQuery(); // 쿼리문을 실행하여 rs 객체에 수신받기

			while (rs.next()) {
				String usertel = rs.getString(1);
				if (usertel != null) {
					return 1; // tel 값이 있으면 1을 리턴
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (db.conn != null) {
				try {
					db.conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return 2; // 아이디 값이 없으면 2를 리턴
	}

	/**
	 * 지역 리스트 출력하기
	 */

	public List<RegionDTO> show_region_list() {

		List<RegionDTO> region_list = new ArrayList<>();

		DB_connect db = new DB_connect();
		db.db_connection(); // 중복되는 DB 연결 코드를 따로 메서드로 실행

		// SQL문 DB에 송신하기
		PreparedStatement pstmt = null; // 데이터베이스에 SQL쿼리를 실행하기 위해 사용

		// 5. SQL문 결과 수신받기
		ResultSet rs = null;

		// 쿼리문 작성
		String selectSQL = "SELECT *\r\n" + "FROM region";

		try {
			pstmt = db.conn.prepareStatement(selectSQL); // jdbc를 통해 쿼리문 송신
			// 해당 객체를 사용하여 SQL쿼리를 실행하고 결과를 가져오거나 처리 가능

			rs = pstmt.executeQuery(); // 쿼리문을 실행하여 rs 객체에 수신받기

			while (rs.next()) {
				RegionDTO regionDto = new RegionDTO();
				regionDto.setRegion_code(rs.getInt(1));
				regionDto.setRegion_name(rs.getString(2));

				region_list.add(regionDto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (db.conn != null) {
				try {
					db.conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return region_list;
	}

	/**
	 * 회원 정보를 회원 테이블에 삽입
	 * 
	 */

	public void Sign_user_in_table(MemberDTO member_DTO) {
		DB_connect db = new DB_connect();
		db.db_connection();

		// SQL문 DB에 송신하기
		PreparedStatement pstmt = null; // 데이터베이스에 SQL쿼리를 실행하기 위해 사용

		// 5. SQL문 결과 수신받기
		ResultSet rs = null;

		// 쿼리문 작성
		String insertSQL = "INSERT INTO MEMBER VALUES(?,?,?,?,?)";

		try {
			pstmt = db.conn.prepareStatement(insertSQL);
			pstmt.setString(1, member_DTO.getId());
			pstmt.setInt(2, member_DTO.getPwd());
			pstmt.setString(3, member_DTO.getTel());
			pstmt.setString(4, member_DTO.getName());
			pstmt.setInt(5, member_DTO.getRegion_code());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (db.conn != null) {
				try {
					db.conn.close();
				} catch (SQLException e) {
				}
			}
		}

		System.out.println("회원가입 성공");

	}

	/**
	 * TODO LIST
	 * 
	 * 로그인시 비밀번호 동일한지 검증 member 테이블에서 비밀번호가 존재하는지 검사 비밀번호가 틀렸다면 다시 호출시켜야함
	 * 
	 * 비밀번호가 일치하지 않다면 1을 리턴 비밀번호가 동일하다면 정상 2를 리턴
	 */
	public int CheckPwd(int userPwd) {
		DB_connect db = new DB_connect();
		db.db_connection();

		PreparedStatement pstmt = null; // DB에 쿼리문 송신

		ResultSet rs = null; // DB에 쿼리 결과문 수신

		String SelectSQL = "SELECT pwd\r\n" + "FROM member\r\n" + "WHERE pwd = ?";

		try {
			pstmt = db.conn.prepareStatement(SelectSQL); // DB에 쿼리 송신
			pstmt.setInt(1, userPwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int checkpwd = rs.getInt(1);

				if (checkpwd != 0) // 가져온값이 0이 아니라 있다면!
				{
					return 1;
				} else {
					System.out.println("비밀번호가 일치합니다.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (db.conn != null) {
				try {
					db.conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return 2; // 비밀번호가 없으면 2를 리턴
	}
}
