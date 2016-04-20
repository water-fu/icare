package com.wisdom.core.filter;

import com.wisdom.core.ActionConfig;
import com.wisdom.core.ApplicationConfig;
import com.wisdom.core.ErrorCodeDefine;
import com.wisdom.core.MappContext;
import com.wisdom.core.exception.BusinessException;
import com.wisdom.core.exception.DefaultExceptionCode;
import com.wisdom.core.model.IMappDatapackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCheckFilter implements IFilter<Map<String,Object>> {

	Logger logger = LoggerFactory.getLogger(UserCheckFilter.class);
	
	@Override
	public void init() {
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<String,Object> doFilter(Map<String,Object> context) throws Exception {
		
		IMappDatapackage request = (IMappDatapackage)context.get(MappContext.MAPPCONTEXT_REQUEST);
		
		ActionConfig config = ApplicationConfig.getActionConfig(request.getHeader().getBizCode());
		
		if(config == null)
			return context;
		
		if(config.isUserCheck() == false)
			return context;
		
		Object user = context.get(MappContext.MAPPCONTEXT_USER);
		if(user == null)
			throw new BusinessException(new DefaultExceptionCode(ErrorCodeDefine.NO_USER_INFO,"MAPP 用户信息为 null"));
		
//		logger.info("用户信息( UserName:"+user.getUserName()  +",UserId:"+user.getUserId()+" )");
		
		return context;
	}

}
