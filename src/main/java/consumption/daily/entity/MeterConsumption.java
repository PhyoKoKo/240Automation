package consumption.daily.entity;

public class MeterConsumption {
	private String device_desc, ieee;
	private double consumption;
	
	public MeterConsumption(String device_desc, String ieee){
		this.device_desc = device_desc;
		this.ieee = ieee;
	}
	public String getDevice_desc() {
		return device_desc;
	}
	public void setDevice_desc(String device_desc) {
		this.device_desc = device_desc;
	}
	public String getIeee() {
		return ieee;
	}
	public void setIeee(String ieee) {
		this.ieee = ieee;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
}
