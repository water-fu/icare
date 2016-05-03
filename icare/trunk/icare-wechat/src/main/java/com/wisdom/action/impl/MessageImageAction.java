package com.wisdom.action.impl;

import com.wisdom.action.BaseAction;
import com.wisdom.constant.MessageTypeConstant;
import com.wisdom.model.req.REQ_MessageImage;
import com.wisdom.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 图片消息处理
 * Created by fusj on 15/12/23.
 */
@Service(MessageTypeConstant.MESSAGE_IMAGE)
public class MessageImageAction extends BaseAction<REQ_MessageImage> {

    private static Logger logger = LoggerFactory.getLogger(MessageImageAction.class);

    @Override
    protected String doAction(REQ_MessageImage request) throws Exception {


        String message = ResponseUtil.initText(request.getFromUserName(), request.getToUserName(), request.getPicUrl());
        return message;
    }
}
