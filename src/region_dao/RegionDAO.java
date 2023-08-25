package region_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import db.DBConnect;

public class RegionDAO implements RegionDAOInterface{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	public RegionDAO() {
		DBConnect dbConn = DBConnect.getInstance();
		conn = dbConn.getConnection();
	}

	@Override
	public Vector<Object> regionList() {
		
		Vector<Object> v = new Vector<Object>();
		
		ResultSet rs = null;
		
		String selectSQL = "";
		
		try {
			pstmt = conn.prepareStatement(selectSQL);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	


}
