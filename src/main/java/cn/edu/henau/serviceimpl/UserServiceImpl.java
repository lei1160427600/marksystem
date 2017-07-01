package cn.edu.henau.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.henau.dao.CheckinDao;
import cn.edu.henau.dao.CourseDao;
import cn.edu.henau.dao.GradeDao;
import cn.edu.henau.dao.TaskDao;
import cn.edu.henau.dao.TeacherDao;
import cn.edu.henau.dao.UserDao;
import cn.edu.henau.entity.Checkin;
import cn.edu.henau.entity.Course;
import cn.edu.henau.entity.Grade;
import cn.edu.henau.entity.Task;
import cn.edu.henau.entity.UserEntity;
import cn.edu.henau.model.CheckInVO;
import cn.edu.henau.model.CourseVO;
import cn.edu.henau.model.Login;
import cn.edu.henau.model.StudentVO;
import cn.edu.henau.service.UserService;
import cn.edu.henau.util.Page;
@Service
public class UserServiceImpl implements UserService {
	 //日志对象
//    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    

    //注入Service依赖
    @Autowired //@Resource
    private UserDao userDao;
    @Autowired
    private CheckinDao checkinDao;
    @Autowired
    private CourseDao courseDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private GradeDao gradeDao;
	@Override
	public List<UserEntity> getUserList() {
		// TODO Auto-generated method stub
		return userDao.queryAll(0, 4);
	}

	@Override
	public UserEntity getById(long userId) {
		// TODO Auto-generated method stub
		return userDao.queryById(userId);
	}

