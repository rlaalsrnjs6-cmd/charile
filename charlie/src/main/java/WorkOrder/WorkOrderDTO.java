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
	int weekly_target;
	
	int mdm_num;
	String search;
	String timefilter;
	String statustitle;
	
	int required_weight;//¾ó¸¶³ª
	String name;//¹¹°¡
	String code;//¹¹°¡
	String unit;//´ÜÀ§
	String process_content;
	int flow;
	String img_url;
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
	public int getWeekly_target() {
		return weekly_target;
	}
	public void setWeekly_target(int weekly_target) {
		this.weekly_target = weekly_target;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
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
	public int getRequired_weight() {
		return required_weight;
	}
	public void setRequired_weight(int required_weight) {
		this.required_weight = required_weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProcess_content() {
		return process_content;
	}
	public void setProcess_content(String process_content) {
		this.process_content = process_content;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	@Override
	public String toString() {
		return "WorkOrderDTO [order_num=" + order_num + ", work_date=" + work_date + ", daily_target=" + daily_target
				+ ", status=" + status + ", ename=" + ename + ", prod_num=" + prod_num + ", empno=" + empno
				+ ", work_order_title=" + work_order_title + ", mod=" + mod + ", weekly_target=" + weekly_target
				+ ", mdm_num=" + mdm_num + ", search=" + search + ", timefilter=" + timefilter + ", statustitle="
				+ statustitle + ", required_weight=" + required_weight + ", name=" + name + ", code=" + code + ", unit="
				+ unit + ", process_content=" + process_content + ", flow=" + flow + ", img_url=" + img_url + "]";
	}
	
	
}
