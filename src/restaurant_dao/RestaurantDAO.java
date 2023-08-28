package restaurant_dao;

import java.lang.module.FindException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnect;
import menu_dto.MenuDTO;
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
	public List<RestaurantDTO> regionSelect(int num) {

		List<RestaurantDTO> list = new ArrayList<>();

		ResultSet rs = null;

		String selectSQL = "SELECT RES_NAME, WISH_COUNT\r\n"
				+ "FROM(\r\n"
				+ "    SELECT ROWNUM AS RN, RES_NAME, WISH_COUNT, RES_ID\r\n"
				+ "    FROM ( \r\n"
				+ "        SELECT RES_NAME, WISH_COUNT, RES_ID \r\n"
				+ "        FROM RESTAURANT\r\n"
				+ "        WHERE REGION_CODE = ?\r\n"
				+ "        ORDER BY WISH_COUNT DESC)\r\n"
				+ "        )";
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new RestaurantDTO(rs.getString(1), rs.getInt(2)));
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

		return list;
	}

	// 찜 많은 순으로 보기
	@Override
	public List<RestaurantDTO> wishBestSelect() {

		List<RestaurantDTO> list = new ArrayList<>();

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
				list.add(new RestaurantDTO(rs.getString(1),rs.getInt(2)));
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

		return list;
	}
	
	// 지역 순에서 선택 식당 보기
	@Override
	public RestaurantDTO selectRegionRes(int RegionNum, int selectNum) {
		
		RestaurantDTO resDTO = null;
		ResultSet rs = null;
		
		String selectSQL="SELECT E.*, M.*,R.*\r\n"
				+ "FROM(\r\n"
				+ "    SELECT ROWNUM AS RN, A.*\r\n"
				+ "    FROM ( \r\n"
				+ "        SELECT * \r\n"
				+ "        FROM RESTAURANT\r\n"
				+ "        WHERE REGION_CODE = ?\r\n"
				+ "        ORDER BY WISH_COUNT DESC) A\r\n"
				+ "        ) E, MENU M, REGION R\r\n"
				+ "WHERE E.RES_ID = M.RES_ID\r\n"
				+ "AND E.REGION_CODE = R.REGION_CODE\r\n"
				+ "AND RN = ?";
		
		RestaurantDTO restaurantDTO = null;
		
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, RegionNum);
			pstmt.setInt(2, selectNum);
			rs = pstmt.executeQuery();
			
			List<MenuDTO> menuList = new ArrayList<>();
			
			while(rs.next()) {
				if(restaurantDTO == null) {
					restaurantDTO = new RestaurantDTO();
					restaurantDTO.setWish_count(rs.getInt("WISH_COUNT"));
					restaurantDTO.setRes_name(rs.getString("RES_NAME"));
					restaurantDTO.setRes_tel(rs.getString("RES_TEL"));
					restaurantDTO.setMenuList(menuList);
					restaurantDTO.setLocation(rs.getString("REGION_NAME"));
					restaurantDTO.setRes_info(rs.getString("RES_INFO"));
				}
				MenuDTO menuDTO = new MenuDTO();
				menuDTO.setMenu_name(rs.getString("MENU_NAME"));
				menuDTO.setMenu_price(rs.getString("MENU_PRICE"));
				menuList.add(menuDTO);
			}
			return restaurantDTO;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return resDTO;
	}

	
	// 찜 많은 순에서 선택 식당 보기 
	@Override
	public RestaurantDTO selectWishRes(int num) throws FindException{
		
		ResultSet rs = null;
		
		String selectSQL = "SELECT E.*, M.*, R.*\r\n"
				+ "FROM(\r\n"
				+ "    SELECT ROWNUM AS rn,A.*\r\n"
				+ "    FROM(\r\n"
				+ "        SELECT \r\n"
				+ "        *\r\n"
				+ "        FROM RESTAURANT\r\n"
				+ "        ORDER BY WISH_COUNT DESC) A\r\n"
				+ "        ) E, MENU M, REGION R\r\n"
				+ "WHERE E.RES_ID = M.RES_ID\r\n"
				+ "AND E.REGION_CODE = R.REGION_CODE\r\n"
				+ "AND RN =?";
		
		RestaurantDTO restaurantDTO = null;
		
		try {
			pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			List<MenuDTO> menuList = new ArrayList<>();
			
			while(rs.next()) {
				if(restaurantDTO == null) {
					restaurantDTO = new RestaurantDTO();
					restaurantDTO.setWish_count(rs.getInt("WISH_COUNT"));
					restaurantDTO.setRes_name(rs.getString("RES_NAME"));
					restaurantDTO.setRes_tel(rs.getString("RES_TEL"));
					restaurantDTO.setMenuList(menuList);
					restaurantDTO.setLocation(rs.getString("REGION_NAME"));
					restaurantDTO.setRes_info(rs.getString("RES_INFO"));
				}
				MenuDTO menuDTO = new MenuDTO();
				menuDTO.setMenu_name(rs.getString("MENU_NAME"));
				menuDTO.setMenu_price(rs.getString("MENU_PRICE"));
				menuList.add(menuDTO);
			}
			
			return restaurantDTO;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
			
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}