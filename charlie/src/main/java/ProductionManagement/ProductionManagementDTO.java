package ProductionManagement;

import java.sql.Date;

public class ProductionManagementDTO {
	private int currentCount; //�쁽�옱源뚯� 留뚮뱺 媛쒖닔
	private int remainCount; //�궓�� 媛쒖닔
	private int mdmNum;
	private String mdmName;
	String selectTitle;


	public String getSelectTitle() {
		return selectTitle;
	}
	public void setSelectTitle(String selectTitle) {
		this.selectTitle = selectTitle;
	}

	int prod_num = -1;
	int target_quantity;
	Date work_start;
	Date work_end;
	String title;
	String content;
	String code;
	int empno = 1;
	int mdm_num;
	
	private int size = 10;
	private int page = 1;
	
	private int start = 0;
	private int end = 0;
	
	
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	public int getMdmNum() {
		return mdmNum;
	}
	public void setMdmNum(int mdmNum) {
		this.mdmNum = mdmNum;
	}
	public String getMdmName() {
		return mdmName;
	}
	public void setMdmName(String mdmName) {
		this.mdmName = mdmName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public int getTarget_quantity() {
		return target_quantity;
	}
	public void setTarget_quantity(int target_quantity) {
		this.target_quantity = target_quantity;
	}
	public Date getWork_start() {
		return work_start;
	}
	public void setWork_start(Date work_start) {
		this.work_start = work_start;
	}
	public Date getWork_end() {
		return work_end;
	}
	public void setWork_end(Date work_end) {
		this.work_end = work_end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}
	@Override
	public String toString() {
		return "ProductionManagementDTO [currentCount=" + currentCount + ", remainCount=" + remainCount + ", mdmName="
				+ mdmName + ", selectTitle=" + selectTitle + ", prod_num=" + prod_num + ", target_quantity="
				+ target_quantity + ", work_start=" + work_start + ", work_end=" + work_end + ", title=" + title
				+ ", content=" + content + ", code=" + code + ", empno=" + empno + ", mdm_num=" + mdm_num + ", size="
				+ size + ", page=" + page + ", start=" + start + ", end=" + end + "]";
	}

	
	
	

	


	
	
}
