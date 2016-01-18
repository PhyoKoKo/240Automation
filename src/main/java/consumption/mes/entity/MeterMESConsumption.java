package consumption.mes.entity;

public class MeterMESConsumption {
	private String device_desc, ieee;
	private String mes;
	
	public MeterMESConsumption(String ieee, String device_desc, String mes, double consumption){
		this.ieee = ieee;
		this.device_desc = device_desc;
		this.mes = mes;
		this.consumption = consumption;
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
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	private double consumption;
}
