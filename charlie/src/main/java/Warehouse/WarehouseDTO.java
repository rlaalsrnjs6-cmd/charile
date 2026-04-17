package Warehouse;

import java.sql.Date;

public class WarehouseDTO {
	int warehouse_num =-1;
	String wh_section = "";
	String floor_level;
	double temperature;
	double humidity;
	String wh_status_chk;
	Date wh_chk_date;
	
	
	
	String mod;
	
	
	public Date getWh_chk_date() {
		return wh_chk_date;
	}
	public void setWh_chk_date(Date wh_chk_date) {
		this.wh_chk_date = wh_chk_date;
	}
	public String getWh_section() {
		return wh_section;
	}
	public void setWh_section(String wh_section) {
		this.wh_section = wh_section;
	}
	public int getWarehouse_num() {
		return warehouse_num;
	}
	public void setWarehouse_num(int warehouse_num) {
		this.warehouse_num = warehouse_num;
	}
	public String getFloor_level() {
		return floor_level;
	}
	public void setFloor_level(String floor_level) {
		this.floor_level = floor_level;
	}

	public String getWh_status_chk() {
		return wh_status_chk;
	}
	public void setWh_status_chk(String wh_status_chk) {
		this.wh_status_chk = wh_status_chk;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
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
	
	
	
}
