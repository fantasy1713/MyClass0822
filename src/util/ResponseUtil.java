package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zhangfan.weixin.msg.response.*;

public class ResponseUtil {

	public static void sendTextResponse(HttpServletResponse response,
			TextRespMsg Msg) {
		String RespXml = XMLUtil.TextToXml(Msg);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(RespXml);
			
			out.close();
		} catch (IOException e) {
			System.out.println("发生异常");
			e.printStackTrace();
		}

	}
	public static void sendTextResponse(HttpServletResponse response,String content ,String toUserName)
	{
		TextRespMsg msg = new TextRespMsg();
		msg.setFromUserName("gh_bf2ff43417e5");
		msg.setToUserName(toUserName);
		msg.setMsgType("text");
		msg.setCreateTime(new Date().getTime());
		msg.setContent(content);
		String RespXml = XMLUtil.TextToXml(msg);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(RespXml);
			
			out.close();
		} catch (IOException e) {
			System.out.println("发生异常");
			e.printStackTrace();
		}
		
	}

	public static void sendPicTextResponse(HttpServletResponse response,			PicTextRespMsg Msg) {
		String RespXml = XMLUtil.PicTextToXml(Msg);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(RespXml);
			out.close();
		} catch (IOException e) {
			System.out.println("发生异常");
			e.printStackTrace();
		}
	}
	
	public static String getMenu()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("同学你好，欢迎使用“咱们班”，请回复数字选择服务：").append("\n\n");
		sb.append("0:创建班\n");
		sb.append("1:加入班\n");
		sb.append("2:发通知\n");
		sb.append("3:布置作业\n");
		sb.append("4:查看通知\n");
		sb.append("5:查看作业\n");
		sb.append("6:辞职\n");
		sb.append("7:担任班干\n\n");
		
		sb.append("如需查看菜单请回复“?”");
		
		return sb.toString();
	}
}
