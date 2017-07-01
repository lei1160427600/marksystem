package cn.edu.henau.model;

public class Login {

	private String userCode;
	private String pwd;
	private String Identity;
	private String userName;
	private int authority;
	private String message;
	private String userClass;
	private String profession;
	private String semester;
	private String rollcallDate;
	private String duties;
	private Integer random;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserClass() {
		return userClass;
	}
	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getRollcallDate() {
		return rollcallDate;
	}
	public void setRollcallDate(String rollcallDate) {
		this.rollcallDate = rollcallDate;
	}
	public String getIdentity() {
		return Identity;
	}
	public void setIdentity(String identity) {
		Identity = identity;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public Integer getRandom() {
		return random;
	}
	public void setRandom(Integer random) {
		this.random = random;
	}
	
}
