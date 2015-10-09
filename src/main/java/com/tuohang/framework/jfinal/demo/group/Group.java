package com.tuohang.framework.jfinal.demo.group;

import com.jfinal.plugin.activerecord.Page;
import com.tuohang.framework.jfinal.ext.plugin.tablebind.TableBind;
import com.tuohang.framework.jfinal.model.ModelPlus;

@TableBind("t_group")
public class Group extends ModelPlus<Group> {

	private static final long serialVersionUID = 8515881125124957591L;
	
	public Page<Group> findByPage(int pageNo,int pageSize){
		return paginate(pageNo, pageSize, "select *", "from t_group order by id name desc");
	}
	
	public void test(){
		
	}

}
