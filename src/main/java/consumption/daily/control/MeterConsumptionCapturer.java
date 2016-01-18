package consumption.daily.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;

import utilities.DBConnect;
import utilities.Utilities;

public class MeterConsumptionCapturer {
	private static final String table_prefix = "Combined_Meter_log_";
	
	public static double getConsumptionByIEEE(String ieee, String date){
		
		String table = Utilities.completeTable(table_prefix, date);
		DateTime dt = Utilities.parseDateTime(date);
		String sql = "select count(*) * avg(total_kW)/60 from \"" + table + "\" where ieee = '" + ieee + "'"
						+ " and time_stamp >= '" + Utilities.printDateTime(dt) + "' and time_stamp < '" + Utilities.printDateTime(dt.plusDays(1)) + "'";
		
		Connection conn = DBConnect.getConn_240();
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			if(rs.next())
				return rs.getDouble(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
