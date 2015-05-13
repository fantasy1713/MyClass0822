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
			System.out.println("�����쳣");
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
			System.out.println("�����쳣");
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
			System.out.println("�����쳣");
			e.printStackTrace();
		}
	}
	
	public static String getMenu()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("ͬѧ��ã���ӭʹ�á����ǰࡱ����ظ�����ѡ�����").append("\n\n");
		sb.append("0:������\n");
		sb.append("1:�����\n");
		sb.append("2:��֪ͨ\n");
		sb.append("3:������ҵ\n");
		sb.append("4:�鿴֪ͨ\n");
		sb.append("5:�鿴��ҵ\n");
		sb.append("6:��ְ\n");
		sb.append("7:���ΰ��\n\n");
		
		sb.append("����鿴�˵���ظ���?��");
		
		return sb.toString();
	}
}
