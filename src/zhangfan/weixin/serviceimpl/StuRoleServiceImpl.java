package zhangfan.weixin.serviceimpl;

import zhangfan.weixin.dao.StuRoleDao;
import zhangfan.weixin.service.StuRoleService;

public class StuRoleServiceImpl implements StuRoleService {

	private StuRoleDao StuRoleDao;

	public StuRoleDao getStuRoleDao() {
		return StuRoleDao;
	}

	public void setStuRoleDao(StuRoleDao stuRoleDao) {
		StuRoleDao = stuRoleDao;
	}
	
}
