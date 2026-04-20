package io;

import java.sql.Date;

public class IoDTO {
	
	// io
	int io_num;
	int quantity;
	String io_type;
	String storage_sec;
	Date io_date;
	Date exp_date;
	
	// mdm
	int mdm_num;
	int price;
	String name;
	String code;
	String unit;
	
	// total
	long total_quantity;
	long total_price;
	

	
	
	public long getTotal_quantity() {
		return total_quantity;
	}
	public void setTotal_quantity(long total_quantity) {
		this.total_quantity = total_quantity;
	}
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getIo_num() {
		return io_num;
	}
	public void setIo_num(int io_num) {
		this.io_num = io_num;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	public String getIo_type() {
		return io_type;
	}
	public void setIo_type(String io_type) {
		this.io_type = io_type;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStorage_sec() {
		return storage_sec;
	}
	public void setStorage_sec(String storage_sec) {
		this.storage_sec = storage_sec;
	}
	public Date getIo_date() {
		return io_date;
	}
	public void setIo_date(Date io_date) {
		this.io_date = io_date;
	}
	public Date getExp_date() {
		return exp_date;
	}
	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}
	
	@Override
	public String toString() {
		return "ioDTO [io_id=" + io_num + ", mdm_num=" + mdm_num + ", io_type=" + io_type + ", quantity=" + quantity
				+ ", storage_sec=" + storage_sec + ", io_date=" + io_date + ", exp_date=" + exp_date + ", getIo_id()="
				+ getIo_num() + ", getMdm_num()=" + getMdm_num() + ", getIo_type()=" + getIo_type() + ", getQuantity()="
				+ getQuantity() + ", getStorage_sec()=" + getStorage_sec() + ", getIo_date()=" + getIo_date()
				+ ", getExp_date()=" + getExp_date() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}