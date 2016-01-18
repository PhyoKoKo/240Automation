package profile.entity;

public class Profile {
	private String ieee, device_desc;
	private String status;
	private double power_min, power_max;
	
	public Profile (String device_desc, String ieee, String status, double power_max, double power_min){
		this.setDevice_desc(device_desc);
		this.setIeee(ieee);
		this.setStatus(status);
		this.power_max = power_max;
		this.power_min = power_min;
	}

	public double getPower_min() {
		return power_min;
	}

	public void setPower_min(double power_min) {
		this.power_min = power_min;
	}

	public double getPower_max() {
		return power_max;
	}

	public void setPower_max(double power_max) {
		this.power_max = power_max;
	}

	public String getIeee() {
		return ieee;
	}

	public void setIeee(String ieee) {
		this.ieee = ieee;
	}

	public String getDevice_desc() {
		return device_desc;
	}

	public void setDevice_desc(String device_desc) {
		this.device_desc = device_desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
