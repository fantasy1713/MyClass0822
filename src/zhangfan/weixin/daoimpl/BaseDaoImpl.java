package zhangfan.weixin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import java.lang.reflect.ParameterizedType;

import zhangfan.weixin.dao.BaseDao;

public class BaseDaoImpl<T,PK extends Serializable> implements BaseDao<T, PK> {

	private Class<T> theClass;
	
	private SessionFactory sessionFactory;
	
	public BaseDaoImpl()
	{
		theClass =(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			
	}
	
	@Override
	public T get(PK id) {
		Session sess = sessionFactory.getCurrentSession();
		T result = (T)sess.get(theClass, id);
		return null;
	}

	@Override
	public boolean add(T object) {
		System.out.println("before try add");
		try{
			System.out.println("add in dao");
		 Session sess = sessionFactory.getCurrentSession();
		 sess.save(object);
		 System.out.println("try add over!");
		 return true;
		}
		catch(Exception e)
		{
			System.out.println("exception occur!");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void update(T object) {
		Session sess = sessionFactory.getCurrentSession();
		//Transaction tx = sess.beginTransaction();
		sess.update(object);
		
	}

	@Override
	public void delete(PK id) {
		Session sess = sessionFactory.getCurrentSession();
		//Transaction tx = sess.beginTransaction();
		T object = (T) sess.get(theClass, id);
		sess.delete(object);
		
	}

	@Override
	public List<T> findAll() {
		Session sess = sessionFactory.getCurrentSession();
		//Transaction tx = sess.beginTransaction();
		System.out.println("from " + theClass.getSimpleName());
		Query q = sess.createQuery("from " + theClass.getName());
		//tx.commit();
		return q.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

}
