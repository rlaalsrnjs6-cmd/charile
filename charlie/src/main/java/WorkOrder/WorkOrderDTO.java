package WorkOrder;

import java.sql.Date;

public class WorkOrderDTO {
	int order_num = -1;
	Date work_date;
	int prod_num;
	int target_quantity;
	int empno;
	String work_order_title;
	String ename;
	String status;
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public Date getWork_date() {
		return work_date;
	}
	public void setWork_date(Date work_date) {
		this.work_date = work_date;
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
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getWork_order_title() {
		return work_order_title;
	}
	public void setWork_order_title(String work_order_title) {
		this.work_order_title = work_order_title;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "WorkOrderDTO [order_num=" + order_num + ", work_date=" + work_date + ", prod_num=" + prod_num
				+ ", target_quantity=" + target_quantity + ", empno=" + empno + ", work_order_title=" + work_order_title
				+ ", ename=" + ename + ", status=" + status + "]";
	}
	
}
