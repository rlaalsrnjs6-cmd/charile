package MaterialManagement;

public class MaterialManagementDTO {
	int material_num;
	int total_quantity;
	int warehouse_num;
	String code;
	
	public int getMaterial_num() {
		return material_num;
	}
	public void setMaterial_num(int material_num) {
		this.material_num = material_num;
	}
	public int getTotal_quantity() {
		return total_quantity;
	}
	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}
	public int getWarehouse_num() {
		return warehouse_num;
	}
	public void setWarehouse_num(int warehouse_num) {
		this.warehouse_num = warehouse_num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "MaterialManagementDTO [material_num=" + material_num + ", total_quantity=" + total_quantity
				+ ", warehouse_num=" + warehouse_num + ", code=" + code + "]";
	}
	
}
