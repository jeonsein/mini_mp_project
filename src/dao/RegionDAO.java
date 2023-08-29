package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import db.DBConnect;
import dto.RegionDTO;

public class RegionDAO implements RegionDAOInterface {

	Connection conn = null;
	PreparedStatement pstmt = null;

	public RegionDAO() {
		DBConnect dbConn = DBConnect.getInstance();
		conn = dbConn.getConnection();
	}

	@Override
	public List<RegionDTO> regionList() {

		List<RegionDTO> list = new ArrayList<RegionDTO>();

		ResultSet rs = null;

		String selectSQL = "SELECT * FROM REGION\r\n" + "ORDER BY REGION_CODE";

		try {
			pstmt = conn.prepareStatement(selectSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new RegionDTO(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
