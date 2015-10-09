package com.tuohang.framework.jfinal.demo.group;

import com.jfinal.plugin.activerecord.Page;
import com.tuohang.framework.jfinal.service.BaseService;

public class GroupService extends BaseService<Group> implements IGroupService {

	public static Group dao = new Group();

	@Override
	public boolean save(Group group) {
		return group.save();
	}

	@Override
	public Page<Group> findByPage(int pageNo, int pageSize) {
		return dao.findByPage(pageNo, pageSize);
	}
}
