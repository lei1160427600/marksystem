package cn.edu.henau.entity;

public class Teacher {

	private long tId;
	private String tCode;
	private String tName;
	private String duties;
	private String tEmail;
	private String semester;
	private String telephoneNo;
	public long gettId() {
		return tId;
	}
	public void settId(long tId) {
		this.tId = tId;
	}
	public String gettCode() {
		return tCode;
	}
	public void settCode(String tCode) {
		this.tCode = tCode;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}

	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String gettEmail() {
		return tEmail;
	}
	public void settEmail(String tEmail) {
		this.tEmail = tEmail;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
