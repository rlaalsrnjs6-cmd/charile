package Material;

public class MaterialDTO {
	int material_num;
	int total_quantity;
	int warehouse_num;
	
	// join mdm
	int mdm_num;
	String code;
	String name;
	int quantity;
	String unit;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
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
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	@Override
	public String toString() {
		return "MaterialManagementDTO [material_num=" + material_num + ", total_quantity=" + total_quantity
				+ ", warehouse_num=" + warehouse_num + ", mdm_num=" + mdm_num + "]";
	}

	
}
