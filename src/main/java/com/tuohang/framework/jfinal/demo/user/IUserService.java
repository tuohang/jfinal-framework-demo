package com.tuohang.framework.jfinal.demo.user;

import com.tuohang.framework.jfinal.service.IBaseService;
import com.tuohang.framework.jfinal.service.annotation.ServiceBind;

@ServiceBind(UserService.class)
public interface IUserService extends IBaseService<User>{
	
}
