package datacombination.controller;
import java.util.ArrayList;

import org.joda.time.DateTime;

import datacombination.entity.MES;
import universal.entity.Tool;
import utilities.Utilities;

public class ToolUpdater {
	public static void updateToolWithDate(Tool tool, String date){
		ArrayList<MES> meslist = MESCapturer.capture(tool, date);
		for(int i = 0; i < meslist.size(); i++)
			MeterLogCapturer.capture(tool, meslist.get(i), date);
	}
	public static void updateToolWithDateRange(Tool tool, String start_date, String end_date){
		DateTime start = Utilities.parseDateTime(start_date), end = Utilities.parseDateTime(end_date);
		if(start.isAfter(end))
			return;
		
		while(!start.isAfter(end)){
			updateToolWithDate(tool, Utilities.printDate(start));
			start = start.plusDays(1);
		}
	}
}
