package zhangfan.weixin.dao;

import org.springframework.transaction.annotation.Transactional;

import zhangfan.weixin.domain.MyClass;
@Transactional
public interface MyClassDao extends BaseDao<MyClass, Integer> {
	

}
