package consumption.mes.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import universal.entity.ToolGroup;

public class ToolgroupMESConsumption {
	private ToolGroup toolgroup;
	private ArrayList<ToolMESConsumption> tools;
	private HashMap<String, ConsumptionUnit> mes_consumption; //ConsumptionUnit: count, consumption, avg_consumption, min_avg_consumption
	private double total_waste, total_consumption;
	
	public ToolgroupMESConsumption(ToolGroup toolgroup){
		this.toolgroup = toolgroup;
		this.tools = new ArrayList<ToolMESConsumption>();
		this.mes_consumption = new HashMap<String, ConsumptionUnit>();
		total_waste = 0;
		total_consumption = 0;
	}
	
	public void consolidate(){
		// getting all mes statuses
		Set<String> mes = new HashSet<String>();
		for(int i = 0; i < tools.size(); i++){
			mes.addAll(tools.get(i).getMes_consumption().keySet());
		}
		
		//calculate count, consumption, min_avg_consumption for toolgroup in each status
		Iterator<String> mes_list = mes.iterator();
		while(mes_list.hasNext()){
			String status = mes_list.next();
			double consumption = 0, min_avg_consumption = -1;
			int count = 0;
			for(int i = 0; i < tools.size(); i++){
				ConsumptionUnit cu = tools.get(i).getMes_consumption().get(status);
				count += cu.getCount();
				consumption += cu.getConsumption();
				if(min_avg_consumption == -1 || min_avg_consumption > cu.getAvg_consumption())
					min_avg_consumption = cu.getAvg_consumption();
			}
			total_consumption += consumption;
			this.mes_consumption.put(status, new ConsumptionUnit(count, consumption, consumption/count, min_avg_consumption));
		}
		
		//calculate the waste for toolgroup&tool in each status
		while(mes_list.hasNext()){
			String status = mes_list.next();
			double min_avg_consumption = this.mes_consumption.get(status).getMin_avg_consumption();
			double toolgroup_waste = 0;
			
			//calculate the waste of each tool under given mes status ('status')
			for(int i = 0; i < tools.size(); i++){
				ConsumptionUnit cu = tools.get(i).getMes_consumption().get(status);
				cu.setWaste(cu.getCount() * (cu.getAvg_consumption() - min_avg_consumption));
				toolgroup_waste += cu.getWaste();
			}
			this.mes_consumption.get(status).setWaste(toolgroup_waste);	
			total_waste += toolgroup_waste;
		}
	}
	
	public ToolGroup getToolgroup() {
		return toolgroup;
	}
	public void setToolgroup(ToolGroup toolgroup) {
		this.toolgroup = toolgroup;
	}
	public ArrayList<ToolMESConsumption> getTools() {
		return tools;
	}
	public void setTools(ArrayList<ToolMESConsumption> tools) {
		this.tools = tools;
	}
	public HashMap<String, ConsumptionUnit> getMes_consumption() {
		return mes_consumption;
	}
	public void setMes_consumption(HashMap<String, ConsumptionUnit> mes_consumption) {
		this.mes_consumption = mes_consumption;
	}
	public double getWaste() {
		return total_waste;
	}
	public void setWaste(double waste) {
		this.total_waste = waste;
	}

	public double getTotal_consumption() {
		return total_consumption;
	}

	public void setTotal_consumption(double total_consumption) {
		this.total_consumption = total_consumption;
	}
}
