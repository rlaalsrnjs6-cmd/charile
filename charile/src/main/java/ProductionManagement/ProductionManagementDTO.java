package ProductionManagement;

import java.sql.Date;

public class ProductionManagementDTO {
	int prod_num;
	int target_quantity;
	Date work_start;
	Date work_end;
	String title;
	String content;
	String code;
	int empno;
	
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
	

	@Override
	public String toString() {
		return "ProductionManagementDTO [prod_num=" + prod_num + ", target_quantity=" + target_quantity
				+ ", work_start=" + work_start + ", work_end=" + work_end + ", title=" + title + ", content=" + content
				+ ", code=" + code + ", empno=" + empno + "]";
	}
	
	
}
