package WorkOrder;

import java.sql.Date;

public class WorkOrderDTO {
	int order_num = -1;
	Date work_date;
	int daily_target;
	String status;
	String ename;
	int prod_num;
	int empno;
	String work_order_title;
	String mod;
	
	String search;
	String timefilter;
	String statustitle;
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
	public int getDaily_target() {
		return daily_target;
	}
	public void setDaily_target(int daily_target) {
		this.daily_target = daily_target;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
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
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getTimefilter() {
		return timefilter;
	}
	public void setTimefilter(String timefilter) {
		this.timefilter = timefilter;
	}
	public String getStatustitle() {
		return statustitle;
	}
	public void setStatustitle(String statustitle) {
		this.statustitle = statustitle;
	}
	@Override
	public String toString() {
		return "WorkOrderDTO [order_num=" + order_num + ", work_date=" + work_date + ", daily_target=" + daily_target
				+ ", status=" + status + ", ename=" + ename + ", prod_num=" + prod_num + ", empno=" + empno
				+ ", work_order_title=" + work_order_title + ", mod=" + mod + ", search=" + search + ", timefilter="
				+ timefilter + ", statustitle=" + statustitle + "]";
	}
	
	
}
