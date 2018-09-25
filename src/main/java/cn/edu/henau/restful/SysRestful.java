package cn.edu.henau.restful;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;






















import cn.edu.henau.dao.CheckinDao;
import cn.edu.henau.dao.CourseDao;
import cn.edu.henau.dao.TaskDao;
import cn.edu.henau.dao.TeacherDao;
import cn.edu.henau.dao.UserDao;
import cn.edu.henau.entity.Checkin;
import cn.edu.henau.entity.Course;
import cn.edu.henau.entity.Grade;
import cn.edu.henau.entity.Task;
import cn.edu.henau.entity.UserEntity;
import cn.edu.henau.model.CourseVO;
import cn.edu.henau.model.Login;
import cn.edu.henau.model.StudentVO;
import cn.edu.henau.service.UserService;

@Path("/mark")
public class SysRestful {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CheckinDao checkinDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskDao taskDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Login login(Login login) {
		return userService.validateLogin(login);
	}

	@POST
	@Path("/createRollCall")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Checkin> createRollCall(Login login) {
		List<UserEntity> list = userService.createRollCall(login);
		List<Checkin> listCh = new ArrayList<Checkin>();
		for (UserEntity item : list) {
			Checkin ch = new Checkin();
			BeanUtils.copyProperties(item, ch);
			ch.setIsCheck(false);
			ch.settCode(login.getUserCode());
			listCh.add(ch);

		}
		return listCh;
	}
	@POST
	@Path("/queryAuthority")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserEntity> queryAuthority(Login login) {
		List<UserEntity> list = userService.createRollCall(login);

		return list;
	}

	@POST
	@Path("/saveCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CourseVO saveCourse(CourseVO courseVO) {
		
		return userService.saveOrUpdateCourseVO(courseVO);
	}

	@POST
	@Path("/saveCheckin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Checkin> saveCheckin(List<Checkin> checkin) {
		
		return userService.savrOrUpdateCheckin(checkin);
	}

	@GET
	@Path("/loadCourseVO")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CourseVO loadCourseVO(@QueryParam("tcode") String tCode) {
		CourseVO v = new CourseVO();
		v.setCourseTable(courseDao.queryByTCode(tCode));
		v.setTeacherInfo(teacherDao.queryByTCode(tCode));
		return v;
	}
	@GET
	@Path("/loadStudentVO/{userCode}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StudentVO loadStudentVO(@PathParam("userCode") String userCode) {
		logger.info("loadStudentVO============= userCode: "+userCode);
		StudentVO v = new StudentVO();
		List<UserEntity> list = new ArrayList<UserEntity>();
		List<UserEntity> deletelist = new ArrayList<UserEntity>();
		v.setStudentInfo(userDao.queryByUserCode(userCode));
		v.setAddStudent(list);
		v.setDeleteStudentList(deletelist);
		return v;
	}
	@POST
	@Path("/saveStudentVO")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StudentVO saveStudentVO(StudentVO studentVO) {
		
		return userService.savrOrUpdateStudent(studentVO);
	}
	@POST
	@Path("/saveAuthority")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StudentVO saveAuthority(StudentVO studentVO) {
		
		return userService.saveAuthority(studentVO);
	}
	
	@POST
	@Path("/uploadStudent")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserEntity> uploadStudent(
			@FormDataParam("fileData") String fileString,
			@FormDataParam("fileData") InputStream fileInputStream,
			@FormDataParam("fileData") FormDataContentDisposition disposition)
			throws IOException {

		List<UserEntity> list = userService.readStudentXls(fileInputStream);
		return list;
	}
	
	@POST
	@Path("/queryCheckin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Checkin> queryCheckin(Checkin checkin) {

		List<Checkin> list = checkinDao.getCurrentPage(checkin);
		return list;
	}

	@POST
	@Path("/uploadCourse")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> uploadCourse(
			@FormDataParam("fileData") String fileString,
			@FormDataParam("fileData") InputStream fileInputStream,
			@FormDataParam("fileData") FormDataContentDisposition disposition)
			throws IOException {

		List<Course> list = userService.readXls(fileInputStream);
		return list;
	}
	
	@POST
	@Path("/uploadTask")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Task uploadTask(
			@FormDataParam("fileData") InputStream fileInputStream,
			@FormDataParam("fileData") FormDataContentDisposition disposition)
			throws IOException {
		String fileName = new SimpleDateFormat("yyyyMMddHH").format(new Date())
				+ disposition.getFileName();
		String savePath = "D:/workspace_pm/marksystem/src/main/webapp/upload/";
			File file = new File(savePath + fileName);
			    //使用common io的文件写入操作
			    FileUtils.copyInputStreamToFile(fileInputStream, file);
			    //原来自己的文件写入操作
			    //saveFile(fileInputStream, file);
//			    Map<String, String> map=new HashMap<String, String>();
//			    map.put("task", fileName);
			    Task task = new Task();
			    task.setTask(fileName);
		return task;
	}
	
	@POST
	@Path("/saveTask")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> saveTask(List<Task> task) {
		return userService.saveTask(task);
	}

	@GET
	@Path("/loadTask")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Task> loadTask(@QueryParam("tcode") String tcode) {
		
		return taskDao.queryByTCode(tcode);
	}
	@POST
	@Path("/queryGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grade> queryGrade(Checkin checkin) {
		
		return userService.queryGrade(checkin);
	}
	
	@POST
	@Path("/saveGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public  List<Grade> saveGrade( List<Grade> grade) {
		return userService.saveGrade(grade);
	}
	@POST
	@Path("/HistoryGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<Grade>> HistoryGrade(Checkin checkin) {
		
		return userService.queryHistoryGrade(checkin);
	}
	@POST
	@Path("/queryHistoryGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grade> queryHistoryGrade(Checkin checkin) {
		
		return userService.queryByCondition(checkin);
	}

	@GET
	@Path("/loadCourseList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<String> loadCourseList(@QueryParam("tcode") String tCode) {
		return courseDao.loadCourseList(tCode);
	}
	@GET
	@Path("/loadAllCourseList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<String> loadAllCourseList() {
		return courseDao.loadAllCourseList();
	}
	@GET
	@Path("/queryByTcodeAndCourse")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Task> queryByTcodeAndCourse(@QueryParam("tcode") String tCode,@QueryParam("course") String course) {
		return taskDao.queryByTcodeAndCourse(tCode,course);
	}
	@GET
	@Path("/loadTeacherList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<UserEntity> loadTeacherList(@QueryParam("identity") String identity) {
		return userDao.loadTeacherList(identity);
		/*
		*
		*
		* */

	}
}
