package datacombination.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datacombination.entity.CombinedRecord;
import utilities.DBConnect;
import utilities.Utilities;

public class CombinedRecordUpdater {
	private static final String table_prefix = "Combined_Meter_log_";
	public static void update(CombinedRecord rec){
		String table = Utilities.completeTable(table_prefix, rec.getTime_stamp());
		checkTableExist(table);
		String sql = "insert into \"" + table + "\" values('" + rec.getTime_stamp() + "', '" + rec.getieee() + "'," + rec.getTotal_kW() + ", '" + rec.getMES() + "')";
		Connection conn = DBConnect.getConn_240();
		try {
			if(conn.prepareStatement(sql).executeUpdate() == 1)
				System.out.println("insertion of (" + rec.getTime_stamp() + ", " + rec.getieee() + ") succeed");
			else
				System.out.println("insertion of (" + rec.getTime_stamp() + ", " + rec.getieee() + ") failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void checkTableExist(String table){
		Connection conn = utilities.DBConnect.getConn_240();
		String sql = "select exists (select 1 from information_schema.tables where table_name='" + table + "')";

		try {		
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			rs.next();
			if(!rs.getBoolean(1)){
				String create_sql = "create table \"" + table + "\""
									+ " (time_stamp timestamp without time zone NOT NULL, "
									+ " ieee character varying NOT NULL, "
									+ " total_kW double precision, "
									+ " MES character varying)";
				conn.prepareStatement(create_sql).executeUpdate();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
