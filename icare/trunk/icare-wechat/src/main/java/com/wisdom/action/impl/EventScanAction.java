package com.wisdom.action.impl;

import com.wisdom.action.BaseAction;
import com.wisdom.constant.EventTypeConstants;
import com.wisdom.constant.MessageTypeConstant;
import com.wisdom.model.req.REQ_EventScan;
import com.wisdom.service.IUserInfoService;
import com.wisdom.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 二维码扫描事件处理
 * Created by fusj on 15/12/24.
 */
@Service(MessageTypeConstant.MESSAGE_EVNET + "_" + EventTypeConstants.EVENT_SCAN)
public class EventScanAction extends BaseAction<REQ_EventScan> {

    private static Logger logger = LoggerFactory.getLogger(EventScanAction.class);

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    protected String doAction(REQ_EventScan request) throws Exception {
        String message = ResponseUtil.initText(request.getFromUserName(), request.getToUserName(), request.getTicket());

        return message;
    }
}
