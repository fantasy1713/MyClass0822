package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import vo.AccessToken;

public class WeChatUtil {

	public static AccessToken getAccessToken(String appid, String appsecret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ appid + "&secret=" + appsecret;
		AccessToken at = new AccessToken();
		try {
			HttpsURLConnection con = (HttpsURLConnection) new URL(url)
					.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			InputStream is = con.getInputStream();
			int in;
			StringBuffer sb = new StringBuffer();

			while ((in = is.read()) != -1) {
				char c = (char) in;
				sb.append(c);
			}

			JSONObject json = JSONObject.fromObject(sb.toString());

			
			if (json.containsKey("access_token")) {
				at.setAccess_token(json.getString("access_token"));
				at.setExpires_in(json.getInt("expires_in"));
			} else if (json.containsKey("errcode")) {
				at.setErrcode(json.getString("errcode"));
				at.setErrmsg(json.getString("errmsg"));
			}
			System.out.print(sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return at;
	}

}
