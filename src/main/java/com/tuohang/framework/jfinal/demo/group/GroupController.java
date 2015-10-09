package com.tuohang.framework.jfinal.demo.group;

import com.jfinal.core.Controller;
import com.tuohang.framework.jfinal.ext.route.ControllerBind;

/**
 * group controller
 * 
 * @author Lims
 * @date 2015年10月9日
 * @version 1.0
 */
@ControllerBind("group")
public class GroupController extends Controller {

	IGroupService service;
	
	public GroupController() {
		service = new GroupService();
	}
	
	public void save(){
		Group group = getModel(Group.class);
		service.save(group);
	}
	
	public void findByPage(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNo = getParaToInt("pageNo", 1);
		renderJson(service.findByPage(pageNo, pageSize));
	}
	
}
