package com.wisdom.action.impl;

import com.wisdom.action.BaseAction;
import com.wisdom.constant.EventTypeConstants;
import com.wisdom.constant.MessageTypeConstant;
import com.wisdom.model.req.REQ_EventClick;
import com.wisdom.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 自定义click菜单处理
 * Created by fusj on 15/12/24.
 */
@Service(MessageTypeConstant.MESSAGE_EVNET + "_" + EventTypeConstants.EVENT_CLICK)
public class EventClickAction extends BaseAction<REQ_EventClick> {

    private static Logger logger = LoggerFactory.getLogger(EventClickAction.class);

    @Override
    protected String doAction(REQ_EventClick request) throws Exception {
        String content = "该功能正在开发中,请稍后";
        String message = ResponseUtil.initText(request.getFromUserName(), request.getToUserName(), content);

        return message;
    }
}
