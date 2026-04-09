package PersonalHygiene;

import java.sql.Date;

public class PersonalHygieneDTO {
	int ph_num;
	int body_temper;
	Date regist_time;
	String washed;
	String memo;
	String supervisor_chk;
	int empno;
	public int getPh_num() {
		return ph_num;
	}
	public void setPh_num(int ph_num) {
		this.ph_num = ph_num;
	}
	public int getBody_temper() {
		return body_temper;
	}
	public void setBody_temper(int body_temper) {
		this.body_temper = body_temper;
	}
	public Date getRegist_time() {
		return regist_time;
	}
	public void setRegist_time(Date regist_time) {
		this.regist_time = regist_time;
	}
	public String getWashed() {
		return washed;
	}
	public void setWashed(String washed) {
		this.washed = washed;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getSupervisor_chk() {
		return supervisor_chk;
	}
	public void setSupervisor_chk(String supervisor_chk) {
		this.supervisor_chk = supervisor_chk;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	@Override
	public String toString() {
		return "PersonalHygieneDTO [ph_num=" + ph_num + ", body_temper=" + body_temper + ", regist_time=" + regist_time
				+ ", washed=" + washed + ", memo=" + memo + ", supervisor_chk=" + supervisor_chk + ", empno=" + empno
				+ "]";
	}
	
	
}
