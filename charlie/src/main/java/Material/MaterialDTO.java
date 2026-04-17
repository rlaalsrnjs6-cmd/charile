package Material;

public class MaterialDTO {
	
	// material
	int material_num;
	int total_quantity;
	long total_price;
	
	// join warehouse
	int warehouse_num;
	String wh_status_chk;
	int temperature;
	int humidity;
	String wh_section;
	String floor_level;
	
	// join mdm
	int mdm_num;
	String code;
	String name; 
	String unit;
	
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
	public String getWh_status_chk() {
		return wh_status_chk;
	}
	public void setWh_status_chk(String wh_status_chk) {
		this.wh_status_chk = wh_status_chk;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public String getWh_section() {
		return wh_section;
	}
	public void setWh_section(String wh_section) {
		this.wh_section = wh_section;
	}
	public String getFloor_level() {
		return floor_level;
	}
	public void setFloor_level(String floor_level) {
		this.floor_level = floor_level;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public long getTotal_price() {
		return total_price;
	}
	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "MaterialDTO [material_num=" + material_num + ", total_quantity=" + total_quantity + ", total_price="
				+ total_price + ", warehouse_num=" + warehouse_num + ", wh_status_chk=" + wh_status_chk
				+ ", temperature=" + temperature + ", humidity=" + humidity + ", wh_section=" + wh_section
				+ ", floor_level=" + floor_level + ", mdm_num=" + mdm_num + ", code=" + code + ", name=" + name
				+ ", unit=" + unit + "]";
	}
	
	

	
}
