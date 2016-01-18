package consumption.daily.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import consumption.daily.entity.MeterConsumption;
import consumption.daily.entity.ToolConsumption;
import utilities.DBConnect;

public class ToolConsumptionCapturer {
	private static final String DEVICE_TABLE = "Device";
	
	public static ToolConsumption getToolConsumption(String tool, String date){
		ToolConsumption toolcon = new ToolConsumption(tool, date);
		toolcon.setMeters(getMeters(tool, date));
		toolcon.calConsumption();
		return toolcon;
	}
	
	private static ArrayList<MeterConsumption> getMeters(String tool, String date){
		ArrayList<MeterConsumption> meters = new ArrayList<MeterConsumption>();
		String sql = "select device_desc, ieee from \"" + DEVICE_TABLE + "\" where device_desc like '" + tool + "%'";
		
		Connection conn = DBConnect.getConn_240();
		try {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()){
				MeterConsumption meter = new MeterConsumption(rs.getString(1), rs.getString(2));
				meter.setConsumption(MeterConsumptionCapturer.getConsumptionByIEEE(meter.getIeee(), date));
				meters.add(meter);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return meters;
	}
}
