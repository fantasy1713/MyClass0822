package zhangfan.weixin.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import util.ResponseUtil;
import util.XMLUtil;
import zhangfan.weixin.domain.MyClass;
import zhangfan.weixin.msg.response.TextRespMsg;
import zhangfan.weixin.service.MyClassService;

import com.opensymphony.xwork2.ActionSupport;

public class InterfaceComfirmAction extends ActionSupport {
	private MyClassService MyClassService;
	
	public String execute() throws Exception
	{
		System.out.println("����InterfaceComfirmAction");
		//��ȡ�������Ӧ
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce  = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String token = "zhang";
		  
		// ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ�� 
		if(true)//if(CheckSignature(token, timestamp, nonce, signature))
		{
			String method = ServletActionContext.getRequest().getMethod();
			if(method.equals("POST"))
			{
				System.out.println("doPost");
				//���ú��ķ����ദ������
				String result = this.doPost(request, response);
				
				
				return result;
			}
			else
			{
				PrintWriter out = response.getWriter();
				System.out.println("doGet");
				out.print(echostr);
				out.close();
				out=null;

			}
		}
		
		
		
		return null;
			
		
	}
	
	private boolean CheckSignature(String token,String timestamp,String nonce,String signature )
	{
		//������Ĳ������Լ���token�������򣬲����������Ľ��Ϊһ���ַ���
		String[] strSet = new String[]{token, timestamp,nonce};
		java.util.Arrays.sort(strSet);
		String temp = "";
		for(int i =0;i<strSet.length;i++)
		{
			temp+=strSet[i];
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest  =md.digest(temp.toString().getBytes());//���ܳ��ֽ�����
			String resultStr = new BigInteger(1,digest).toString(16);//�����ܺ���ֽ�����ת�����ַ���
			
			return resultStr !=null ?resultStr.equals(signature):false;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	private String doPost(HttpServletRequest request,HttpServletResponse response)
	{
		
		try
		{
			System.out.println("����dopostFunc");
			Map<String, String> map = XMLUtil.parseXML(request,response);
			if(map!=null)
			{
				System.out.println("map not null");
				String tousername = map.get("ToUserName");//�����˺�
				String fromusername = map.get("FromUserName");// ���ͷ��ʺţ�open_id��
				String msgtype = map.get("MsgType");
				System.out.println(msgtype);
				if(msgtype.equals(XMLUtil.REQ_MESSAGE_TYPE_TEXT))
				{
					String content =map.get("Content");
					System.out.println(content);
					request.setAttribute("ReqMap", map);//�ѽ�����õ���map����request��
					if(content.equals(XMLUtil.TEXTTYPE_CREATECLASS))
					{
						
						return "CREATECLASS";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_JOINCLASS))
					{
						return "JOINCLASS";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_SENDNOTICE))
					{
						return "SENDNOTICE";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_SENDHOMEWORK))
					{
						return "SENDHOMEWORK";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_SEARCHNOTICE))
					{
						return "SEARCHNOTICE";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_SEARCHHOMEWORK))
					{
						return "SEARCHHOMEWORK";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_QUITJOB))
					{
						return "QUITJOB";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_GETJOB))
					{
						return "GETJOB";
					}
					else if(content.equals(XMLUtil.TEXTTYPE_HELP)||content.equals(XMLUtil.TEXTTYPE_HELP1))
					{
						//���ɻظ�
						System.out.println("���ɻظ���Ϣ");
						String Content = ResponseUtil.getMenu();
						TextRespMsg resp = new TextRespMsg();
						resp.setToUserName(fromusername);
						resp.setFromUserName(tousername);
						resp.setCreateTime(new Date().getTime());
						resp.setMsgType("text");
						resp.setContent(Content);
						ResponseUtil.sendTextResponse(response, resp);
						return SUCCESS;
					}
				}
				else if(msgtype.equals(XMLUtil.REQ_MESSAGE_TYPE_EVENT))
				{
					String Event = map.get("Event");
					
					if(Event!=null&&(Event.equals(XMLUtil.EVENT_TYPE_SUBSCRIBE)||Event.equals(XMLUtil.EVENT_TYPE_UNSUBSCRIBE)))
					{
						System.out.println("return value:"+Event.toUpperCase());
						request.setAttribute("ReqMap", map);//�ѽ�����õ���map����request��
							return Event.toUpperCase();
					}
				}
				
				else
				{
					return "MISSMACH";
				}	
			}
			else 
			{
				System.out.println("map null");
			}
		}
		catch(Exception e)
		{
			System.out.println("�쳣");
			e.printStackTrace();
			return ERROR;
		}
		
			return SUCCESS;
	}
	
	
	public MyClassService getMyClassService() {
		return MyClassService;
	}

	public void setMyClassService(MyClassService myClassService) {
		MyClassService = myClassService;
	}

}
