package com.wisdom.encrypt;

import com.wisdom.exception.ApplicationException;

/**
 * 加密工厂
 * @author
 *
 */
public class EncryptFactory {

	/**
	 * 根据摘要算法字符串返回加密类
	 * 支持MD5算法
	 * @param encryptName
	 * @return
	 * @throws
	 */
	public static IEncrypt getInstance(String encryptName) throws ApplicationException {
		IEncrypt iEncrypt = null;
		try {
			iEncrypt = (IEncrypt) Class.forName("com.wisdom.encrypt."+encryptName).newInstance();
		} catch (Exception e) {
			throw new ApplicationException("com.wisdom.encrypt."+encryptName+"类初始化异常", e);
		}

		return iEncrypt;
	}
}
