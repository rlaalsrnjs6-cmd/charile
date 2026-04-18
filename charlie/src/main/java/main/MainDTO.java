package main;

public class MainDTO {
	//기계 점검일정
	private int dsNum;
	private String dsLine;
	private String dsDate;
	private int dsStatus;
	////////////////////////////////
	
	//기계 라인별 현재 상태
	private int lineNum;
	private String lineName;
	private int lineStatus;
	private String lineContent;
	//////////////////////////////////////
	
	//웨어하우스
	private String whSection; 	//냉장창고 이름
	private String floorLevel; 	//냉장창고 위치
	private double temperature;	//온도
	private double humidity;	//습도
	
	public String getWhSection() {
		return whSection;
	}


	public void setWhSection(String whSection) {
		this.whSection = whSection;
	}


	public String getFloorLevel() {
		return floorLevel;
	}


	public void setFloorLevel(String floorLevel) {
		this.floorLevel = floorLevel;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public double getHumidity() {
		return humidity;
	}


	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}


	public int getDsNum() {
		return dsNum;
	}


	public void setDsNum(int dsNum) {
		this.dsNum = dsNum;
	}


	public String getDsLine() {
		return dsLine;
	}


	public void setDsLine(String dsLine) {
		this.dsLine = dsLine;
	}


	public String getDsDate() {
		return dsDate;
	}


	public void setDsDate(String dsDate) {
		this.dsDate = dsDate;
	}


	public int getDsStatus() {
		return dsStatus;
	}


	public int getLineNum() {
		return lineNum;
	}


	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}


	public String getLineName() {
		return lineName;
	}


	public void setLineName(String lineName) {
		this.lineName = lineName;
	}


	public int getLineStatus() {
		return lineStatus;
	}


	public void setLineStatus(int lineStatus) {
		this.lineStatus = lineStatus;
	}


	public String getLineContent() {
		return lineContent;
	}


	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}


	public void setDsStatus(int dsStatus) {
		this.dsStatus = dsStatus;
	}

	@Override
	public String toString() {
		return "MainDTO [dsNum=" + dsNum + ", dsLine=" + dsLine + ", dsDate=" + dsDate + ", dsStatus=" + dsStatus
				+ ", lineNum=" + lineNum + ", lineName=" + lineName + ", lineStatus=" + lineStatus + ", lineContent="
				+ lineContent + ", whSection=" + whSection + ", floorLevel=" + floorLevel + ", temperature="
				+ temperature + ", humidity=" + humidity + "]";
	}

}
