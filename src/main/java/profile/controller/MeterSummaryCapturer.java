package profile.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.DateTime;

import profile.entity.MeterSummary;
import utilities.DBConnect;
import utilities.Utilities;

public class MeterSummaryCapturer {
	private static final String table_prefix = "Combined_Meter_log_";
	private static final String DEVICE_TABLE = "Device";
	
	public static ArrayList<MeterSummary> getMeterSummary(String tool, String date){
		ArrayList<MeterSummary> meterSum = new ArrayList<MeterSummary>();
		String table = Utilities.completeTable(table_prefix, date);
		DateTime dt = Utilities.parseDateTime(date);
		String sql = "select a.ieee, device_desc, mes, count(*), avg(total_kW) from \"" + table + "\" as a, \"" + DEVICE_TABLE + "\" as b "
						+ " where a.ieee = b.ieee and device_desc like '" + tool + "%' and time_stamp >= '" + Utilities.printDateTime(dt) + "' and time_stamp < '" + Utilities.printDateTime(dt.plusDays(1)) + "' "
						+ " group by a.ieee, mes";
		Connection conn = DBConnect.getConn_240();
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				meterSum.add(new MeterSummary(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meterSum;
	}
}
