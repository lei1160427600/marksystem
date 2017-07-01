package cn.edu.henau.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.henau.entity.Teacher;

public interface TeacherDao {

	Teacher queryById(long tId);
	
	Teacher queryByTCode(String tCode);

	long insertTeacher(Teacher teacher);

	void updateTeacher(Teacher teacherr);

	List<Teacher> queryAll(@Param("offset") int offset,
			@Param("limit") int limit);
}
