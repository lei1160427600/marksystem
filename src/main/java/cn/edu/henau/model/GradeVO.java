package cn.edu.henau.model;

public class GradeVO {
	private long gradeId;
	private String userCode;
	private String userName;
	private int absenteeismCount;
	private int absenteeism;
	private int urgent;
	private int leaveCount;
	private int grade;
	private String tCode;
	private int count;
	public long getGradeId() {
		return gradeId;
	}
	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAbsenteeismCount() {
		return absenteeismCount;
	}
	public void setAbsenteeismCount(int absenteeismCount) {
		this.absenteeismCount = absenteeismCount;
	}
	public int getAbsenteeism() {
		return absenteeism;
	}
	public void setAbsenteeism(int absenteeism) {
		this.absenteeism = absenteeism;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	
	public int getLeaveCount() {
		return leaveCount;
	}
	public void setLeaveCount(int leaveCount) {
		this.leaveCount = leaveCount;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String gettCode() {
		return tCode;
	}
	public void settCode(String tCode) {
		this.tCode = tCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
