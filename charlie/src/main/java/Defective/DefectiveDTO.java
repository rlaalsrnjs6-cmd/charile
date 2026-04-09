package Defective;

public class DefectiveDTO {
	String defective_num;
	String category;
	int count;
	int qc_num;
	String action;
	
	public String getDefective_num() {
		return defective_num;
	}
	public void setDefective_num(String defective_num) {
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
	
	@Override
	public String toString() {
		return "DefectiveDTO [defective_num=" + defective_num + ", category=" + category + ", count=" + count
				+ ", qc_num=" + qc_num + ", action=" + action + "]";
	}
	
}
