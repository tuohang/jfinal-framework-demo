package com.tuohang.framework.jfinal.demo.base;

import com.jfinal.plugin.activerecord.Page;
import com.tuohang.framework.jfinal.controller.plus.ControllerKitPlusExt;
import com.tuohang.framework.jfinal.model.ModelPlus;
import com.tuohang.framework.jfinal.model.query.Paging;
import com.tuohang.framework.jfinal.model.query.Sort;
import com.tuohang.framework.jfinal.service.IBaseService;
import com.tuohang.framework.jfinal.uicomponent.datatables.DataTablesPackUtil;

/**
 * BaseController
 * 
 * @author Lims
 * @date 2015年10月7日
 * @version 1.0
 * @param <M>
 *            model 继承了modelPlus的对象
 * @param <S>
 *            service 继承了IBaseService的接口
 */
public class BaseController<M extends ModelPlus<M>, S extends IBaseService<M>>
		extends ControllerKitPlusExt<M, S> {

	/**
	 * 获取paging分页查询对象（默认，此处封装为dataTables参数格式）
	 * 
	 * @return Paging
	 */
	protected Paging getPaging() {
		// 默认从0开始
		int pageStart = getParaToInt(ParamKey.PAGE_START, 0);
		// 默认为10
		int pageSize = getParaToInt(ParamKey.PAGE_SIZE, 10);
		// 获取页码
		int pageNumber = (pageStart / pageSize) + 1;
		Paging paging = new Paging(pageNumber, pageSize);
		return paging;
	}

	/**
	 * 获取Sort排序查询对象（默认）
	 * 
	 * @return Sort
	 */
	protected Sort getSort() {
		return getSort(ParamKey.ORDER_FIELD, ParamKey.ORDER_DIRECTION);
	}

	/**
	 * 分页查询
	 */
	public void findByPage() {
		String sEcho = getPara(ParamKey.PAGE_SECHO, "1");
		Page<M> page = service.findByPage(getPaging(), getSort());
		renderJson(DataTablesPackUtil.pack(page, sEcho));
	}

}
