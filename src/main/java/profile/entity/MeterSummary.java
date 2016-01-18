package profile.entity;

public class MeterSummary {
	private String ieee, device_desc;
	private String status;
	private int count;
	private double avg_power;
	private boolean isNormal;
	
	public MeterSummary(String ieee, String device_desc, String status, int count, double avg_power){
		this.setIeee(ieee);
		this.setDevice_desc(device_desc);
		this.setStatus(status);
		this.setCount(count);
		this.setAvg_power(avg_power);
	}
	
	public void print(){
		System.out.println(device_desc + " (" + status + "): " + count + " min, average power at " + avg_power + " kW, " + ((isNormal)?"within":"out of") + " range" );
	}

	
	public String getIeee() {
		return ieee;
	}

	public void setIeee(String ieee) {
		this.ieee = ieee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAvg_power() {
		return avg_power;
	}

	public void setAvg_power(double avg_power) {
		this.avg_power = avg_power;
	}

	public boolean isNormal() {
		return isNormal;
	}

	public void setNormal(boolean isNormal) {
		this.isNormal = isNormal;
	}

	public String getDevice_desc() {
		return device_desc;
	}

	public void setDevice_desc(String device_desc) {
		this.device_desc = device_desc;
	}
}
