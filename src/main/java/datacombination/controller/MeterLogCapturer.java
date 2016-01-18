package datacombination.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.joda.time.DateTime;

import datacombination.entity.CombinedRecord;
import datacombination.entity.MES;
import utilities.DBConnect;
import utilities.Utilities;

public class MeterLogCapturer {
	private static final String table_prefix = "Meter_log_";
	private static final String DEVICE_TABLE = "Device";
	
	public static void capture(MES mes, String date){
		String table = Utilities.completeTable(table_prefix, date);
		DateTime day = Utilities.parseDateTime(date);
		DateTime day_start = day.withTimeAtStartOfDay(), day_end = day.plusDays(1).withTimeAtStartOfDay();
		DateTime start = Utilities.parseDateTime(mes.getStart()), end = Utilities.parseDateTime(mes.getEnd());
		if(day_start.isAfter(start))
			start = day_start;
		if(day_end.isBefore(end))
			end = day_end;
		
		Connection conn = DBConnect.getConn_240();
		String sql = "select a.time_stamp, a.ieee, (a.ch1_watt+a.ch2_watt+a.ch3_watt)/1000 "
						+ " from \"" + table + "\" as a, \"" + DEVICE_TABLE + "\" as b "
						+ " where a.ieee = b.ieee and b.device_desc like '" + mes.getTool() + "%' "
						+ " and time_stamp >= '" + Utilities.printDateTime(start) + "' and time_stamp < '" + Utilities.printDateTime(end) + "'";
		try {
			PreparedStatement stat = conn.prepareCall(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				CombinedRecordUpdater.update(new CombinedRecord(rs.getString(1), rs.getString(2), rs.getDouble(3), mes.getStatus()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
