package zhangfan.weixin.action;

import zhangfan.weixin.domain.Student;
import zhangfan.weixin.service.StudentService;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	private StudentService StudentService;
	
	public String Testadd()throws Exception
	{
		
		return null;
	}
	public String TestgetByCode()throws Exception
	{
		Student stu = StudentService.getByCode("1234");
		return null;
	}
	public StudentService getStudentService() {
		return StudentService;
	}
	public void setStudentService(StudentService studentService) {
		this.StudentService = studentService;
	}
	

}
