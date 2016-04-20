package com.wisdom.util;

import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.encrypt.EncryptFactory;

import java.util.Arrays;

public class WeChatUtil {

	public static boolean checkSignature(String signature,String timestamp,String nonce, String token){
		String[] arr = new String[]{token,timestamp,nonce};
		//排序
		Arrays.sort(arr);

		//生成字符串
		StringBuffer content = new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}

		//sha1加密
		String temp = EncryptFactory.getInstance(SysParamDetailConstant.SHA1).encodePassword(content.toString(), "");

		return temp.equals(signature);
	}

}
