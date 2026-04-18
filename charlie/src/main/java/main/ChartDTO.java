package main;

public class ChartDTO {
	//////mdm///////////////////////////
	private String mdmName;
	private String mdmUnit;		//단위
	private String mdmType;	//구분
	private int mdmPrice;	//가격 및 금액
	private int mdmQuantity; 	//개수
	
	//불량품
	private int okay;

	/////////emp///////////////////
	private int sal;
	////////////////////////////////생산관리꺼
	private int target_quantity;//전체목표량
	private int currentCount; // 전체실적
	
	public int getOkay() {
		return okay;
	}
	public void setOkay(int okay) {
		this.okay = okay;
	}
	public int getTarget_quantity() {
		return target_quantity;
	}
	public void setTarget_quantity(int target_quantity) {
		this.target_quantity = target_quantity;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getMdmName() {
		return mdmName;
	}
	public void setMdmName(String mdmName) {
		this.mdmName = mdmName;
	}
	public String getMdmUnit() {
		return mdmUnit;
	}
	public void setMdmUnit(String mdmUnit) {
		this.mdmUnit = mdmUnit;
	}
	public String getMdmType() {
		return mdmType;
	}
	public void setMdmType(String mdmType) {
		this.mdmType = mdmType;
	}
	public int getMdmPrice() {
		return mdmPrice;
	}
	public void setMdmPrice(int mdmPrice) {
		this.mdmPrice = mdmPrice;
	}
	public int getMdmQuantity() {
		return mdmQuantity;
	}
	public void setMdmQuantity(int mdmQuantity) {
		this.mdmQuantity = mdmQuantity;
	}
	@Override
	public String toString() {
		return "ChartDTO [mdmName=" + mdmName + ", mdmUnit=" + mdmUnit + ", mdmType=" + mdmType + ", mdmPrice="
				+ mdmPrice + ", mdmQuantity=" + mdmQuantity + ", okay=" + okay + ", sal=" + sal + ", target_quantity="
				+ target_quantity + ", currentCount=" + currentCount + "]";
	}
}
