package com.wisdom.action.impl;

import com.wisdom.action.BaseAction;
import com.wisdom.constant.EventTypeConstants;
import com.wisdom.constant.MessageTypeConstant;
import com.wisdom.model.req.REQ_EventSubscribe;
import com.wisdom.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 关注事件处理
 * Created by fusj on 15/12/24.
 */
@Service(MessageTypeConstant.MESSAGE_EVNET + "_" + EventTypeConstants.EVENT_SUBSCRIBE)
public class EventSubscribeAction extends BaseAction<REQ_EventSubscribe> {

    private static Logger logger = LoggerFactory.getLogger(EventSubscribeAction.class);

    @Override
    protected String doAction(REQ_EventSubscribe request) throws Exception {
        StringBuffer content = new StringBuffer();
        content.append("脑血管疾病康复就找e帮护！");
        content.append("\n\r");
        content.append("e帮护是一个提供专业居家康复服务的互联网平台，致力于汇聚专业的一线临床康复医生和康复师，利用他们的业余时间为用户上门提供医院级专业康复服务。");
        content.append("\n\r");
        content.append("现阶段服务项目1：脑血管疾病康复（脑梗、脑出血、中风、脑卒中）");
        content.append("\n\r");
        content.append("现阶段服务项目2：脑外伤康复");

        String message = ResponseUtil.initText(request.getFromUserName(), request.getToUserName(), content.toString());

       return message;
    }
}
