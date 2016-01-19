package universal.entity;

import java.util.ArrayList;

public class Map {
	private ArrayList<Module> modules;
	public Map(){
		this.setModules(new ArrayList<Module>());
	}
	
	public Module getModule(String name){
		for(int i = 0; i < modules.size(); i++)
			if(modules.get(i).getModule().equals(name))
				return modules.get(i);
		return null;
	}
	public ArrayList<Module> getModules() {
		return modules;
	}
	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}
}
