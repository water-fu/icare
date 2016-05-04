package com.wisdom.controller.common.toolbox;

import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.SessionDetail;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.SpringBeanUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 获取用户登陆信息工具
 * Created by fusj on 16/5/2.
 */
public class SessionDetailTools {

    /**
     * 根据key获取sessionDetail
     * @param request
     * @return
     */
    public static SessionDetail getSessionDetail(HttpServletRequest request) {
        try {
            SessionCache sessionCache = SpringBeanUtil.getSpringBeanByClassType(SessionCache.class);
            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            return (SessionDetail) sessionCache.get(cookie.getValue());
        } catch (Exception ex) {
            return new SessionDetail();
        }
    }
}
