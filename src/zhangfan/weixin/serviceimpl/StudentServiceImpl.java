package zhangfan.weixin.serviceimpl;

import zhangfan.weixin.domain.Student;
import zhangfan.weixin.service.StudentService;
import zhangfan.weixin.dao.*;

public class StudentServiceImpl implements StudentService {
	private StudentDao StudentDao;

	@Override
	public Student getByCode(String code) {
		Student stu =StudentDao.getByCode(code); 
		System.out.println("stu:"+stu);
		return stu;
	}

	public StudentDao getStudentDao() {
		return StudentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		StudentDao = studentDao;
	}

	@Override
	public void change(Student student) {
		StudentDao.update(student);
		
	}

	@Override
	public void addStudent(Student student) {
		System.out.println("addstudentService");
		StudentDao.add(student);
		
	}

	@Override
	public void deleteStudent(Student student) {
		StudentDao.delete(student.getStudentID());
	}
	

}
