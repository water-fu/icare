package com.wisdom.config;

import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.AccessToken;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.IAccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * 获取AccessToken
 * Created by fusj on 15/12/21.
 */
@Repository
public class AccessTokenSetting {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenSetting.class);

    @Autowired
    private IAccessTokenService accessTokenService;

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private JsapiTicketSetting jsapiTicketSetting;

    /**
     * 项目启动初始化access_token
     * @throws Exception
     */
    @PostConstruct
    public void initAccessToken() throws Exception {
        Object obj = sessionCache.get(CommonConstant.ACCESS_TOKEN_VALUE);
        if(null != obj) {
            return;
        }

        logger.debug("正在加载Access_Token");

        AccessToken accessToken = accessTokenService.getAccessToken();
        // 把accessToken放到redis中
        sessionCache.put(CommonConstant.ACCESS_TOKEN_VALUE, accessToken, accessToken.getExpiresIn());

        // 初始化jsapi_ticket
        jsapiTicketSetting.initJsapiTicket();
        logger.debug("加载Access_Token结束");
    }
}
