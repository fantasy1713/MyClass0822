package zhangfan.weixin.domain;
import java.util.Date;
public class Notice {
	private Integer NoticeID;//����
	private MyClass MyClass;//�༶
	private Date Date;//��������
	private String Content;//����
	private Student Student;//������
	public Integer getNoticeID() {
		return NoticeID;
	}
	public void setNoticeID(Integer noticeID) {
		NoticeID = noticeID;
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
