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
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * ������Ϣ���ͣ�ͼ��
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * ������Ϣ���ͣ��ı�
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * ������Ϣ���ͣ�ͼƬ
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * ������Ϣ���ͣ�����λ��
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * ������Ϣ���ͣ���Ƶ
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * ������Ϣ���ͣ�����
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * �¼����ͣ�subscribe(����)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * �¼����ͣ�unsubscribe(ȡ������)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * �¼����ͣ�CLICK(�Զ���˵�����¼�)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	// �ı����͵�ֵ
	public static final String TEXTTYPE_CREATECLASS = "0";// ������
	public static final String TEXTTYPE_JOINCLASS = "1";// �����
	public static final String TEXTTYPE_SENDNOTICE = "2";// ��֪ͨ
	public static final String TEXTTYPE_SENDHOMEWORK = "3";// ������ҵ
	public static final String TEXTTYPE_SEARCHNOTICE = "4";// �鿴֪ͨ
	public static final String TEXTTYPE_SEARCHHOMEWORK = "5";// �鿴��ҵ
	public static final String TEXTTYPE_QUITJOB = "6";// ��ְ
	public static final String TEXTTYPE_GETJOB = "7";// ���ΰ��
	public static final String TEXTTYPE_HELP = "?";// ����
	public static final String TEXTTYPE_HELP1 = "��";// ����
	

	public static Map<String, String> parseXML(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		System.out.println("parseXML");
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		System.out.println(inputStream.toString());
		// ��ȡ������
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// �õ�xml��Ԫ��
		Element root = document.getRootElement();
		// �õ���Ԫ�ص������ӽڵ�
		List<Element> elementList = root.elements();

		// ���������ӽڵ�
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// �ͷ���Դ
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
	 * ��չxstream��ʹ��֧��CDATA��
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// ������xml�ڵ��ת��������CDATA���
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
