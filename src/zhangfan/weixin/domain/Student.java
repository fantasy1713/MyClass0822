package zhangfan.weixin.domain;

public class Student {
	private Integer StudentID;// 主键
	private String StudentCode;// 学生代码（微信号）
	private MyClass MyClass;// 班级

	public Integer getStudentID() {
		return StudentID;
	}

	public void setStudentID(Integer studentID) {
		StudentID = studentID;
	}

	public String getStudentCode() {
		return StudentCode;
	}

	public void setStudentCode(String studentCode) {
		StudentCode = studentCode;
	}

	public MyClass getMyClass() {
		return MyClass;
	}

	public void setMyClass(MyClass myClass) {
		MyClass = myClass;
	}

}
