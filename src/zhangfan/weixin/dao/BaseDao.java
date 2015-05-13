package zhangfan.weixin.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseDao<T, PK extends Serializable> {
	public T get(PK id);//

	public boolean add(T object);//

	public void update(T object);//

	public void delete(PK id);//

	public List<T> findAll();//

}
