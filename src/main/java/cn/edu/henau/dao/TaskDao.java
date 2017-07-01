package cn.edu.henau.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.edu.henau.entity.Task;

public interface TaskDao {

	Task queryById(long tId);
	
	List<Task> queryByTCode(String tCode);
	
	List<Task> queryByTcodeAndCourse(@Param("tCode")String tCode,@Param("course")String course);

	long insertTask(List<Task> task);

	void updateTask(List<Task> task);

	List<Task> queryAll(@Param("offset") int offset,
			@Param("limit") int limit);
}
