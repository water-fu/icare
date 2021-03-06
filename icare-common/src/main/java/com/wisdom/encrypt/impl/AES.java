package com.wisdom.encrypt.impl;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AES {

	/**
	 * 加密
	 * 	PS. Linux与Windows对AES加密的密钥不一样，所有需要做处理
	 *
	 * @param param
	 * @param strKey
	 * @return
	 * @throws Exception
	 */
	public static String enAes(String param, String strKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//防止linux下 随机生成key  
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(strKey.getBytes());
		kgen.init(128, secureRandom);

		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器     

		byte[] byteContent = param.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化     
		byte[] result = cipher.doFinal(byteContent);

		return parseByte2HexStr(result);
	}

	/**
	 * 解密函数
	 *
	 * @return
	 */
	public static String deAes(String param, String strKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		//防止linux下 随机生成key  
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(strKey.getBytes());

		kgen.init(128, secureRandom);
		//kgen.init(128, new SecureRandom(password.getBytes()));     
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();

		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器     
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化     
		byte[] result = cipher.doFinal(parseHexStr2Byte(param));

		return new String(result, "UTF-8");
	}

	/**2进制转化成16进制 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**将16进制转换为二进制 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
