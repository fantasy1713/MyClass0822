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
		System.out.println("����SubscribeAction");
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
			String tousername = map.get("ToUserName");//�����˺�
			String fromusername = map.get("FromUserName");// ���ͷ��ʺţ�open_id��
			String event = map.get("Event");
			System.out.println(tousername+":"+fromusername+":"+event);
			if(event.equals(XMLUtil.EVENT_TYPE_SUBSCRIBE))//��ע������
			{
				System.out.println("����");
				//�ж��Ƿ��Ѿ�����
				System.out.println("�û�code��"+fromusername);
				Student stu =StudentService.getByCode(fromusername);
				
				//��������£��������򴴽�
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
				//���ɻظ�
				System.out.println("���ɻظ���Ϣ");
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
			else if(event.equals(XMLUtil.EVENT_TYPE_UNSUBSCRIBE))//ȡ������
			{
				System.out.println("ȡ������");
				//�ж��Ƿ���ڸ�ѧ��
				Student stu =StudentService.getByCode(fromusername);
				if(stu!=null)
				//������ɾ��
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
