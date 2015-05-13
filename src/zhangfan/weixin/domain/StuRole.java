package zhangfan.weixin.domain;

public class StuRole {
	private Integer StuRoleID;
	private Student Student;
	private Role Role;
	public Integer getStuRoleID() {
		return StuRoleID;
	}
	public void setStuRoleID(Integer stuRoleID) {
		StuRoleID = stuRoleID;
	}
	public Student getStudent() {
		return Student;
	}
	public void setStudent(Student student) {
		Student = student;
	}
	public Role getRole() {
		return Role;
	}
	public void setRole(Role role) {
		Role = role;
	}
	
}
