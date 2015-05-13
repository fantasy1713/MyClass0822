package zhangfan.weixin.domain;

import java.util.Date;

public class Homework {
	private Integer HomeworkID;//主键
	private MyClass MyClass;//班级
	private Date Date;//发送日期
	private String Content;//内容
	private Student Student;//发送人
	public Integer getHomeworkID() {
		return HomeworkID;
	}
	public void setHomeworkID(Integer homeworkID) {
		HomeworkID = homeworkID;
	}
	public MyClass getMyClass() {
		return MyClass;
	}
	public void setMyClass(MyClass myClass) {
		MyClass = myClass;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Student getStudent() {
		return Student;
	}
	public void setStudent(Student student) {
		Student = student;
	}
	
}
