package zhangfan.weixin.dao;

import org.springframework.transaction.annotation.Transactional;

import zhangfan.weixin.domain.StuRole;
@Transactional
public interface StuRoleDao extends BaseDao<StuRole,Integer> {

}
