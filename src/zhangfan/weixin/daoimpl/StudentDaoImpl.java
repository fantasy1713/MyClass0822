package zhangfan.weixin.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import zhangfan.weixin.dao.StudentDao;
import zhangfan.weixin.domain.Student;

public class StudentDaoImpl extends BaseDaoImpl<Student, Integer> implements StudentDao {

	@Override
	public Student getByCode(String code) {
		System.out.println("StudentDaoimpl");
		Student stu;
		try
		{

			Session sess = getSessionFactory().getCurrentSession();
			String hql="from Student as s where s.StudentCode =:code";
			Query query = sess.createQuery(hql);
			query.setString("code", code.trim());
			System.out.println("exe query");
			stu=(Student)query.uniqueResult();
			System.out.println("exe query finish");
			return stu;
		}
		catch(Exception e)
		{
			System.out.println("²éÑ¯³ö´í");
			e.printStackTrace();
			return null;
		}
		
	}

	

}
