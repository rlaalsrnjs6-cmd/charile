package QC;

import java.sql.Date;

public class QCDTO {
	int qc_num = -1;
	int lot_num;
	Date qc_date;
	int empno;
	String mod;
	
	public int getQc_num() {
		return qc_num;
	}
	public void setQc_num(int qc_num) {
		this.qc_num = qc_num;
	}
	public int getLot_num() {
		return lot_num;
	}
	public void setLot_num(int lot_num) {
		this.lot_num = lot_num;
	}
	public Date getQc_date() {
		return qc_date;
	}
	public void setQc_date(Date qc_date) {
		this.qc_date = qc_date;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	
	@Override
	public String toString() {
		return "QCDTO [qc_num=" + qc_num + ", lot_num=" + lot_num + ", qc_date=" + qc_date + ", empno=" + empno
				+ ", mod=" + mod + "]";
	}
	
	
}
