package Bom;

import Mdm.MdmDTO;

public class BomDTO {
	int bom_num = -1;
	int required_weight;//¾ó¸¶³ª
	int mdm_num;
	
	String name;//¹¹°¡
	String code;//¹¹°¡
	String unit;//´ÜÀ§
	String type;
	
	int target_mdm_num;
	String target_name;
	String target_code;
	
	
	
	public String getTarget_code() {
		return target_code;
	}
	public void setTarget_code(String target_code) {
		this.target_code = target_code;
	}
	public String getTarget_name() {
		return target_name;
	}
	public void setTarget_name(String target_name) {
		this.target_name = target_name;
	}
	public int getTarget_mdm_num() {
		return target_mdm_num;
	}
	public void setTarget_mdm_num(int target_mdm_num) {
		this.target_mdm_num = target_mdm_num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public int getBom_num() {
		return bom_num;
	}
	public void setBom_num(int bom_num) {
		this.bom_num = bom_num;
	}
	public int getRequired_weight() {
		return required_weight;
	}
	public void setRequired_weight(int required_weight) {
		this.required_weight = required_weight;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	@Override
	public String toString() {
		return "BomDTO [bom_num=" + bom_num + ", required_weight=" + required_weight + ", mdm_num=" + mdm_num + "]";
	}
	
}
