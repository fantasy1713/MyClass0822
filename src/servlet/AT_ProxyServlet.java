package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AT_ProxyServlet
 */
@WebServlet("/AT_ProxyServlet")
public class AT_ProxyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		String appid = getInitParameter("appid");
		String appsecret = getInitParameter("appsecred");
		if(appid!=null&&appsecret!=null){
			//开启中控器线程
			
		}
	}

	

}
