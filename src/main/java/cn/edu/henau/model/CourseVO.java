package cn.edu.henau.model;

import java.util.List;

import cn.edu.henau.entity.Course;
import cn.edu.henau.entity.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class CourseVO {

	private Teacher teacherInfo;
	private List<Course> courseTable;
	private List<Course> deleteCourseList;
	public Teacher getTeacherInfo() {
		return teacherInfo;
	}
	public void setTeacherInfo(Teacher teacherInfo) {
		this.teacherInfo = teacherInfo;
	}
	public List<Course> getCourseTable() {
		return courseTable;
	}
	public void setCourseTable(List<Course> courseTable) {
		this.courseTable = courseTable;
	}
	public List<Course> getDeleteCourseList() {
		return deleteCourseList;
	}
	public void setDeleteCourseList(List<Course> deleteCourseList) {
		this.deleteCourseList = deleteCourseList;
	}

	@Test
	public String test() {
		for (int item = 0; item < 100; item++) {
			System.out.println(item);
		}
		String a = "usrename";
		return "CourseVO{";
	}


}
