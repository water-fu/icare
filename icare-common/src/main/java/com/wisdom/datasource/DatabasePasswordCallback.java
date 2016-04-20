package com.wisdom.datasource;

import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.encrypt.EncryptFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.PasswordCallback;

/**
 * 加密数据库的密码，防止明文密码泄露.
 */
public class DatabasePasswordCallback extends PasswordCallback {

    private static final Logger logger = LoggerFactory.getLogger(DatabasePasswordCallback.class);

    private static final long serialVersionUID = 1L;

    public DatabasePasswordCallback(String prompt, boolean echoOn) {
        super(prompt, echoOn);
    }

    public DatabasePasswordCallback() {
        super("DatabasePasswordCallback", true);
    }

    @Override
    public void setPassword(char[] password) {
        String pwd = EncryptFactory.getInstance(SysParamDetailConstant.AES).decodePassword(new String(password), CommonConstant.DB_SALT);

        if(logger.isDebugEnabled()) {
            logger.debug("decode the text is : " + pwd);
        }

        super.setPassword(pwd.toCharArray());
    }

    private static void passwordEncode() {
        System.out.println(EncryptFactory.getInstance(SysParamDetailConstant.AES).encodePassword("root", CommonConstant.DB_SALT));
    }

    public static void main(String[] args) {
        passwordEncode();
    }
}
