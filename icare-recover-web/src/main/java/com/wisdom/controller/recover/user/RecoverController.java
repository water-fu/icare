package com.wisdom.controller.recover.user;

import com.wisdom.annotation.Check;
import com.wisdom.cache.SessionCache;
import com.wisdom.config.SystemSetting;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.IZoneCommonService;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.util.CookieUtil;
import com.wisdom.dao.entity.Recover;
import com.wisdom.service.user.IRecoverService;
import com.wisdom.service.IMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 康复师
 * Created by fusj on 16/4/9.
 */
@Controller
@RequestMapping("/recover")
public class RecoverController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RecoverController.class);

    private static final String VM_ROOT_PATH = "recover/user/%s";

    @Autowired
    private IMediaService mediaDownload;

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private IRecoverService recoverService;

    @Autowired
    private IZoneCommonService zoneCommonService;

    @Autowired
    private SystemSetting systemSetting;

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 注册成功页面
     * @return
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success() {
        return String.format(VM_ROOT_PATH, "success");
    }

    /**
     * 康复师首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(Model model, HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
        SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

        // 统计新派订单、待评估订单、服务中订单数量
        Map<String, Integer> statMap = orderInfoService.statOrderNum(sessionDetail);
        model.addAttribute("stat", statMap);

        return new ModelAndView("recover/order/home");
    }

    /**
     * 康复师个人简况
     * @return
     */
    @RequestMapping(value = "personalInfo", method = RequestMethod.GET)
    public String personalInfo() {
        return String.format(VM_ROOT_PATH, "personalInfo");
    }

    /**
     * 康复师个人简介保存
     * @return
     */
    @RequestMapping(value = "personalInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean personalInfo(Recover recover, String[] server, HttpServletRequest request) {
        try {
            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            recoverService.personalInfo(recover, server, sessionDetail);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 认证页面(微信浏览器)
     * @return
     */
    @RequestMapping(value = "identification", method = RequestMethod.GET)
    @Check(phoneCheck = true)
    public String identification() {
        return String.format(VM_ROOT_PATH, "identification");
    }

    /**
     * 认证页面(非微信浏览器)
     * @return
     */
    @RequestMapping(value = "identification_o", method = RequestMethod.GET)
    @Check(phoneCheck = true)
    public String identification_o() {
        return String.format(VM_ROOT_PATH, "identification_o");
    }

    /**
     * 患者认证(微信浏览器)
     * @param recover
     * @param headImg
     * @param bodyImg
     * @param request
     * @return
     */
    @RequestMapping(value = "identification", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean identification(Recover recover, String headImg, String bodyImg, HttpServletRequest request) {
        try {

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            // 从微信后台获取图片
            byte[] head = mediaDownload.getMediaFile(headImg);
            byte[] body = mediaDownload.getMediaFile(bodyImg);

            recoverService.identification(recover, head, body, sessionDetail);

            // 更新session的账户状态
            sessionDetail.setStatus(SysParamDetailConstant.ACCOUNT_STATUS_AUTHEN);
            sessionCache.put(cookie.getValue(), sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 患者认证(非微信浏览器)
     * @param recover
     * @param headImg
     * @param bodyImg
     * @param request
     * @return
     */
    @RequestMapping(value = "identification_o", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean identification_o(Recover recover, String headImg, String bodyImg, HttpServletRequest request) {
        try {

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            recoverService.identification(recover, headImg, bodyImg, sessionDetail);

            // 更新session的账户状态
            sessionDetail.setStatus(SysParamDetailConstant.ACCOUNT_STATUS_AUTHEN);
            sessionCache.put(cookie.getValue(), sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 证件上传
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload() {
        return String.format(VM_ROOT_PATH, "upload");
    }

    /**
     * 证件上传
     * @param model
     * @param serverIds
     * @param request
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean upload(Model model, String[] serverIds, HttpServletRequest request) {
        try {
            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            List<byte[]> list = new ArrayList<>();
            for(String serverId : serverIds) {
                byte[] file = mediaDownload.getMediaFile(serverId);

                list.add(file);
            }

            recoverService.upload(list, sessionDetail);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 行政区域下拉列表
     * @return
     */
    @RequestMapping(value = "zoneSelect", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean zoneSelect() {
        try {

            String data = zoneCommonService.zoneSelect(systemSetting.getCountry());

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(data);

            return resultBean;

        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }
}
