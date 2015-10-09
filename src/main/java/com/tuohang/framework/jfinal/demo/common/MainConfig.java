package com.tuohang.framework.jfinal.demo.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;
import com.tuohang.framework.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.tuohang.framework.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.tuohang.framework.jfinal.ext.route.AutoBindRoutes;

/**
 * 全局统一配置文件，API引导式配置
 * 
 * @author Lims
 * @date 2015年9月14日
 * @version 1.0
 */
public class MainConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// 设置Jfinal运行在开发模式下，且视图默认为JSP
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
//		me.add("/weixin", WeiXinController.class);
//		me.add("/user", UserController.class);
		me.add(new AdminRoutes());
		me.add(new FrontRoutes());
		me.add(new AutoBindRoutes().autoScan(false));
	}

	@Override
	public void configPlugin(Plugins me) {
		// 集成druid
		loadPropertyFile("jdbc.properties");
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password"));
		druidPlugin.setFilters(getProperty("filters"));
		druidPlugin.setInitialSize(getPropertyToInt("initialSize"));
		druidPlugin.setMaxActive(getPropertyToInt("maxActive"));
		druidPlugin.setMaxWait(getPropertyToInt("maxWait"));
		druidPlugin
				.setTimeBetweenEvictionRunsMillis(getPropertyToLong("timeBetweenEvictionRunsMillis"));
		druidPlugin
				.setMinEvictableIdleTimeMillis(getPropertyToLong("minEvictableIdleTimeMillis"));
		druidPlugin.setValidationQuery(getProperty("validationQuery"));
		druidPlugin.setTestWhileIdle(getPropertyToBoolean("testWhileIdle"));
		druidPlugin.setTestOnBorrow(getPropertyToBoolean("testOnBorrow"));
		druidPlugin.setTestOnReturn(getPropertyToBoolean("testOnReturn"));
		druidPlugin
				.setMaxPoolPreparedStatementPerConnectionSize(getPropertyToInt("maxPoolPreparedStatementPerConnectionSize"));
		me.add(druidPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin("mysql", druidPlugin);
		me.add(arp);

		// 配置数据库方言，默认为mysql
		// arp.setDialect(new MysqlDialect());

		// 关键配置（数据库表名到model的映射）
		// arp.addMapping("t_user", User.class);

		// 配置@TableBind插件
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(druidPlugin,
				SimpleNameStyles.LOWER);
		atbp.autoScan(false);
		atbp.setShowSql(true);
		atbp.setDialect(new MysqlDialect());// 配置MySql方言
		me.add(atbp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// Handler可以接管所有web请求，并对应用具有完全控制权
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
		me.add(dvh);
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}
}
