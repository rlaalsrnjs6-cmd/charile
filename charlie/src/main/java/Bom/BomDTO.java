package Bom;

public class BomDTO {
	int bom_num;
	int required_weight;
	int mdm_num;
	
	String name;
	String code;
	String unit;
	
	
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
