package WorkOrder;

import java.sql.Date;

public class WorkOrderDTO {
	int order_num = -1;
	Date work_date;
	int prod_num;
	int daily_target;
	String status;
	int mdm_num;
		int empno;
		String title;
		int work_start;
		int work_end;
		String content;
		int weekly_target;
	String mod;
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
	public int getDaily_target() {
		return daily_target;
	}
	public void setDaily_target(int daily_target) {
		this.daily_target = daily_target;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWork_start() {
		return work_start;
	}
	public void setWork_start(int work_start) {
		this.work_start = work_start;
	}
	public int getWork_end() {
		return work_end;
	}
	public void setWork_end(int work_end) {
		this.work_end = work_end;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWeekly_target() {
		return weekly_target;
	}
	public void setWeekly_target(int weekly_target) {
		this.weekly_target = weekly_target;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	@Override
	public String toString() {
		return "WorkOrderDTO [order_num=" + order_num + ", work_date=" + work_date + ", prod_num=" + prod_num
				+ ", daily_target=" + daily_target + ", empno=" + empno + ", status=" + status + ", mdm_num=" + mdm_num
				+ ", title=" + title + ", work_start=" + work_start + ", work_end=" + work_end + ", content=" + content
				+ ", weekly_target=" + weekly_target + ", mod=" + mod + "]";
	}
	
	
}
