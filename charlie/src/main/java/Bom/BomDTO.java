package Bom;

public class BomDTO {
	int bom_num;
	int required_weight;
	String code;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "BomDTO [bom_num=" + bom_num + ", required_weight=" + required_weight + ", code=" + code + "]";
	}
	
}
