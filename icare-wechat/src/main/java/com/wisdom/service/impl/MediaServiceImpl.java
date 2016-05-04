package com.wisdom.service.impl;

import com.wisdom.annotation.Token;
import com.wisdom.cache.CommonCache;
import com.wisdom.cache.SessionCache;
import com.wisdom.constant.UrlConstant;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.AccessToken;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.IMediaService;
import com.wisdom.util.HttpClientUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 多媒体文件接口
 * Created by fusj on 16/3/31.
 */
@Service
@Transactional
public class MediaServiceImpl implements IMediaService {

    @Autowired
    private CommonCache commonCache;

    /**
     * 多媒体下载
     * @param mediaId 多媒体编号
     * @return
     */
    @Override
    @Token
    public byte[] getMediaFile(String mediaId) {
//        AccessToken accessToken = (AccessToken) commonCache.get(CommonConstant.ACCESS_TOKEN_VALUE);
//
//        String url = UrlConstant.MEDIA_DOWN_LOAD.replace("ACCESS_TOKEN", accessToken.getToken()).replace("MEDIA_ID", mediaId);
//
//        try {
//            HttpResponse httpResponse = HttpClientUtil.doDownStr(url);
//
//            return EntityUtils.toByteArray(httpResponse.getEntity());
//
//        } catch (Exception ex) {
//            throw new ApplicationException(ex.getMessage(), ex);
//        }
        return null;
    }
}
