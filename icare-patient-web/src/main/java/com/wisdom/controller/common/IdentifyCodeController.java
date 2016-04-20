package com.wisdom.controller.common;

import com.wisdom.annotation.Check;
import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.IIdentifyCodeService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证码
 * Created by fusj on 16/3/16.
 */
@Controller
@RequestMapping("identifyCode")
public class IdentifyCodeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IdentifyCodeController.class);

    @Autowired
    private IIdentifyCodeService identifyCodeService;

    @Autowired
    private SessionCache sessionCache;

    /**
     * 发送验证码
     * @param phoneNo
     * @return
     */
    @RequestMapping(value = "sendCode", method = RequestMethod.POST)
    @ResponseBody
    @Check(loginCheck = false)
    public ResultBean sendIdentifyCode(String phoneNo, String type, HttpServletRequest request) {
        try {
            if(!StringUtil.isNotEmptyObject(phoneNo)) {
                throw new ApplicationException("请填写手机号码");
            }

            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }

            String code = identifyCodeService.sendIdentifyCode(phoneNo, type, ip, null);
            // 发送短信 phoneNo+code

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 账号绑定发送验证码，需要校验是否登陆，登陆后才能进行绑定
     * @param phoneNo
     * @return
     */
    @RequestMapping(value = "bindSendCode", method = RequestMethod.POST)
    @ResponseBody
    @Check(loginCheck = true)
    public ResultBean bindSendIdentifyCode(String phoneNo, String type, HttpServletRequest request) {
        try {
            if(!StringUtil.isNotEmptyObject(phoneNo)) {
                throw new ApplicationException("请填写手机号码");
            }

            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            String code = identifyCodeService.sendIdentifyCode(phoneNo, type, ip, sessionDetail);
            // 发送短信 phoneNo+code

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 校验码验证
     * @return
     */
    @RequestMapping(value = "validIdentifyCode", method = RequestMethod.GET)
    @ResponseBody
    @Check(loginCheck = false)
    public List validIdentifyCode(String fieldId, String fieldValue, String phoneNo) {
        List result = new ArrayList();
        result.add(fieldId);

        try {

            // 校验验证码是否正确
            Boolean flag = identifyCodeService.validIdentifyCode(phoneNo, fieldValue, SysParamDetailConstant.IDENTIFY_TYPE_REGISTER);
            if(!flag) {
                throw new ApplicationException("验证码错误");
            }

            result.add(true);

        } catch (Exception ex) {
            result.add(false);
        }

        return result;
    }
}
