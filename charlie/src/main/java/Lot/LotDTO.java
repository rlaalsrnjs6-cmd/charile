package Lot;

public class LotDTO {
	int lot_num;
	int lot_count;
	int order_num;
	String qc_chk;
	int material_num;
	public int getLot_num() {
		return lot_num;
	}
	public void setLot_num(int lot_num) {
		this.lot_num = lot_num;
	}
	public int getLot_count() {
		return lot_count;
	}
	public void setLot_count(int lot_count) {
		this.lot_count = lot_count;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getQc_chk() {
		return qc_chk;
	}
	public void setQc_chk(String qc_chk) {
		this.qc_chk = qc_chk;
	}
	public int getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(int material_num) {
		this.material_num = material_num;
	}
	
	@Override
	public String toString() {
		return "LotDTO [lot_num=" + lot_num + ", lot_count=" + lot_count + ", order_num=" + order_num + ", qc_chk="
				+ qc_chk + ", material_num=" + material_num + "]";
	}
	
	
	
	
}
