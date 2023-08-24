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
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		
		if(pstmt !=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		
		if(conn !=null) {
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
	
	// 지역별로 맛집 목록 가져오기
	@Override
	public Vector<RestaurantDTO> regionSelect(int num) {
		
		Vector<RestaurantDTO> v = new Vector<RestaurantDTO>();
		
		ResultSet rs = null;
		
		String selectSQL = "SELECT RES_NAME FROM RESTAURANT\r\n"
				+ "WHERE REGION_CODE = ?";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, 2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				v.add(new RestaurantDTO(rs.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
		}
		
		
		return v;
	}
	
}

