package restaurant_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import db.DBConnect;
import restaurant_dto.RestaurantDTO;

public class RestaurantDAO implements RestaurantDAOInterface {

	private Connection conn;
	private PreparedStatement pstmt = null;

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public RestaurantDAO() {
		DBConnect dbconn = DBConnect.getInstance();
		conn = dbconn.getConnection();
	}

	public Connection getConn() {
		return conn;
	}

	// 지역별 맛집 리스트 보기
	@Override
	public Vector<Object> regionSelect(int num) {

		Vector<Object> v = new Vector<Object>();

		ResultSet rs = null;

		String selectSQL = "SELECT RES_NAME FROM RESTAURANT\r\n" + "WHERE REGION_CODE = ?";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				v.add(new RestaurantDTO(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return v;
	}

	// 찜 많은 순으로 보기
	@Override
	public Vector<Object> wishBestSelect() {

		Vector<Object> v = new Vector<Object>();

		ResultSet rs = null;

		String selectSQL = "SELECT RES_NAME, WISH_COUNT\r\n"
				+ "FROM(\r\n"
				+ "    SELECT ROWNUM AS rn, RES_NAME, WISH_COUNT \r\n"
				+ "    FROM(\r\n"
				+ "        SELECT RES_NAME, WISH_COUNT \r\n"
				+ "        FROM RESTAURANT\r\n"
				+ "        ORDER BY WISH_COUNT DESC)\r\n"
				+ "        )";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				v.add(new RestaurantDTO(rs.getString(1),rs.getInt(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return v;
	}
	
	// 지역 순에서 원하는 식당 보기
	@Override
	public Object selectRegionRes(int num) {
		Object obj = null;
		ResultSet rs = null;
		
		String selectSQL="";
		
		try {
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = new RestaurantDTO();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return obj;
	}

	
	// 찜 많은 순에서 선택 식당 보기 
	@Override
	public Object selectWishRes(int num) {
		Object obj = null;
		ResultSet rs = null;
		
		String selectSQL="SELECT E.RES_NAME, M.menu_name, M.menu_price,E.WISH_COUNT\r\n"
				+ "FROM(\r\n"
				+ "    SELECT ROWNUM AS rn, RES_NAME, WISH_COUNT, RES_ID \r\n"
				+ "    FROM(\r\n"
				+ "        SELECT RES_NAME, WISH_COUNT, RES_ID \r\n"
				+ "        FROM RESTAURANT\r\n"
				+ "        ORDER BY WISH_COUNT DESC)\r\n"
				+ "        ) E, MENU M\r\n"
				+ "WHERE E.RES_ID = M.RES_ID\r\n"
				+ "AND rn = ?";
		
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = new RestaurantDTO();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return obj;
	}


}
