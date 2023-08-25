package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DB_CONNECT.DB_connect;
import DTO.RegionDTO;

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
		db.db_connection(); //중복되는 DB 연결 코드를 따로 메서드로 실행
		
		//SQL문을 DB에 송신하기 위해 멤버변수로 송신 객체를 만들어 놓자.
		PreparedStatement pstmt = null;
		
		//SQL문을 DB에 수신받기 위해 멤버변수로 수신 객체를 만들어 놓자.
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
		}
		return 2; // 아이디 값이 없으면 2를 리턴
	}

	/**
	 * 지역 리스트 출력하기
	 */

	public List<RegionDTO> show_region_list() {


		List<RegionDTO> region_list = new ArrayList<>();


		DB_connect db = new DB_connect();
		db.db_connection(); //중복되는 DB 연결 코드를 따로 메서드로 실행

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
		}
		return region_list;
	}

}
