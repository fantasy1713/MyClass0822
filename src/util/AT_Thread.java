package util;

import vo.AccessToken;

public class AT_Thread implements Runnable {

	// �������û�Ψһƾ֤    
    public static String appid = "";    
    // �������û�Ψһƾ֤��Կ    
    public static String appsecret = "";    
    public static AccessToken accessToken = null;    
    
    public void run() {    
        while (true) {    
            try {    
                accessToken = WeChatUtil.getAccessToken(appid, appsecret);    
                if (null != accessToken) {    
                   
                    // ����7000��    
                    Thread.sleep((accessToken.getExpires_in() - 200) * 1000);    
                } else {    
                    // ���access_tokenΪnull��60����ٻ�ȡ    
                    Thread.sleep(60 * 1000);    
                }    
            } catch (InterruptedException e) {    
                try {    
                    Thread.sleep(60 * 1000);    
                } catch (InterruptedException e1) {    
                       
                }    
                   
            }    
        }    
    }    

}
