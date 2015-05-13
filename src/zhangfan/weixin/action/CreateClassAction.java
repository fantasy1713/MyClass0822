package zhangfan.weixin.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import util.RandomCodeUtil;
import util.ResponseUtil;
import util.XMLUtil;
import zhangfan.weixin.domain.MyClass;
import zhangfan.weixin.domain.Student;
import zhangfan.weixin.msg.response.TextRespMsg;


import com.opensymphony.xwork2.ActionSupport;
import zhangfan.weixin.service.*;

public class CreateClassAction extends ActionSupport {

	private HttpServletRequest request;
	private HttpServletResponse response;
	// Service
	private MyClassService MyClassService;
	private StudentService StudentService;
	private StuRoleService StuRoleService;
	//

	public String execute() throws Exception {
		System.out.println("����CreateClassAction");
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Map<String, String> map;
		try{
		 map=(Map<String,String>) request.getAttribute("ReqMap");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		if (map != null) {
			String fromusernaem = map.get("FromUserName");// ���ͷ��ʺţ�open_id��
			String tousername = map.get("ToUserName");
			Student stu = StudentService.getByCode(fromusernaem);
			if (stu == null) {
				// �����쳣
			}
			if (stu.getMyClass() != null) {
				// �����û��Ѿ���ĳ���༶��
				System.out.println("�����û��Ѿ���ĳ���༶��");
				ResponseUtil.sendTextResponse(response, "���Ѿ���ĳ�༶���ʱ��֧�ִ����°༶��",
						fromusernaem);
			} else {
				// �����༶
				// 1��������༶����
				String rc = RandomCodeUtil.RandomCode(4);
				while (MyClassService.getByCode(rc) != null) {
					rc = RandomCodeUtil.RandomCode(4);
				}

				// 2new MyClass
				MyClass newClass = new MyClass();
				newClass.setClassCode(rc);
				newClass.setStudents(1);

				// ����Service�����
				if (MyClassService.addMyClass(newClass)) {
					System.out.println("�����û��Ѿ��������°༶");
					ResponseUtil.sendTextResponse(response, "�㴴�����°༶�����ǡ�"
							+ rc + "�������μǣ�������ͬѧͨ���ô������༶", fromusernaem);
					//�������ӵİ༶��ô����˰�
					stu.setMyClass(newClass);
					StudentService.change(stu);
					//�޸Ĵ����˽�ɫ
					
					return "SUCCESS";
				}
				return "ERROR";
			}
		}

		return null;

	}

	public MyClassService getMyClassService() {
		return MyClassService;
	}

	public void setMyClassService(MyClassService myClassService) {
		MyClassService = myClassService;
	}

	public StudentService getStudentService() {
		return StudentService;
	}

	public void setStudentService(StudentService studentService) {
		StudentService = studentService;
	}

	public StuRoleService getStuRoleService() {
		return StuRoleService;
	}

	public void setStuRoleService(StuRoleService stuRoleService) {
		StuRoleService = stuRoleService;
	}
	

}
