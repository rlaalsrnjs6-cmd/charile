package Defective;

public class DefectiveDTO {
	int defective_num = -1;
	String category;
	int count;
	int qc_num;
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
	public int getQc_num() {
		return qc_num;
	}
	public void setQc_num(int qc_num) {
		this.qc_num = qc_num;
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
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	@Override
	public String toString() {
		return "DefectiveDTO [defective_num=" + defective_num + ", category=" + category + ", count=" + count
				+ ", qc_num=" + qc_num + ", action=" + action + ", mdm_num=" + mdm_num + ", status=" + ", mod="
				+ mod + "]";
	}
	
	
	
}
