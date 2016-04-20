package com.wisdom.service;

import com.wisdom.entity.SessionDetail;

/**
 * 验证码
 * Created by fusj on 16/3/16.
 */
public interface IIdentifyCodeService {

    /**
     * 发送验证码
     * @param phoneNo
     * @param type
     */
    String sendIdentifyCode(String phoneNo, String type, String ip, SessionDetail sessionDetail);

    /**
     * 校验验证码是否正确
     * @param phoneNo
     * @param code
     * @param type
     * @return
     */
    Boolean validIdentifyCode(String phoneNo, String code, String type);
}
