package com.wisdom.controller.common.interceptor;

import com.wisdom.annotation.Check;
import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.ExceptionCodeConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.ResultExceptionCode;
import com.wisdom.entity.SessionDetail;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.SpringBeanUtil;
import com.wisdom.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * Created by fusj on 16/3/10.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        // 判断是否需要认证
        if (handler instanceof HandlerMethod) {
            if(logger.isDebugEnabled()) {
                logger.debug("请求地址:" + url);
            }

            HandlerMethod method = (HandlerMethod) handler;

            Check authen = method.getMethodAnnotation(Check.class);

            SessionCache sessionCache = (SessionCache) SpringBeanUtil.getSpringBean("sessionCache");
            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);

            // 1.空-要校验登陆 2.false-不需要校验 3.true-需要校验登陆

            // 登陆校验
            if(null == authen || authen.loginCheck()) {
                // 浏览器cookie过期
                if(null == cookie) {
                    return loginTimeOut(request, response, url);
                } else {
                    String key = cookie.getValue();

                    // redis的session过期
                    Object obj = sessionCache.get(key);
                    if(obj == null) {
                        return loginTimeOut(request, response, url);
                    } else {
                        // 不是该用户登陆的
                        SessionDetail sessionDetail = (SessionDetail) obj;
                        if(!SysParamDetailConstant.ACCOUNT_TYPE_PATIENT.equals(sessionDetail.getType())) {
                            return loginTimeOut(request, response, url);
                        }

                        // 重新设置cookie和redis的失效时间
                        CookieUtil.addCookie(response, CommonConstant.COOKIE_VALUE, cookie.getValue(), CommonConstant.SESSION_TIME_OUT_DAY);
                        sessionCache.put(key, obj, CommonConstant.SESSION_TIME_OUT_DAY);
                    }
                }
            }

            // 如果是空，只需要校验是否登陆，不需要校验手机号码和认证
            if(null == authen || !authen.loginCheck()) {
                return true;
            }

            // cookie的key和session
            String key = cookie.getValue();
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(key);

            StringBuffer sb = new StringBuffer();
            sb.append("http://").append(request.getServerName())
                    .append(":").append(request.getServerPort())
                    .append(request.getContextPath());

            // 手机号码校验
            if(authen.phoneCheck()) {
                if(!StringUtil.isNotEmptyObject(sessionDetail.getPhoneNo())) {
                    if(logger.isDebugEnabled()) {
                        logger.debug("未绑定手机号码,跳转到绑定页面");
                    }

                    // 根据账号类型跳转不同的绑定页面
                    sb.append("/user/bind");

                    response.sendRedirect(sb.toString());
                    return false;
                }
            }
            // 账号状态校验
            else if(authen.statusCheck()) {
                // 账号状态为新增
                if(sessionDetail.getStatus().equals(SysParamDetailConstant.ACCOUNT_STATUS_NEW)) {
                    if(logger.isDebugEnabled()) {
                        logger.debug("未实名认证,跳转到认证页面");
                    }

                    sb.append("/patient/identification");
                    response.sendRedirect(sb.toString());
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 登陆超时
     * @param response
     * @return
     * @throws Exception
     */
    private boolean loginTimeOut(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        if(logger.isDebugEnabled()) {
            logger.debug("用户未登录，跳转到登陆页面");
        }

        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equals("XMLHttpRequest")){

            response.setContentType("application/json");

            // 登陆超时CODE
            ResultExceptionCode code = new ResultExceptionCode();
            code.setCode(ExceptionCodeConstant.LOGIN_TIME_OUT);

            // 登陆超时返回的url
            StringBuffer sb = new StringBuffer();
            sb.append("http://").append(request.getServerName())
                    .append(":").append(request.getServerPort())
                    .append(request.getContextPath())
                    .append("/user/login");
            code.setUrl(sb.toString());

            // 登陆超时返回bean
            ResultBean resultBean = new ResultBean(false);
            resultBean.setData(code);

            response.getWriter().print(JackonUtil.writeEntity2JSON(resultBean));
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("http://").append(request.getServerName())
                    .append(":").append(request.getServerPort())
                    .append(request.getContextPath());
            sb.append("/user/login");

            response.sendRedirect(sb.toString());
        }

        return false;
    }
}
