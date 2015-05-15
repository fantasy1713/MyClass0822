package test;

import org.junit.Test;

import util.WeChatUtil;

public class ATTest {

	@Test
	public void att(){
		String appid = "wx38fafa8832c60973";
		String appsecret="04197b22ca54f795987a29ddeabd97c4";
		WeChatUtil.getAccessToken(appid, appsecret);
	}
}
