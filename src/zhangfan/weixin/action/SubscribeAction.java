package zhangfan.weixin.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import util.ResponseUtil;
import util.XMLUtil;
import zhangfan.weixin.domain.Student;
import zhangfan.weixin.msg.response.TextRespMsg;
import zhangfan.weixin.service.StudentService;

import com.opensymphony.xwork2.ActionSupport;

public class SubscribeAction extends ActionSupport {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private StudentService StudentService;
	@SuppressWarnings("unchecked")
	public String execute()throws Exception
	{
		System.out.println("进入SubscribeAction");
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
		//System.out.println("map!=null?");
		if(map!=null)
		{
			System.out.println("map not null");
			String tousername = map.get("ToUserName");//公众账号
			String fromusername = map.get("FromUserName");// 发送方帐号（open_id）
			String event = map.get("Event");
			System.out.println(tousername+":"+fromusername+":"+event);
			if(event.equals(XMLUtil.EVENT_TYPE_SUBSCRIBE))//关注、订阅
			{
				System.out.println("订阅");
				//判断是否已经存在
				System.out.println("用户code："+fromusername);
				Student stu =StudentService.getByCode(fromusername);
				
				//存在则更新，不存在则创建
				if(stu!=null)
				{
					System.out.println("stu not null");
					stu.setStudentCode(fromusername);
					stu.setMyClass(null);
					StudentService.change(stu);
				}
				else
				{
					System.out.println("stu null");
					stu = new Student();
					stu.setStudentCode(fromusername);
					
					StudentService.addStudent(stu);
				}
				//生成回复
				System.out.println("生成回复信息");
				String content = ResponseUtil.getMenu();
				TextRespMsg resp = new TextRespMsg();
				resp.setToUserName(fromusername);
				resp.setFromUserName(tousername);
				resp.setCreateTime(new Date().getTime());
				resp.setMsgType("text");
				resp.setContent(content);
				ResponseUtil.sendTextResponse(response, resp);
				return null;
			}
			else if(event.equals(XMLUtil.EVENT_TYPE_UNSUBSCRIBE))//取消订阅
			{
				System.out.println("取消订阅");
				//判断是否存在该学生
				Student stu =StudentService.getByCode(fromusername);
				if(stu!=null)
				//存在则删除
				{
					StudentService.deleteStudent(stu);
				}
				return null;
			}
			
		}
		System.out.println("map null");
		return null;
	}
	public StudentService getStudentService() {
		return StudentService;
	}
	public void setStudentService(StudentService studentService) {
		StudentService = studentService;
	}

	
}
