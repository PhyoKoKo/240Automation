package universal.entity;

import java.util.ArrayList;

public class ToolGroup {
	private String toolgroup;
	private ArrayList<Tool> tools;
	
	public ToolGroup(String toolgroup){
		this.setToolgroup(toolgroup);
		this.setTools(new ArrayList<Tool>());
	}

	public String getToolgroup() {
		return toolgroup;
	}

	public void setToolgroup(String toolgroup) {
		this.toolgroup = toolgroup;
	}

	public ArrayList<Tool> getTools() {
		return tools;
	}

	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}
}
