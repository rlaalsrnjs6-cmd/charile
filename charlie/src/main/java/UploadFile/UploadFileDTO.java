package UploadFile;

import java.sql.Date;

public class UploadFileDTO {
	int file_num;
	String url;
	int random_code;
	Date upload_time;
	String mod;
	
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getRandom_code() {
		return random_code;
	}
	public void setRandom_code(int random_code) {
		this.random_code = random_code;
	}
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	
	@Override
	public String toString() {
		return "UploadFileDTO [file_num=" + file_num + ", url=" + url + ", random_code=" + random_code
				+ ", upload_time=" + upload_time + ", mod=" + mod + "]";
	}
	
	
}
