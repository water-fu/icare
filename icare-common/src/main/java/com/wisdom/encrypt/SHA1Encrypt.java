package com.wisdom.encrypt;

import com.wisdom.encrypt.impl.SHA1;
import com.wisdom.exception.ApplicationException;

/**
 * SHA1加密
 * Created by fusj on 16/4/17.
 */
public class SHA1Encrypt implements IEncrypt {

    /**
     * 加密
     */
    @Override
    public String encodePassword(String rawPass, Object salt) {
        try {
            return SHA1.sha1(rawPass);
        } catch (Exception e) {
            throw new ApplicationException("参数加密异常", e);
        }
    }

    /**
     *
     */
    @Deprecated
    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return false;
    }

    /**
     * 解密
     */
    @Override
    public String decodePassword(String rawPass, Object salt) {
        return null;
    }
}
