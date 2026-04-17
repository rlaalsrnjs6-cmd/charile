package myPage;

public class MypageDTO {
	private int empno;
	private String ename;
	private String id;
	private String pw;
	private int empLevel;
	private String tel;
	private String addr;//주소
	private String email;
	private String dept;//부서
	private String hireDate;//입사일

	
	
	public int getEmpno() {
		return empno;
	}



	public void setEmpno(int empno) {
		this.empno = empno;
	}



	public String getEname() {
		return ename;
	}



	public void setEname(String ename) {
		this.ename = ename;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPw() {
		return pw;
	}



	public void setPw(String pw) {
		this.pw = pw;
	}



	public int getEmpLevel() {
		return empLevel;
	}



	public void setEmpLevel(int empLevel) {
		this.empLevel = empLevel;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getAddr() {
		return addr;
	}



	public void setAddr(String addr) {
		this.addr = addr;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDept() {
		return dept;
	}



	public void setDept(String dept) {
		this.dept = dept;
	}



	public String getHireDate() {
		return hireDate;
	}



	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}



	@Override
	public String toString() {
		return "MypageDTO [empNo=" + empno + ", ename=" + ename + ", id=" + id + ", pw=" + pw + ", empLevel=" + empLevel
				+ ", tel=" + tel + ", addr=" + addr + ", email=" + email + ", dept=" + dept + ", hireDate=" + hireDate
				+ "]";
	}
	
}
