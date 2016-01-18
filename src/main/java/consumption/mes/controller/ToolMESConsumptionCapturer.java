package consumption.mes.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;

import consumption.mes.entity.MeterMESConsumption;
import consumption.mes.entity.ToolMESConsumption;
import utilities.DBConnect;
import utilities.Utilities;

public class ToolMESConsumptionCapturer {
	private static final String DEVICE_TABLE = "Device";
	private static final String table_prefix = "Combined_Meter_log_";
	
	public static ToolMESConsumption getConsumption(String tool, String start, String end){
		ToolMESConsumption tool_con = new ToolMESConsumption(tool, start, end);
		DateTime start_dt = Utilities.parseDateTime(start), end_dt = Utilities.parseDateTime(end);
		
		String table = Utilities.unionTable(table_prefix, start, end);
		String sql = "select a.ieee, device_desc, mes, count(*)*avg(total_kW)/60 from " + table + " as a, \"" + DEVICE_TABLE + "\" as b "
					+ " where a.ieee = b.ieee and device_desc like '" + tool + "%'"
					+ " and time_stamp >= '" + Utilities.printDateTime(start_dt) + "' and time_stamp <= '" + Utilities.printDateTime(end_dt.plusDays(1)) + "'"
					+ " group by a.ieee, device_desc, mes";
		Connection conn = DBConnect.getConn_240();
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				tool_con.getMeters().add(new MeterMESConsumption(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tool_con;
	}
}
