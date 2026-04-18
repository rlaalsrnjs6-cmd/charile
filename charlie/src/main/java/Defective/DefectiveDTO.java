package Defective;

public class DefectiveDTO {
	int defective_num = -1;
	String category;
	int count;
	int lot_num;
	String action;
	int mdm_num;
//	String status;
	String mod;
	
	public int getDefective_num() {
		return defective_num;
	}
	public void setDefective_num(int defective_num) {
		this.defective_num = defective_num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLot_num() {
		return lot_num;
	}
	public void setLot_num(int lot_num) {
		this.lot_num = lot_num;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	@Override
	public String toString() {
		return "DefectiveDTO [defective_num=" + defective_num + ", category=" + category + ", count=" + count
				+ ", lot_num=" + lot_num + ", action=" + action + ", mdm_num=" + mdm_num + ", mod=" + mod + "]";
	}
	
	
}
