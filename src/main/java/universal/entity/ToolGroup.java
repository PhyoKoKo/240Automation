package universal.entity;

import java.util.ArrayList;

public class ToolGroup {
	private String name;
	private ArrayList<Tool> tools;
	
	public ToolGroup(String toolgroup){
		this.setName(toolgroup);
		this.setTools(new ArrayList<Tool>());
	}

	public ArrayList<Tool> getTools() {
		return tools;
	}

	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
