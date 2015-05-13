package zhangfan.weixin.service;

import zhangfan.weixin.domain.Student;

public interface StudentService {
	public Student getByCode(String code);
	public void change(Student student);
	public void addStudent(Student student);
	public void deleteStudent(Student student);
}
