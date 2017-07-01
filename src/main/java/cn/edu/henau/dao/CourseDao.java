package cn.edu.henau.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.henau.entity.Course;

public interface CourseDao {

	 Course queryById(long courseId);
	 
	 List<Course> queryByTCode(String tCode);
	 
	 int insertCourse(List<Course> course);
	 
	 void updateCourse(List<Course> course);
	 
	 void deleteCourse(List<Course> course);
	 
	 Course updateCourse(Course course);
	 
	 List<String> loadCourseList(String tCode);
	 
	 List<String> loadAllCourseList();
	    
	 List<Course> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
