package consumption.daily.entity;

import java.util.ArrayList;

public class ToolConsumption {
	private String tool;
	private String date;
	private double consumption;
	private ArrayList<MeterConsumption> meters;
	
	public void print(){
		System.out.println("Tool: " + tool + ", Date: " + date);
		for(int i = 0; i < meters.size(); i++)
			System.out.println("Meter: " + meters.get(i).getDevice_desc() + ", Consumption: " + meters.get(i).getConsumption());
	}
	public ToolConsumption(String tool, String date){
		this.setTool(tool);
		this.setDate(date);
		setMeters(new ArrayList<MeterConsumption>());
	}
	
	public void calConsumption(){
		double total = 0;
		for(int i = 0; i < meters.size(); i++)
			total += meters.get(i).getConsumption();
		consumption = total;
	}
	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public ArrayList<MeterConsumption> getMeters() {
		return meters;
	}

	public void setMeters(ArrayList<MeterConsumption> meters) {
		this.meters = meters;
	}

}
