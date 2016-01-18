package universal.entity;

import java.util.ArrayList;

public class Tool {
	private String tool;
	private ArrayList<Meter> meters;
	
	public Tool(String tool){
		this.setTool(tool);
		this.setMeters(new ArrayList<Meter>());
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public ArrayList<Meter> getMeters() {
		return meters;
	}

	public void setMeters(ArrayList<Meter> meters) {
		this.meters = meters;
	}
}
