package com.wisdom.controller.patient.system;

import com.wisdom.cache.SessionCache;
import com.wisdom.config.SystemSetting;
import com.wisdom.constants.CommonConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.IZoneCommonService;
import com.wisdom.service.order.ILinkInfoService;
import com.wisdom.service.user.IPatientService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 下拉列表生成
 * Created by fusj on 16/4/26.
 */
@Controller
@RequestMapping("/select")
public class SelectController extends BaseController {

    @Autowired
    private IZoneCommonService zoneCommonService;

    @Autowired
    private SystemSetting systemSetting;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private ILinkInfoService linkInfoService;

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

    /**
     * 患者下拉列表
     * @return
     */
    @RequestMapping(value = "patientSelect", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean patientSelect(HttpServletRequest request) {
        try {
            ResultBean resultBean = new ResultBean(true);

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            if(StringUtil.isNotEmptyObject(sessionDetail.getPatientSelect())) {
                resultBean.setData(sessionDetail.getPatientSelect());
            } else {
                String data = patientService.select("patient", sessionDetail.getAccountId());
                resultBean.setData(data);

                sessionDetail.setPatientSelect(data);
                sessionCache.put(cookie.getValue(), sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);
            }

            return resultBean;

        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 联系人列表
     * @return
     */
    @RequestMapping(value = "linkSelect", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean linkSelect(HttpServletRequest request) {
        try {
            ResultBean resultBean = new ResultBean(true);

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            if(StringUtil.isNotEmptyObject(sessionDetail.getLinkSelect())) {
                resultBean.setData(sessionDetail.getLinkSelect());
            } else {
                String data = linkInfoService.select("link", sessionDetail.getAccountId());
                resultBean.setData(data);

                sessionDetail.setLinkSelect(data);
                sessionCache.put(cookie.getValue(), sessionDetail, CommonConstant.SESSION_TIME_OUT_DAY);
            }

            return resultBean;

        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }
}
