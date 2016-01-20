package universal.entity;

import java.util.ArrayList;

public class Module {
	private String name;
	private ArrayList<ToolGroup> toolgroups;
	
	public Module(String module){
		this.setName(module);
		this.setToolgroups(new ArrayList<ToolGroup>());
	}
	public ToolGroup getToolGroupByName(String name){
		for(int i = 0; i < toolgroups.size(); i++)
			if(toolgroups.get(i).getName().equals(name))
				return toolgroups.get(i);
		return null;
	}

	public ArrayList<ToolGroup> getToolgroups() {
		return toolgroups;
	}

	public void setToolgroups(ArrayList<ToolGroup> toolgroups) {
		this.toolgroups = toolgroups;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
