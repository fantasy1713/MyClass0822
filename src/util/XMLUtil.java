package util;

import java.io.InputStream;
import java.io.Writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import zhangfan.weixin.msg.response.*;

public class XMLUtil {
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// 文本类型的值
	public static final String TEXTTYPE_CREATECLASS = "0";// 创建班
	public static final String TEXTTYPE_JOINCLASS = "1";// 加入班
	public static final String TEXTTYPE_SENDNOTICE = "2";// 发通知
	public static final String TEXTTYPE_SENDHOMEWORK = "3";// 布置作业
	public static final String TEXTTYPE_SEARCHNOTICE = "4";// 查看通知
	public static final String TEXTTYPE_SEARCHHOMEWORK = "5";// 查看作业
	public static final String TEXTTYPE_QUITJOB = "6";// 辞职
	public static final String TEXTTYPE_GETJOB = "7";// 担任班干
	public static final String TEXTTYPE_HELP = "?";// 帮助
	public static final String TEXTTYPE_HELP1 = "？";// 帮助
	

	public static Map<String, String> parseXML(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		System.out.println("parseXML");
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		System.out.println(inputStream.toString());
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;

	}

	public static String TextToXml(TextRespMsg trm) {
		xstream.alias("xml", trm.getClass());
		String xml = xstream.toXML(trm);
		System.out.println(xml);
		return xml;
//		return null;
	}

	public static String PicTextToXml(PicTextRespMsg ptrm) {
		xstream.alias("xml", ptrm.getClass());
		String xml = xstream.toXML(ptrm);
		System.out.println(xml);
		return xml;
//		return null;
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}
