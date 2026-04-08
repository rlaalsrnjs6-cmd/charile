package Warehouse;

public class WarehouseDTO {
	int warehouse_num;
	String section;
	String floor_level;
	int temperature;
	String humidity;
	String wh_status_chk;
	public int getWarehouse_num() {
		return warehouse_num;
	}
	public void setWarehouse_num(int warehouse_num) {
		this.warehouse_num = warehouse_num;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getFloor_level() {
		return floor_level;
	}
	public void setFloor_level(String floor_level) {
		this.floor_level = floor_level;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWh_status_chk() {
		return wh_status_chk;
	}
	public void setWh_status_chk(String wh_status_chk) {
		this.wh_status_chk = wh_status_chk;
	}
	
	@Override
	public String toString() {
		return "WarehouseDTO [warehouse_num=" + warehouse_num + ", section=" + section + ", floor_level=" + floor_level
				+ ", temperature=" + temperature + ", humidity=" + humidity + ", wh_status_chk=" + wh_status_chk + "]";
	}
	
	
	
}
