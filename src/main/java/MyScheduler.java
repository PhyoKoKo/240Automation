import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.joda.time.DateTime;

import datacombination.controller.ToolUpdater;
import universal.entity.Map;
import universal.entity.Module;
import universal.entity.ToolGroup;
import utilities.Utilities;

@Stateless(name="AutomaticSchedulerBean")

public class MyScheduler{
	private static Map map = null;
	@Schedule(dayOfWeek = "*", hour = "1", minute = "0", second = "0", year="*", persistent = false)
    public void backgroundProcessing() {
		if(map == null)
			map = FileLoader.load();

		DateTime yesterday = new DateTime().minusDays(1);
		
		for(int i = 0; i < map.getModules().size(); i++){
			Module m = map.getModules().get(i);
			for(int j = 0; j < m.getToolgroups().size(); j++){
				ToolGroup tg = m.getToolgroups().get(j);
				for(int k = 0; k < tg.getTools().size(); k++)
					ToolUpdater.updateToolWithDate(tg.getTools().get(k), Utilities.printDate(yesterday));
			}
		}
		
//		for(int i = 0; i < map.getModules().size(); i++){
//			Module m = map.getModules().get(i);
//			ModuleMESConsumptionCapturer.getConsumption(m, Utilities.printDate(yesterday), Utilities.printDate(yesterday));
//		}
   }
} 
