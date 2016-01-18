package datacombination.entity;

public class MES {
	private String start, end;
	private String tool;
	private String status;
	public MES(String start, String end, String tool, String status){
		this.start = start;
		this.end = end;
		this.tool = tool;
		this.status = status;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
