package cn.edu.henau.dao;

import java.util.Date;
import java.util.List;

import cn.edu.henau.entity.Checkin;
import cn.edu.henau.entity.Grade;

public interface GradeDao {

	Grade queryById(long gradeId);
	
	List<Grade> queryByTCode(String tCode);
	
	List<Date> queryaddDate();

	long insertGrade(List<Grade> grade);

	void updateGrade(List<Grade> grade);
	
	List<Grade> queryAll();

	List<Grade> queryByCondition(Checkin checkin);
}
