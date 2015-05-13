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
		System.out.println("进入InterfaceComfirmAction");
		//获取请求和响应
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
		  
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
		if(true)//if(CheckSignature(token, timestamp, nonce, signature))
		{
			String method = ServletActionContext.getRequest().getMethod();
			if(method.equals("POST"))
			{
				System.out.println("doPost");
				//调用核心服务类处理请求
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
		//对请求的参数和自己的token进行排序，并连接排序后的结果为一个字符串
		String[] strSet = new String[]{token, timestamp,nonce};
		java.util.Arrays.sort(strSet);
		String temp = "";
		for(int i =0;i<strSet.length;i++)
		{
			temp+=strSet[i];
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest  =md.digest(temp.toString().getBytes());//加密成字节数组
			String resultStr = new BigInteger(1,digest).toString(16);//将加密后的字节数组转换成字符串
			
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
			System.out.println("进入dopostFunc");
			Map<String, String> map = XMLUtil.parseXML(request,response);
			if(map!=null)
			{
				System.out.println("map not null");
				String tousername = map.get("ToUserName");//公众账号
				String fromusername = map.get("FromUserName");// 发送方帐号（open_id）
				String msgtype = map.get("MsgType");
				System.out.println(msgtype);
				if(msgtype.equals(XMLUtil.REQ_MESSAGE_TYPE_TEXT))
				{
					String content =map.get("Content");
					System.out.println(content);
					request.setAttribute("ReqMap", map);//把解析后得到的map放入request中
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
						//生成回复
						System.out.println("生成回复信息");
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
						request.setAttribute("ReqMap", map);//把解析后得到的map放入request中
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
			System.out.println("异常");
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
