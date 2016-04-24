package com.wisdom.aspect;

import com.wisdom.annotation.Token;
import com.wisdom.cache.CommonCache;
import com.wisdom.cache.SessionCache;
import com.wisdom.config.AccessTokenSetting;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.AccessToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 校验Token切面
 * Created by fusj on 15/12/23.
 */
@Aspect
@Component
public class TokenAspect {

    private static Logger logger = LoggerFactory.getLogger(TokenAspect.class);

    @Autowired
    private AccessTokenSetting accessTokenSetting;

    @Autowired
    private CommonCache commonCache;

    private String synchronize = "synchronize";

    /**
     * 解析是否需要判断Token令牌过期校验
     *
     * @param
     * @throws
     */
    @Before("execution(public * com.wisdom.service.impl..*Impl.*(..))")
    public void before(JoinPoint point) throws Throwable {

        Method targetMethod = null;

        Method[] methods = point.getTarget().getClass().getDeclaredMethods();
        for(Method method : methods) {
            if(point.getSignature().getName().equals(method.getName())) {
                targetMethod = method;
                break;
            }
        }

        if(null == targetMethod) {
            return;
        }

        Token token = targetMethod.getAnnotation(Token.class);

        if(null != token && token.tokenCheck()) {
            Object obj = commonCache.get(CommonConstant.ACCESS_TOKEN_VALUE);
            // 不需要刷新access_token
            if(null != obj) {
                AccessToken accessToken = (AccessToken) obj;

                long currentTime = System.currentTimeMillis();
                long loadTime = accessToken.getLoadTime();

                if((currentTime - loadTime) / 1000 <= accessToken.getExpiresIn() - 15 * 60) {
                    return;
                }

                Object isLoading = commonCache.get(CommonConstant.ACCESS_TOKEN_IS_LOADING);
                if(null != isLoading) {
                    return;
                }

                // 刷新access_token是同步的
                synchronized (synchronize) {
                    isLoading = commonCache.get(CommonConstant.ACCESS_TOKEN_IS_LOADING);
                    if(null != isLoading) {
                        return;
                    }

                    commonCache.put(CommonConstant.ACCESS_TOKEN_IS_LOADING, true, 5 * 60);

                    accessToken = (AccessToken) commonCache.get(CommonConstant.ACCESS_TOKEN_VALUE);

                    currentTime = System.currentTimeMillis();
                    loadTime = accessToken.getLoadTime();

                    // 过期前15分钟加载
                    if((currentTime - loadTime) / 1000 > accessToken.getExpiresIn() - 15 * 60) {
                        accessTokenSetting.initAccessToken();
                    }
                }
            }
            // 需要加载access_token
            else {
                synchronized (synchronize) {
                    obj = commonCache.get(CommonConstant.ACCESS_TOKEN_VALUE);
                    if(null != obj) {
                        return;
                    }

                    accessTokenSetting.initAccessToken();
                }
            }

        }
    }
}