package com.wisdom.action.impl;

import com.wisdom.action.BaseAction;
import com.wisdom.constant.EventTypeConstants;
import com.wisdom.constant.MessageTypeConstant;
import com.wisdom.model.req.REQ_EventSubscribe;
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
       return "";
    }
}
