package universal.entity;

import java.util.ArrayList;

public class Module {
	private String module;
	private ArrayList<ToolGroup> toolgroups;
	
	public Module(String module){
		this.setModule(module);
		this.setToolgroups(new ArrayList<ToolGroup>());
	}
	public ToolGroup getToolGroup(String name){
		for(int i = 0; i < toolgroups.size(); i++)
			if(toolgroups.get(i).getToolgroup().equals(name))
				return toolgroups.get(i);
		return null;
	}
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public ArrayList<ToolGroup> getToolgroups() {
		return toolgroups;
	}

	public void setToolgroups(ArrayList<ToolGroup> toolgroups) {
		this.toolgroups = toolgroups;
	}
}
