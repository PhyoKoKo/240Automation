package datacombination.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.joda.time.DateTime;

import datacombination.entity.CombinedRecord;
import datacombination.entity.MES;
import universal.entity.Tool;
import utilities.DBConnect;
import utilities.Utilities;

public class MeterLogCapturer {
	private static final String table_prefix = "Meter_log_";
	private static final String DEVICE_TABLE = "Device";
	private static final String SQUARE_ROOT_3 = " |/ 3 ";
	private static final double REFERENCE_VOLTAGE = 208.0;
	private static final double REFERENCE_POWER_FACTOR = 0.9;
	
	public static void capture(Tool tool, MES mes, String date){
		String table = Utilities.completeTable(table_prefix, date);
		DateTime day = Utilities.parseDateTime(date);
		DateTime day_start = day.withTimeAtStartOfDay(), day_end = day.plusDays(1).withTimeAtStartOfDay();
		DateTime start = Utilities.parseDateTime(mes.getStart()), end = Utilities.parseDateTime(mes.getEnd());
		if(day_start.isAfter(start))
			start = day_start;
		if(day_end.isBefore(end))
			end = day_end;
		
		Connection conn = DBConnect.getConn_240();
		String watt_sql = tool.isReference()? "(a.ch1_current+a.ch2_current+a.ch3_current)/3000 * " + SQUARE_ROOT_3 + "*" + REFERENCE_VOLTAGE + "*" + REFERENCE_POWER_FACTOR
							:"(a.ch1_watt+a.ch2_watt+a.ch3_watt)/1000";
		String sql = "select a.time_stamp, a.ieee, " + watt_sql
						+ " from \"" + table + "\" as a, \"" + DEVICE_TABLE + "\" as b "
						+ " where a.ieee = b.ieee and b.device_desc like '" + mes.getTool() + "%' "
						+ " and time_stamp >= '" + Utilities.printDateTime(start) + "' and time_stamp < '" + Utilities.printDateTime(end) + "'";
		
		try {
			PreparedStatement stat = conn.prepareCall(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				CombinedRecordUpdater.update(new CombinedRecord(rs.getString(1), rs.getString(2), rs.getDouble(3), mes.getStatus()));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
