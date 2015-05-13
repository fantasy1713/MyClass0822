package zhangfan.weixin.msg.response;

import java.util.List;

public class PicTextRespMsg extends BaseRespMsg {
	private int AriticlaCount;
	private int FuncFlag;
	private List<Article> Articles;

	public int getAriticlaCount() {
		return AriticlaCount;
	}

	public void setAriticlaCount(int ariticlaCount) {
		AriticlaCount = ariticlaCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}

}
