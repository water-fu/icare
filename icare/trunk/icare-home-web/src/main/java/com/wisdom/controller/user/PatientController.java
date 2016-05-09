package com.wisdom.controller.user;

import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.dao.entity.Account;
import com.wisdom.dao.entity.Patient;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.entity.jackon.RewardList;
import com.wisdom.service.IFileService;
import com.wisdom.service.user.IAccountService;
import com.wisdom.service.user.IPatientService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.JackonUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者实名认证信息
 * Created by fusj on 16/3/14.
 */
@Controller
@RequestMapping("patient")
public class PatientController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private IAccountService accountService;

    /**
     * 根据accountId获取患者实名信息
     * @param patient
     * @return
     */
    @RequestMapping(value = "getAuditByAccountId", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean getAuditByAccountId(Patient patient) {
        try {

            patient = patientService.getAuditByAccountId(patient);

            if(StringUtil.isNotEmptyObject(patient.getIdPath())) {
                String idPath = fileService.getHttpUrl(patient.getIdPath());
                patient.setIdPath(idPath);
            }

            if(StringUtil.isNotEmptyObject(patient.getBodyPath())) {
                String bodyPath = fileService.getHttpUrl(patient.getBodyPath());
                patient.setBodyPath(bodyPath);
            }

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(patient);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 患者审核
     * @param patient
     * @param auditType  审核类型 1:通过  0:不通过
     * @param auditMsg   审核意见
     * @return
     */
    @RequestMapping(value = "patientAudit", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean patientAudit(Patient patient, String auditType, String auditMsg, HttpServletRequest request) {
        try {
            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            patientService.audit(patient, auditType, auditMsg, sessionDetail);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 获取账户推荐信息
     * @param account
     * @return
     */
    @RequestMapping(value = "getRewardId", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean getRewardId(Account account) {
        try {
            List<Map<String, String>> result = new ArrayList<>();

            account = accountService.get(account);
            if(null != account && StringUtil.isNotEmptyObject(account.getRewardId())) {
                RewardList rewardList = JackonUtil.readJson2Entity(account.getRewardId(), RewardList.class);

                for (RewardList.Reward reward : rewardList.getList()) {
                    Account pAccount = accountService.get(reward.getRewardId());

                    Map<String, String> map = new HashMap<>();
                    map.put("name", pAccount.getName());
                    map.put("phoneNo", pAccount.getPhoneNo());

                    result.add(map);
                }
            }

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(result);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }
}
