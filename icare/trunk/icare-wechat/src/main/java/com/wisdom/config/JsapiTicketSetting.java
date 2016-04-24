package com.wisdom.config;

import com.wisdom.cache.CommonCache;
import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.JsapiTicket;
import com.wisdom.service.IJsapiTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * jsapi_ticket票据申请
 * Created by fusj on 16/3/17.
 */
@Repository
public class JsapiTicketSetting {

    @Autowired
    private IJsapiTicketService jsapiTicketService;

    @Autowired
    private CommonCache commonCache;

    public void initJsapiTicket() throws Exception {
        JsapiTicket jsapiTicket = jsapiTicketService.getJsapiTicket();

        // jsapi_ticket
        commonCache.put(CommonConstant.JSAPI_TICKET_VALUE, jsapiTicket, jsapiTicket.getExpiresIn());
    }
}
