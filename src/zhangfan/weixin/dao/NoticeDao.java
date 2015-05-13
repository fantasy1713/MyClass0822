package zhangfan.weixin.dao;

import org.springframework.transaction.annotation.Transactional;

import zhangfan.weixin.domain.Notice;
@Transactional
public interface NoticeDao extends BaseDao<Notice, Integer> {

}
