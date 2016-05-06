package com.wisdom.controller.patient.user;

import com.wisdom.annotation.Check;
import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.dao.entity.Patient;
import com.wisdom.encrypt.EncryptFactory;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.IMediaService;
import com.wisdom.service.user.IPatientService;
import com.wisdom.util.CookieUtil;
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

/**
 * 患者业务处理
 * Created by fusj on 16/3/20.
 */
@Controller
@RequestMapping("/patient")
public class PatientController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private static final String VM_ROOT_PATH = "patient/user/%s";

    @Autowired
    private IMediaService mediaDownload;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private SessionCache sessionCache;

    /**
     * 注册成功页面
     * @return
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public ModelAndView success(Model model, HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
        SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

        // 推荐KEY
        String key = EncryptFactory.getInstance(SysParamDetailConstant.AES).encodePassword(sessionDetail.getAccountId()+"", CommonConstant.SALT);
        model.addAttribute("key", key);

        return new ModelAndView(String.format(VM_ROOT_PATH, "success"));
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
     * @param patient
     * @param headImg
     * @param bodyImg
     * @param request
     * @return
     */
    @RequestMapping(value = "identification", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean identification(Patient patient, String headImg, String bodyImg, HttpServletRequest request) {
        try {

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            // 从微信后台获取图片
            byte[] head = mediaDownload.getMediaFile(headImg);
            byte[] body = mediaDownload.getMediaFile(bodyImg);

            patientService.identification(patient, head, body, sessionDetail);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 患者认证(非微信浏览器)
     * @param patient
     * @param headImg
     * @param bodyImg
     * @param request
     * @return
     */
    @RequestMapping(value = "identification_o", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean identification_o(Patient patient, String headImg, String bodyImg, HttpServletRequest request) {
        try {

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            patientService.identification(patient, headImg, bodyImg, sessionDetail);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 患者首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return String.format(VM_ROOT_PATH, "index");
    }
}
