package com.wisdom.service.impl;

import com.wisdom.annotation.Token;
import com.wisdom.cache.CommonCache;
import com.wisdom.config.AccessTokenSetting;
import com.wisdom.constant.UrlConstant;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.AccessToken;
import com.wisdom.po.UserInfo;
import com.wisdom.service.IUserInfoService;
import com.wisdom.util.HttpClientUtil;
import com.wisdom.util.JackonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户基本信息接口实现
 * Created by fusj on 15/12/22.
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private CommonCache commonCache;

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    @Token
    public UserInfo queryUserInfo(String openId) throws Exception {
        AccessToken accessToken = (AccessToken) commonCache.get(CommonConstant.ACCESS_TOKEN_VALUE);


        String url = UrlConstant.QUERY_USER_INFO.replace("ACCESS_TOKEN", accessToken.getToken()).replace("OPENID", openId);

        UserInfo userInfo = new UserInfo();
        JSONObject jsonObject = HttpClientUtil.doPostStr(url, "");

        if(jsonObject!=null){
            userInfo = JackonUtil.readJson2Entity(jsonObject.toString(), UserInfo.class);
        }

        return userInfo;
    }
}