	@Override
	public UserEntity getByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userDao.queryByUserCode(userCode);
	}
	
	@Override
	public Login validateLogin(Login login){
		UserEntity entity = this.getByUserCode(login.getUserCode());
		Login user = new Login();
		String message = null;
		if(entity == null){
			message = "用户名不存在";
		}else{
			BeanUtils.copyProperties(entity, user);
			if(login.getPwd().equals(entity.getUserPwd())){
				if(!login.getIdentity().equals(user.getIdentity())){
					message = "请确认你的身份";
				}else{
					message = "success";
				}
			}else{
				message = "请输入正确的密码";
			}
		}
		user.setMessage(message);
		return user;
	}
	
	@Override
	public List<Checkin> savrOrUpdateCheckin(List<Checkin> checkin){
		if(CollectionUtils.isNotEmpty(checkin)){
			List<Checkin> insertList =new ArrayList<>();
			List<Checkin> updateList =new ArrayList<>();
			for(Checkin item : checkin){
				if(item.getCheckinId()>0){
					updateList.add(item);
				}else{
					insertList.add(item);
				}
			}
			if(CollectionUtils.isNotEmpty(insertList)){
				checkinDao.insertCheckin(insertList);
			}
			if(CollectionUtils.isNotEmpty(updateList)){
				checkinDao.updateCheckin(updateList);
			}
		}
		return checkin;
	}
	
	@Override
	public  List<UserEntity> createRollCall(Login login){
		List<UserEntity> list = userDao.queryByProAndClass(login.getProfession(), login.getUserClass());
		Integer random = login.getRandom();
		if(CollectionUtils.isNotEmpty(list)&&random!= null){
			if(random<list.size()){
				Collections.shuffle(list);
				list = list.subList(0, random);
			}
		}
		return list;
	}
	
	@Override
	public Page<CheckInVO> getCurrentPage(CheckInVO vo,int pageIndex,int countPerPage){
		Page<CheckInVO> page = new Page<CheckInVO>();
		List<CheckInVO> listCh = new ArrayList<CheckInVO>();
		Checkin checkin=new Checkin(); 
		BeanUtils.copyProperties(vo, checkin);
		List<Checkin> list =checkinDao.getCurrentPage(checkin);
		Long results =(long) list.size();
		List<Checkin>  pageList = list.subList((pageIndex-1)*countPerPage, countPerPage);
		for(Checkin item : pageList){
			CheckInVO checkInVO = new CheckInVO();
			BeanUtils.copyProperties(item, checkInVO);
			listCh.add(checkInVO);
		}
	    page.setRows(listCh);  
        page.setPageIndex(pageIndex);  
        Long pageCount = results % countPerPage == 0 ? results / countPerPage : results / countPerPage + 1;  
        page.setPageCount(pageCount);  
		return page;
		
	}
	
	@Override
	public List<UserEntity> readStudentXls(InputStream is) throws IOException {  
        @SuppressWarnings("resource")
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);  
        UserEntity userEntity = null;  
        List<UserEntity> list = new ArrayList<UserEntity>();  
        // 循环工作表Sheet  
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {  
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);  
            if (xssfSheet == null) {  
                continue;  
            }  
            // 循环行Row  
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {  
                XSSFRow hssfRow = xssfSheet.getRow(rowNum); 
                if (hssfRow != null) {  
                	userEntity = new UserEntity();  
                    XSSFCell code = hssfRow.getCell(0);  
                    XSSFCell name = hssfRow.getCell(1);  
                    XSSFCell durity = hssfRow.getCell(2);  
                    XSSFCell profession = hssfRow.getCell(3);  
                    XSSFCell classroom = hssfRow.getCell(4);  
                    XSSFCell telephone = hssfRow.getCell(5);  
                    XSSFCell tcode = hssfRow.getCell(6);  
                    userEntity.setUserCode(getValue(code).substring(getValue(code).indexOf("-")+1,getValue(code).length()));
                    userEntity.setUserName(getValue(name));
                    if(durity!=null){
                    	userEntity.setDuties(getValue(durity));
                    }
                    userEntity.setProfession(getValue(profession).substring(0, getValue(profession).indexOf("-")));
                    userEntity.setUserClass(getValue(classroom).substring(0, getValue(classroom).indexOf("-")));
                    userEntity.setTelephoneNo(getValue(telephone).substring(getValue(telephone).indexOf("-")+1,getValue(telephone).length()));
                    userEntity.settCode(getValue(tcode));
                    list.add(userEntity);  
                }  
            }  
        }  
        return list;  
    } 
	
	@Override
	public List<Course> readXls(InputStream is) throws IOException {  
//        InputStream is = new FileInputStream(path);  
        @SuppressWarnings("resource")
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);  
        Course course = null;  
        List<Course> list = new ArrayList<Course>();  
        // 循环工作表Sheet  
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {  
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);  
            if (xssfSheet == null) {  
                continue;  
            }  
            // 循环行Row  
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {  
                XSSFRow hssfRow = xssfSheet.getRow(rowNum); 
                if (hssfRow != null) {  
                	course = new Course();  
                    XSSFCell course1 = hssfRow.getCell(0);  
                    XSSFCell profession = hssfRow.getCell(1);  
                    XSSFCell classRoom = hssfRow.getCell(2);  
                    XSSFCell courseDate = hssfRow.getCell(3);  
                    XSSFCell tcode = hssfRow.getCell(4);  
                    course.setCourse(getValue(course1));  
                    course.setProfession(getValue(profession).substring(0, getValue(profession).indexOf("-")));  
                    course.setClassRoom(getValue(classRoom).substring(0, getValue(classRoom).indexOf("-")));  
                    course.setCourseDate(getValue(courseDate));  
                    course.settCode(getValue(tcode).substring(getValue(tcode).indexOf("-")+1,getValue(tcode).length()));
                    list.add(course);  
                }  
            }  
        }  
        return list;  
    }  
	 @SuppressWarnings({ "deprecation", "static-access" })
	private String getValue(XSSFCell xssfCell) {  
         if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {  
             // 返回布尔类型的值  
             return String.valueOf(xssfCell.getBooleanCellValue());  
         } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {  
             // 返回数值类型的值  
             return String.valueOf(xssfCell.getNumericCellValue());  
         } else {  
             // 返回字符串类型的值  
             return xssfCell.getStringCellValue();  
         }  
     }
	 
	 @Override
	 public CourseVO saveOrUpdateCourseVO(CourseVO courseVO){
		 
		 List<Course> listCourse = courseVO.getCourseTable();
			if (CollectionUtils.isNotEmpty(listCourse)) {
				List<Course> insertList = new ArrayList<Course>();
				List<Course> updateList = new ArrayList<Course>();
				for (Course item : listCourse) {
					if (item.getCourseId() > 0) {
						updateList.add(item);
					} else {
						insertList.add(item);
					}
				}
				if (CollectionUtils.isNotEmpty(insertList)) {
					courseDao.insertCourse(insertList);
				}
				if (CollectionUtils.isNotEmpty(updateList)) {
					courseDao.updateCourse(updateList);
				}
			}
			if(CollectionUtils.isNotEmpty(courseVO.getDeleteCourseList())){
				courseDao.deleteCourse(courseVO.getDeleteCourseList());
			}
			if (courseVO.getTeacherInfo() != null) {
				if (courseVO.getTeacherInfo().gettId() > 0) {
					teacherDao.updateTeacher(courseVO.getTeacherInfo());
				} else {
					teacherDao.insertTeacher(courseVO.getTeacherInfo());
				}
			}
		return courseVO;
		 
	 }

	@Override
	public StudentVO savrOrUpdateStudent(StudentVO studentVO) {
		UserEntity student = studentVO.getStudentInfo();
		List<UserEntity> list= studentVO.getAddStudent();
		if(student != null){
			if(student.getUserId()>0){
				userDao.updateStudent(student);
			}else{
				userDao.insertStudent(student);
			}
		}
		if(CollectionUtils.isNotEmpty(list)){
			List<UserEntity> insertlist= new ArrayList<UserEntity>();
			List<UserEntity> updatelist= new ArrayList<UserEntity>();
			for(UserEntity item : list){
				if(item.getUserId()>0){
					updatelist.add(item);
				}else{
					item.setUserPwd(item.getUserCode());
					item.setIdentity("学生");
					insertlist.add(item);
				}
			}
			if(CollectionUtils.isNotEmpty(insertlist)){
				userDao.insertStudentList(insertlist);
			}
			if(CollectionUtils.isNotEmpty(updatelist)){
				userDao.updateStudentList(updatelist);
			}
		}
		if(CollectionUtils.isNotEmpty(studentVO.getDeleteStudentList())){
			userDao.deleteUser(studentVO.getDeleteStudentList());
		}
		return studentVO;
	}

	@Override
	public List<Task> saveTask(List<Task> task) {
		if(CollectionUtils.isNotEmpty(task)){
			List<Task> insertTask = new ArrayList<Task>();
			List<Task> updateTask = new ArrayList<Task>();
			
			for(Task item : task){
				if(item.getTaskId()>0){
					updateTask.add(item);
				}else{
					insertTask.add(item);
				}
			}
			if(CollectionUtils.isNotEmpty(insertTask)){
				taskDao.insertTask(insertTask);
			}
			if(CollectionUtils.isNotEmpty(updateTask)){
				taskDao.updateTask(updateTask);
			}
		}
		return task;
	}
	
	@Override
	public List<Grade> queryGrade(Checkin checkin){
		 int Count = checkinDao.queryRollCallCount();

			List<Grade> grade = new ArrayList<Grade>();
			List<Checkin> list = checkinDao.getCurrentPage(checkin);
			List<UserEntity> listUser = userDao.queryByProAndClass(checkin.getProfession(),checkin.getUserClass());
			if(CollectionUtils.isNotEmpty(listUser)){
				for(UserEntity item : listUser){
					 int AbsenteeismCount = 0;
					 int Absenteeism = 0;
					 int Urgent = 0;
					 int Leave = 0;
					for(Checkin it : list ){
						if(item.getUserCode().equals(it.getUserCode()) && !it.getIsCheck()){
							AbsenteeismCount=AbsenteeismCount+1;
							if(it.getReason()==null){
								Absenteeism=Absenteeism+1;
							}else if(it.getReason().equals("1")){
								Absenteeism=Absenteeism+1;
							}else if(it.getReason().equals("0")){
								Leave=Leave+1;
							}else {
								Urgent=Urgent+1;
							}
						}
					}
					Grade vo = new Grade();
					vo.setUserName(item.getUserName());
					vo.setUserCode(item.getUserCode());
					vo.setAbsenteeismCount(AbsenteeismCount);
					vo.setAbsenteeism(Absenteeism);
					vo.setUrgent(Urgent);
					vo.setLeaveCount(Leave);
					vo.setCount(Count);
					vo.settCode(checkin.gettCode());
					vo.setProfession(item.getProfession());
					vo.setUserClass(item.getUserClass());
					grade.add(vo);
				}
			}
			
			
			return grade;
	}

	@Override
	public List<Grade> saveGrade(List<Grade> grade) {
		if(CollectionUtils.isNotEmpty(grade)){
			List<Grade> insertList = new ArrayList<Grade>();
			List<Grade> updateList = new ArrayList<Grade>();
			for(Grade item : grade ){
				if(item.getGradeId()>0){
					updateList.add(item);
				}else{
					insertList.add(item);
				}
			}
			if(CollectionUtils.isNotEmpty(insertList)){
				gradeDao.insertGrade(insertList);
			}
			if(CollectionUtils.isNotEmpty(updateList)){
				gradeDao.updateGrade(updateList);
			}
		}
		return grade;
	}

	@Override
	public List<List<Grade>> queryHistoryGrade(Checkin checkin) {
		List<Date> dateList = gradeDao.queryaddDate();
		List<Grade> list = gradeDao.queryByTCode(checkin.gettCode());
		List<List<Grade>> all = new ArrayList<List<Grade>>();
		for(Date date:dateList){
			List<Grade> listItem = new ArrayList<Grade>();
			for(Grade item : list ){
				if(item.getAddDate().equals(date)){
					listItem.add(item);
				}
			}
			all.add(listItem);
		}
		return all;
	}

	@Override
	public List<Grade> queryByCondition(Checkin checkin) {
		return gradeDao.queryByCondition(checkin);
	}

	@Override
	public StudentVO saveAuthority(StudentVO studentVO) {
		List<UserEntity> list =studentVO.getAddStudent();
		List<UserEntity> updatelist =new ArrayList<UserEntity>();
		List<UserEntity> deletelist =studentVO.getDeleteStudentList();
		if(CollectionUtils.isNotEmpty(list)){
			for(UserEntity item:list){
				if(item.getAuthority()!=null && item.getAuthority()){
					item.setIdentity("管理员");
				}else{
					item.setIdentity(null);
				}
				updatelist.add(item);
			}
			userDao.updateIdentityAndAuthority(updatelist);
		}
		if(CollectionUtils.isNotEmpty(deletelist)){
			userDao.deleteUser(deletelist);
		}
		return studentVO;
	}  
}
