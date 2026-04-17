package IOLog;

import java.sql.Date;

public class IOLogDTO {
	int log_num = -1;
	Date io_time;
	String io_type;
	int lot_num;
	int mdm_num;
	String mod;
	public int getLog_num() {
		return log_num;
	}
	public void setLog_num(int log_num) {
		this.log_num = log_num;
	}
	public Date getIo_time() {
		return io_time;
	}
	public void setIo_time(Date io_time) {
		this.io_time = io_time;
	}
	public String getIo_type() {
		return io_type;
	}
	public void setIo_type(String io_type) {
		this.io_type = io_type;
	}
	public int getLot_num() {
		return lot_num;
	}
	public void setLot_num(int lot_num) {
		this.lot_num = lot_num;
	}
	public int getMdm_num() {
		return mdm_num;
	}
	public void setMdm_num(int mdm_num) {
		this.mdm_num = mdm_num;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	@Override
	public String toString() {
		return "IOLogDTO [log_num=" + log_num + ", io_time=" + io_time + ", io_type=" + io_type + ", lot_num=" + lot_num
				+ ", mdm_num=" + mdm_num + ", mod=" + mod + "]";
	}
	
	
}
