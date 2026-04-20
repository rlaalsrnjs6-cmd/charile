package Mdm;

import java.sql.Date;

public class MdmDTO {
	int mdm_num;
	String code;
	String name;
	String unit;
	String type;
	
	Date received_date;
	Date exp_date;
	
	int price;
	
	String canUse;

	
	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
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
	public String getCanUse() {
		return canUse;
	}
	public void setCanUse(String canUse) {
		this.canUse = canUse;
	}
	
	@Override
	public String toString() {
		return "MdmDTO [mdm_num=" + mdm_num + ", code=" + code + ", name=" + name + ", unit=" + unit + ", type=" + type
				+ ", received_date=" + received_date + ", exp_date=" + exp_date + ", price=" + price + ", quantity="
			 + ", canUse=" + canUse + "]";
	}
}
