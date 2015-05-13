package zhangfan.weixin.dao;

import org.springframework.transaction.annotation.Transactional;

import zhangfan.weixin.domain.Student;
@Transactional
public interface StudentDao extends BaseDao<Student, Integer> {
	public Student getByCode(String code);
	
}
