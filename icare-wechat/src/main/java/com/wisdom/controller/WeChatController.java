package com.wisdom.controller;

import com.wisdom.action.IAction;
import com.wisdom.annotation.Check;
import com.wisdom.config.WeChatSetting;
import com.wisdom.entity.ResultBean;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.IJsapiTicketService;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.SAXReaderUtil;
import com.wisdom.util.SpringBeanUtil;
import com.wisdom.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 微信首页处理器
 * Created by fusj on 16/3/17.
 */
@Controller
@RequestMapping("weChat")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WeChatSetting weChatSetting;

    @Autowired
    private IJsapiTicketService jsapiTicketService;

    /**
     * 微信公众号GET请求
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @Check(loginCheck = false)
    public String doGet(HttpServletRequest request) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if(WeChatUtil.checkSignature(signature, timestamp, nonce, weChatSetting.getToken())){
            return echostr;
        }

        return "";
    }

    /**
     * 微信公众号POST请求
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    @Check(loginCheck = false)
    public String doPost(HttpServletRequest request) throws Exception {
        try {
            request.setCharacterEncoding("UTF-8");

            // 解析请求的内容
            Map<String, String> map = SAXReaderUtil.xmlToMap(request);

            logger.info(String.format("请求参数:[%s]", JackonUtil.writeEntity2JSON(map)));

            // 消息类型
            String msgType = map.get("MsgType");
            // 事件类型
            String eventType = map.get("Event");

            // 业务action的Bean名称
            StringBuffer actionBean = new StringBuffer(msgType);

            if(!StringUtils.isEmpty(eventType)) {
                actionBean.append("_").append(eventType);
            }

            IAction messageAction = null;
            try {
                messageAction = (IAction) SpringBeanUtil.getSpringBean(actionBean.toString());
            } catch (Exception ex) {
                logger.error("Spring Bean Error:" + ex.getMessage());
            }
            if(null == messageAction) {
                return "success";
            }

            String message = messageAction.execute(map);

            return message;
        } catch (Exception ex) {
            logger.error("微信公众号请求失败:" + ex.getMessage(), ex);
            return "success";
        }
    }

    /**
     * 获取微信config时的参数
     * @return
     */
    @RequestMapping(value = "getWeChatConfigParam", method = RequestMethod.GET)
    @ResponseBody
    @Check(loginCheck = false)
    public ResultBean getWeChatConfigParam(String url) {
        try {
            Map map = jsapiTicketService.sign(url);
            map.put("appId", weChatSetting.getAppId());

            // 需要获取接口权限
            List<String> jsApiList = new ArrayList<>();
            jsApiList.add("onMenuShareTimeline");
            jsApiList.add("hideOptionMenu");
            jsApiList.add("showMenuItems");
            jsApiList.add("chooseImage");
            jsApiList.add("uploadImage");

            map.put("jsApiList", jsApiList);

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(map);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * ajax请求异常返回
     * @param ex
     * @return
     */
    protected ResultBean ajaxException(Exception ex) {
        logger.error(ex.getMessage(), ex);

        ResultBean resultBean = new ResultBean(false);

        if(ex instanceof ApplicationException) {
            resultBean.setMsg(ex.getMessage());
        } else {
            resultBean.setMsg("系统出错了");
        }

        return resultBean;
    }
}
