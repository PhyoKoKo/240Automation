package datacombination.entity;

public class CombinedRecord {
	private String ieee;
	private String time_stamp;
	private double total_kW;
	private String MES;
	
	public CombinedRecord( String time_stamp, String ieee, double total_kW, String MES){
		this.ieee = ieee;
		this.time_stamp = time_stamp;
		this.total_kW = total_kW;
		this.MES = MES;
	}

	public String getieee() {
		return ieee;
	}

	public void setieee(String ieee) {
		this.ieee = ieee;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public double getTotal_kW() {
		return total_kW;
	}

	public void setTotal_kW(double total_kW) {
		this.total_kW = total_kW;
	}

	public String getMES() {
		return MES;
	}

	public void setMES(String mES) {
		MES = mES;
	}
}
