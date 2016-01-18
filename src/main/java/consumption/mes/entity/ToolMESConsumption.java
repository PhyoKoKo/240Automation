package consumption.mes.entity;

import java.util.ArrayList;

public class ToolMESConsumption {
	private String tool;
	private String start, end;
	private ArrayList<MeterMESConsumption> meters;
	
	public ToolMESConsumption(String tool, String start, String end){
		this.setTool(tool);
		this.setStart(start);
		this.setEnd(end);
		meters = new ArrayList<MeterMESConsumption>();
	}

	public void print(){
		System.out.println( tool + ", " + start + " to " + end + ":");
		for(int i = 0; i < meters.size(); i++)
			System.out.println(meters.get(i).getDevice_desc() + " " + meters.get(i).getMes() + " " + meters.get(i).getConsumption());
	}
	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public ArrayList<MeterMESConsumption> getMeters() {
		return meters;
	}

	public void setMeters(ArrayList<MeterMESConsumption> meters) {
		this.meters = meters;
	}
	
}
