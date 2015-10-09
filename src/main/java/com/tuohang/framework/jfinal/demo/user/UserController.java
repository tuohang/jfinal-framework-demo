package com.tuohang.framework.jfinal.demo.user;

import java.util.Date;
import java.util.List;

import com.jfinal.core.ActionKey;
import com.jfinal.plugin.activerecord.Page;
import com.tuohang.framework.jfinal.demo.base.BaseController;
import com.tuohang.framework.jfinal.ext.route.ControllerBind;
import com.tuohang.framework.jfinal.kits.UUIDGenerator;
import com.tuohang.framework.jfinal.model.query.Paging;
import com.tuohang.framework.jfinal.model.query.QueryCondition;
import com.tuohang.framework.jfinal.model.query.QueryType;
import com.tuohang.framework.jfinal.model.query.Sort;

@ControllerBind("/user")
public class UserController extends BaseController<User, IUserService> {

	public void save4() {
		model.set("username", "tuohang");
		model.set("password", "1");
		model.set("nickname", "拓航");
		model.set("age", 20);
		model.set("sex", 1);
		model.set("birth", new Date());
		boolean result = service.save(model);
		if (result)
			renderJson(model.get(idFieldName));
		else
			renderJson("添加失败！");
	}

	public void save2() {
		model.set("username", "tuohangxx");
		model.set("password", "1");
		model.set("nickname", "拓航xx");
		model.set("age", 20);
		model.set("birth", new Date());
		boolean resultFlag = service.save(model);

		if (resultFlag)
			renderJson("-----save2------" + model.get(idFieldName));
		else
			renderJson("添加失败！");
	}

	public void save3() {
		instanceModel();
		boolean result = service.save(model);
		if (result)
			renderJson("-----save3------" + model.get(idFieldName));
		else
			renderJson("添加失败！");
	}

	@Override
	protected QueryCondition getQueryCondition() {
		QueryCondition condition = super.getQueryCondition();
		// model.set(idFieldName, "7c1ffe5021924a6e83da6a1322616556");
		condition.setFiledQuery(QueryType.NOT_EQUAL, idFieldName);
		condition.setValueQuery(QueryType.GREATER_EQUAL, "birth", "2010-10-04"); // 开始时间
		condition.setValueQuery(QueryType.LESS_EQUAL, "birth",
				"2015-10-06 11:25:19"); // 结束时间
		condition.modelToCondition(model);
		return condition;
	}
	
	@ActionKey("/user/test")
	public void test() {
		QueryCondition condition = getQueryCondition();
		Paging paging = getPaging();
		Sort sort = getSort();
		List<User> list = service.findByParam(condition, paging, sort);
		System.out.println(modelClass.getSimpleName());
		renderJson(list);
	}

	protected QueryCondition getCondition2() {
		instanceModel();
		model.set(idFieldName, UUIDGenerator.getUUID());
		model.set("username", "tuohangxx");
		model.set("password", "1");
		model.set("nickname", "拓航xx");
		model.set("age", 20);
		model.set("birth", new Date());

		QueryCondition conditon = new QueryCondition();
		conditon.setFiledQuery(QueryType.FUZZY, "username", "nickname"); // 字段name、field2按照模糊查询
		conditon.setFiledQuery(QueryType.NOT_EQUAL, "id"); // 字段filed3按照不等于查询

		Date startdate = new Date();
		Date enddate = new Date();
		// 针对字段field进行时间范围查询
		conditon.setValueQuery(QueryType.GREATER_EQUAL, "birth", startdate); // 开始时间
		conditon.setValueQuery(QueryType.LESS_EQUAL, "birth", enddate); // 结束时间

		conditon.modelToCondition(model);
		return conditon;
	}

	public void findByIPage() {
		// http://localhost/jfinal-shop/user/findByIPage?orderBy=id&pageNo=1&pageSize=15&direc=desc
		Page<User> page = service.findByPage(getPaging("pageNo", "pageSize"),
				getSort("orderBy", "direc"));
		renderJson(page);
	}
}
