package com.wisdom.controller.patient.user;

import com.wisdom.annotation.Check;
import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.dao.entity.Account;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.IIdentifyCodeService;
import com.wisdom.service.user.IAccountService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 前端用户注册、登陆、认证类
 * Created by fusj on 16/3/17.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String VM_ROOT_PATH = "patient/user/%s";

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private IIdentifyCodeService identifyCodeService;

    @Autowired
    private IAccountService accountService;

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @Check(loginCheck = false)
    public ModelAndView register() {

        return new ModelAndView(String.format(VM_ROOT_PATH, "register"));
    }

    /**
     * 注册
     * @param account
     * @param code
     * @param key      注册推荐KEY
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    @Check(loginCheck = false)
    public ResultBean register(Account account, String code, @RequestParam(value = "key", required = false) String key, HttpServletRequest request, HttpServletResponse response) {
        try {

            // 校验验证码是否正确
            Boolean flag = identifyCodeService.validIdentifyCode(account.getPhoneNo(), code, SysParamDetailConstant.IDENTIFY_TYPE_REGISTER);
            if(!flag) {
                throw new ApplicationException("验证码错误");
            }

            account.setType(SysParamDetailConstant.ACCOUNT_TYPE_PATIENT);

            // 保存account表
            account = accountService.register(account, key);

            // session缓存失败，不影响用户注册
            try {
                // session到redis的对象
                SessionDetail sessionDetail = new SessionDetail();
                sessionDetail.setAccountId(account.getId());
                sessionDetail.setPhoneNo(account.getPhoneNo());
                sessionDetail.setType(account.getType());
                sessionDetail.setStatus(account.getStatus());
                sessionDetail.setFrom(SysParamDetailConstant.LOGIN_FROM_SYSTEM);

                // 把redis的key存入cookie，有效期1天
                Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
                String value;
                if(cookie != null) {
                    value = cookie.getValue();
                } else {
                    value = UUID.randomUUID().toString();
                }
                CookieUtil.addCookie(response, CommonConstant.COOKIE_VALUE, value, CommonConstant.SESSION_TIME_OUT_DAY);

                // 把用户登陆信息存入redis
                sessionCache.put(value, sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);

            } catch (Exception ex) {
                logger.error("缓存redis异常:" + ex.getMessage(), ex);
            }

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(transType(account.getType()));

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }


    /**
     * 校验手机号码
     * @return
     */
    @RequestMapping(value = "validPhoneNo", method = RequestMethod.POST)
    @ResponseBody
    @Check(loginCheck = false)
    public List validPhoneNo(String fieldId, String fieldValue) {
        List result = new ArrayList();
        result.add(fieldId);

        try {
            Account account = new Account();
            account.setPhoneNo(fieldValue);

            List<Account> list = accountService.list(account);
            if(CollectionUtils.isEmpty(list)) {
                result.add(true);
            } else {
                result.add(false);
            }
        } catch (Exception ex) {
            result.add(false);
        }

        return result;
    }

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Check(loginCheck = false)
    public ModelAndView login(Model model, @RequestParam(value = "errorMsg", required = false) String errorMsg) {
        if(StringUtil.isNotEmptyObject(errorMsg)) {
            ResultBean resultBean = new ResultBean(false);
            resultBean.setMsg(errorMsg);

            model.addAttribute("resultBean", resultBean);
        }

        return new ModelAndView(String.format(VM_ROOT_PATH, "login"));
    }

    /**
     * 登陆
     * @param account
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    @Check(loginCheck = false)
    public ResultBean login(Account account, HttpServletRequest request, HttpServletResponse response) {
        try {
            account.setType(SysParamDetailConstant.ACCOUNT_TYPE_PATIENT);

            account = accountService.login(account);

            // session缓存失败，不影响用户注册
            try {
                // session到redis的对象
                SessionDetail sessionDetail = new SessionDetail();
                sessionDetail.setAccountId(account.getId());
                sessionDetail.setPhoneNo(account.getPhoneNo());
                sessionDetail.setType(account.getType());
                sessionDetail.setStatus(account.getStatus());
                sessionDetail.setFrom(SysParamDetailConstant.LOGIN_FROM_SYSTEM);

                // 把redis的key存入cookie，有效期1天
                Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
                String value;
                if(cookie != null) {
                    value = cookie.getValue();
                } else {
                    value = UUID.randomUUID().toString();
                }
                CookieUtil.addCookie(response, CommonConstant.COOKIE_VALUE, value, CommonConstant.SESSION_TIME_OUT_DAY);

                // 把用户登陆信息存入redis
                sessionCache.put(value, sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);

            } catch (Exception ex) {
                logger.error("缓存redis异常:" + ex.getMessage(), ex);
            }

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(transType(account.getType()));

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 忘记密码页面
     * @return
     */
    @RequestMapping(value = "forget", method = RequestMethod.GET)
    @Check(loginCheck = false)
    public String forget() {
        return String.format(VM_ROOT_PATH, "forget");
    }

    /**
     * 忘记密码
     * @param account
     * @return
     */
    @RequestMapping(value = "forget", method = RequestMethod.POST)
    @ResponseBody
    @Check(loginCheck = false)
    public ResultBean forget(Account account, String code) {
        try {
            // 校验验证码是否正确
            Boolean flag = identifyCodeService.validIdentifyCode(account.getPhoneNo(), code, SysParamDetailConstant.IDENTIFY_TYPE_FOTGET);
            if(!flag) {
                throw new ApplicationException("验证码错误");
            }

            // 修改account表
            account = accountService.forget(account);

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(transType(account.getType()));

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 账号绑定
     * @return
     */
    @RequestMapping(value = "/bind")
    public ModelAndView bind() {

        return new ModelAndView(String.format(VM_ROOT_PATH, "bind"));
    }

    /**
     * 账号绑定
     * @return
     */
    @RequestMapping(value = "bind", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean bind(Account account, String code, HttpServletRequest request, HttpServletResponse response) {
        try {

            // 校验验证码是否正确
            Boolean flag = identifyCodeService.validIdentifyCode(account.getPhoneNo(), code, SysParamDetailConstant.IDENTIFY_TYPE_BIND);
            if(!flag) {
                throw new ApplicationException("验证码错误");
            }

            account.setType(SysParamDetailConstant.ACCOUNT_TYPE_PATIENT);

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            account = accountService.bind(account, sessionDetail);

            // 更新session内容
            sessionDetail.setAccountId(account.getId());
            sessionDetail.setPhoneNo(account.getPhoneNo());
            sessionDetail.setStatus(account.getStatus());

            sessionCache.put(cookie.getValue(), sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(transType(account.getType()));

            return resultBean;

        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }
}
