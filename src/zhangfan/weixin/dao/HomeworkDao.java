package zhangfan.weixin.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import zhangfan.weixin.domain.Homework;
@Transactional
public interface HomeworkDao extends BaseDao<Homework, Integer> {
	
	public Homework findLastHomework(String ClassCode);//
	
	public List<Homework> findLase3Homework(String ClassCode);

}
