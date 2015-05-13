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
		System.out.println("进入CreateClassAction");
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
			String fromusernaem = map.get("FromUserName");// 发送方帐号（open_id）
			String tousername = map.get("ToUserName");
			Student stu = StudentService.getByCode(fromusernaem);
			if (stu == null) {
				// 处理异常
			}
			if (stu.getMyClass() != null) {
				// 提醒用户已经在某个班级了
				System.out.println("提醒用户已经在某个班级了");
				ResponseUtil.sendTextResponse(response, "你已经在某班级里，暂时不支持创建新班级。",
						fromusernaem);
			} else {
				// 创建班级
				// 1生成随机班级代码
				String rc = RandomCodeUtil.RandomCode(4);
				while (MyClassService.getByCode(rc) != null) {
					rc = RandomCodeUtil.RandomCode(4);
				}

				// 2new MyClass
				MyClass newClass = new MyClass();
				newClass.setClassCode(rc);
				newClass.setStudents(1);

				// 调用Service层添加
				if (MyClassService.addMyClass(newClass)) {
					System.out.println("提醒用户已经创建了新班级");
					ResponseUtil.sendTextResponse(response, "你创建的新班级代码是“"
							+ rc + "”，请牢记！，其他同学通过该代码加入班级", fromusernaem);
					//把新增加的班级与该创建人绑定
					stu.setMyClass(newClass);
					StudentService.change(stu);
					//修改创建人角色
					
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
