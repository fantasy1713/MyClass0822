package zhangfan.weixin.service;

import zhangfan.weixin.domain.MyClass;

public interface MyClassService {
	public boolean addMyClass(MyClass newClass);
	public MyClass getByCode(String Code);

}
