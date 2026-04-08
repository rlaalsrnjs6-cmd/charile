package Board;

import java.sql.Date;

public class BoardDTO {
	int empno;
	int post_num;
	String category;
	String title;
	String content;
	Date write_time;
	int file_num;
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public Date getWrite_time() {
		return write_time;
	}
	public void setWrite_time(Date write_time) {
		this.write_time = write_time;
	}
	public int getFile_num() {
		return file_num;
	}
	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [empno=" + empno + ", post_num=" + post_num + ", category=" + category + ", title=" + title
				+ ", content=" + content + ", write_time=" + write_time + ", file_num=" + file_num + "]";
	}
	
	
}
