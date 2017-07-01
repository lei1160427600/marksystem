package cn.edu.henau.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.edu.henau.entity.Checkin;
import cn.edu.henau.entity.Course;
import cn.edu.henau.entity.Grade;
import cn.edu.henau.entity.Task;
import cn.edu.henau.entity.UserEntity;
import cn.edu.henau.model.CheckInVO;
import cn.edu.henau.model.CourseVO;
import cn.edu.henau.model.Login;
import cn.edu.henau.model.StudentVO;
import cn.edu.henau.util.Page;
public interface UserService {
    List<UserEntity> getUserList();
    
    UserEntity getById(long userId);
    
    UserEntity getByUserCode(String userCode);

	Login validateLogin(Login login);

	List<UserEntity> createRollCall(Login login);

	Page<CheckInVO> getCurrentPage(CheckInVO vo, int pageIndex, int countPerPage);

	List<Course> readXls(InputStream inputStream) throws IOException;

	List<Checkin> savrOrUpdateCheckin(List<Checkin> checkin);

	StudentVO savrOrUpdateStudent(StudentVO studentVO);

	List<UserEntity> readStudentXls(InputStream is) throws IOException;

	CourseVO saveOrUpdateCourseVO(CourseVO courseVO);

	List<Task> saveTask(List<Task> task);


	List<Grade> queryGrade(Checkin checkin);

	List<Grade> saveGrade(List<Grade> grade);

	List<List<Grade>> queryHistoryGrade(Checkin checkin);

	List<Grade> queryByCondition(Checkin checkin);

	StudentVO saveAuthority(StudentVO studentVO);
}
