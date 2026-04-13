package Mdm;

import java.sql.Date;

public class MdmDTO {
	int mdm_num;
	String code;
	String name;
	String unit;
	String type;
	Date received_date;
	int price;
	

	
	public Date getReceived_date() {
		return received_date;
	}
	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "MdmDTO [mdm_num=" + mdm_num + ", code=" + code + ", name=" + name + ", unit=" + unit + ", type=" + type
				+ ", received_date=" + received_date + ", price=" + price + "]";
	}
	
	
}
