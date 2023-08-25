package menu_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import db.DBConnect;
import menu_dto.MenuDTO;

public class MenuDAO implements MenuDAOInterface{
	
	private Connection conn;
	private PreparedStatement pstmt = null;
	
	public MenuDAO() {
		DBConnect dbConn = DBConnect.getInstance();
		conn = dbConn.getConnection();
	}

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

	
	
	@Override
	public Vector<Object> selectWishMenu(int num) {
		
		Vector<Object> v = new Vector<Object>();
		
		ResultSet rs = null;
		
		String selectSQL = "SELECT M.menu_name, M.menu_price\r\n"
				+ "FROM(\r\n"
				+ "    SELECT ROWNUM AS rn, RES_ID \r\n"
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
			while(rs.next()) {
				v.add(new MenuDTO(rs.getString(1), rs.getString(2)));
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
	

}
