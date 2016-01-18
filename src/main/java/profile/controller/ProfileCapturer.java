package profile.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import profile.entity.Profile;
import utilities.DBConnect;

public class ProfileCapturer {
	private static final String PROFILE_TABLE = "Meter_profile";
	
	public static Profile getProfileByIEEE(String ieee, String status){
		String sql = "select * from \"" + PROFILE_TABLE + "\" where ieee = '" + ieee + "' and status = '" + status + "'";
		Connection conn = DBConnect.getConn_240();
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				return new Profile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Profile getProfileByDesc(String ieee, String status){
		String sql = "select * from \"" + PROFILE_TABLE + "\" where device_desc = '" + ieee + "' and status = '" + status + "'";
		Connection conn = DBConnect.getConn_240();
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			if(rs.next()){
				return new Profile(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
