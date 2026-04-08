package Machinery;

import java.sql.Date;

public class MachineryDTO {
	int machinery_log_num;
	String machinery_type;
	String machinery_status;
	String code;
	String error_sign;
	String m_action;
	Date m_log_time;
	public int getMachinery_log_num() {
		return machinery_log_num;
	}
	public void setMachinery_log_num(int machinery_log_num) {
		this.machinery_log_num = machinery_log_num;
	}
	public String getMachinery_type() {
		return machinery_type;
	}
	public void setMachinery_type(String machinery_type) {
		this.machinery_type = machinery_type;
	}
	public String getMachinery_status() {
		return machinery_status;
	}
	public void setMachinery_status(String machinery_status) {
		this.machinery_status = machinery_status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getError_sign() {
		return error_sign;
	}
	public void setError_sign(String error_sign) {
		this.error_sign = error_sign;
	}
	public String getM_action() {
		return m_action;
	}
	public void setM_action(String m_action) {
		this.m_action = m_action;
	}
	public Date getM_log_time() {
		return m_log_time;
	}
	public void setM_log_time(Date m_log_time) {
		this.m_log_time = m_log_time;
	}
	
	@Override
	public String toString() {
		return "MachineryDTO [machinery_log_num=" + machinery_log_num + ", machinery_type=" + machinery_type
				+ ", machinery_status=" + machinery_status + ", code=" + code + ", error_sign=" + error_sign
				+ ", m_action=" + m_action + ", m_log_time=" + m_log_time + "]";
	}
	
	
}
