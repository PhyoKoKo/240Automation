package datacombination.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import datacombination.entity.MES;
import universal.entity.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.DBConnect;

public class MESCapturer {
	private static final String MES_TABLE = "MES";
	
	public static ArrayList<MES> capture(Tool tool, String date){
		ArrayList<MES> MESlist = new ArrayList<MES>();
		Connection conn = DBConnect.getConn_mes();
		
		String sql = "select * from " + MES_TABLE + " where EQP_ID = '" + tool.getTool()
						+ "' and (trunc(start_time) = to_date('" + date + "', 'yyyy-mm-dd') or trunc(end_time) = to_date('" + date + "', 'yyyy-mm-dd')"
						+ " or (trunc(start_time) < to_date('" + date + "', 'yyyy-mm-dd') and trunc(end_time) > to_date('" + date + "', 'yyyy-mm-dd')))";

		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();		
			while(rs.next()){
				MESlist.add(new MES(rs.getString(2), rs.getString(4), rs.getString(1), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return MESlist;
	}
}
