package zhangfan.weixin.serviceimpl;

import zhangfan.weixin.domain.MyClass;
import zhangfan.weixin.dao.MyClassDao;
import zhangfan.weixin.service.MyClassService;

public class MyClassServiceImpl implements MyClassService {
	private MyClassDao MyClassDao;

	@Override
	public boolean addMyClass(MyClass newClass) {
		System.out.println("addmyclass");
		System.out.println(newClass.getClassCode());
		boolean flag  = MyClassDao.add(newClass);
		System.out.println(flag);
		return flag;
	}

	@Override
	public MyClass getByCode(String Code) {
		// TODO 自动生成的方法存根
		return null;
	}

	public MyClassDao getMyClassDao() {
		return MyClassDao;
	}

	public void setMyClassDao(MyClassDao myClassDao) {
		MyClassDao = myClassDao;
	}

}
