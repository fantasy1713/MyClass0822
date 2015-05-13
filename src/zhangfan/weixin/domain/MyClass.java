package zhangfan.weixin.domain;

public class MyClass {
	private Integer ClassID;// 主键
	private String ClassCode;// 班级代码
	private int Students;// 人数
	public Integer getClassID() {
		return ClassID;
	}
	public void setClassID(Integer classID) {
		ClassID = classID;
	}
	public String getClassCode() {
		return ClassCode;
	}
	public void setClassCode(String classCode) {
		ClassCode = classCode;
	}
	public int getStudents() {
		return Students;
	}
	public void setStudents(int students) {
		Students = students;
	}

}
