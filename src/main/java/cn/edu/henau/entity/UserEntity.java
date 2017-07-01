package cn.edu.henau.entity;

import java.util.Date;


public class UserEntity {
	private long userId;
	
	private String userName;
	
	private String userCode;
	
	private String userClass;
	
	private String profession;
	
	private String userPwd;
	
	private Boolean authority;
	
	private Date createTime;
	
	private String Identity;
	
	private String semester;
	
	private String rollcallDate;
	
	private String duties;
	
	private String telephoneNo;
	
	private String tCode;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}



	public Boolean getAuthority() {
		return authority;
	}

	public void setAuthority(Boolean authority) {
		this.authority = authority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
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

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String gettCode() {
		return tCode;
	}

	public void settCode(String tCode) {
		this.tCode = tCode;
	}

}
