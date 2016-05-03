package com.wisdom.action.impl;

import com.wisdom.action.BaseAction;
import com.wisdom.constant.EventTypeConstants;
import com.wisdom.constant.MessageTypeConstant;
import com.wisdom.model.req.REQ_EventView;
import com.wisdom.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 自定义view菜单处理
 * Created by fusj on 15/12/24.
 */
@Service(MessageTypeConstant.MESSAGE_EVNET + "_" + EventTypeConstants.EVNET_VIEW)
public class EventViewAction extends BaseAction<REQ_EventView> {

    private static Logger logger = LoggerFactory.getLogger(EventViewAction.class);

    @Override
    protected String doAction(REQ_EventView request) throws Exception {
        String message = ResponseUtil.initText(request.getFromUserName(), request.getToUserName(), request.getEventKey());

        return message;
    }
}
