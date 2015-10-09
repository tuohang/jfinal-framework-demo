package com.tuohang.framework.jfinal.demo.group;

import com.jfinal.plugin.activerecord.Page;
import com.tuohang.framework.jfinal.service.IBaseService;

public interface IGroupService extends IBaseService<Group>{
	public boolean save(Group group);
	
	public Page<Group> findByPage(int pageNo,int pageSize);
}
