package zhangfan.weixin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ResponseAction extends ActionSupport {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	public String execute()throws Exception
	{
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		return null;
	}

	
}
