import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import universal.entity.Map;
import universal.entity.Meter;
import universal.entity.Module;
import universal.entity.Tool;
import universal.entity.ToolGroup;

public class FileLoader {

	private static final String TOOLS_CONFIG_FILE = "tools.properties";
	public static Map load(){
		
		Map map = new Map();
		String toolKey = "tool";
		int toolIndex = 1;
		try {
			Properties prop = new Properties();
			//InputStream input = new FileInputStream("../resources" + File.separator + TOOLS_CONFIG_FILE); 
			InputStream input = FileLoader.class.getResourceAsStream(TOOLS_CONFIG_FILE);
			prop.load(input);
			while(prop.containsKey(toolKey + toolIndex)){
				String[] tool_info = prop.getProperty(toolKey + toolIndex).trim().split("\\|");
				int length = tool_info.length;
				String module = tool_info[0], tool_name = tool_info[1], tool_group = tool_name.substring(0, 4);
				boolean isRef = Boolean.valueOf(tool_info[length - 1]);
				Tool newTool = new Tool(tool_name, isRef);
				for(int i = 2; i < length - 1; i++)
					newTool.getMeters().add(new Meter(tool_info[i]));
				
				if(map.getModuleByName(module) == null)
					map.getModules().add(new Module(module));
				
				if(map.getModuleByName(module).getToolGroupByName(tool_group) == null)
					map.getModuleByName(module).getToolgroups().add(new ToolGroup(tool_group));
				
				map.getModuleByName(module).getToolGroupByName(tool_group).getTools().add(newTool);
				toolIndex++;
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return map;
		
	}
}
