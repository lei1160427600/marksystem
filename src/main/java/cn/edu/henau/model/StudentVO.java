package cn.edu.henau.model;

import java.util.List;

import cn.edu.henau.entity.UserEntity;

public class StudentVO {

	private UserEntity studentInfo;
	private List<UserEntity> addStudent;
	private List<UserEntity> deleteStudentList;
	public UserEntity getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(UserEntity studentInfo) {
		this.studentInfo = studentInfo;
	}
	public List<UserEntity> getAddStudent() {
		return addStudent;
	}
	public void setAddStudent(List<UserEntity> addStudent) {
		this.addStudent = addStudent;
	}
	public List<UserEntity> getDeleteStudentList() {
		return deleteStudentList;
	}
	public void setDeleteStudentList(List<UserEntity> deleteStudentList) {
		this.deleteStudentList = deleteStudentList;
	}

}
