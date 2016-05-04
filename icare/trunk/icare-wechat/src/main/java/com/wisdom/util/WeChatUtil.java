package com.wisdom.util;

import com.wisdom.constant.ButtonKeyConstants;
import com.wisdom.constant.ButtonTypeConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.encrypt.EncryptFactory;
import com.wisdom.po.menu.Button;
import com.wisdom.po.menu.ClickButton;
import com.wisdom.po.menu.Menu;
import com.wisdom.po.menu.ViewButton;

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

	/**
	 * 微信菜单
	 * @return
	 * @throws Exception
	 */
	public static String initMenu() throws Exception {
		Menu menu = new Menu();

		// 菜单一
		ClickButton button1 = new ClickButton();
		button1.setName("康复预约");
		button1.setType(ButtonTypeConstant.BUTTON_CLICK);
		button1.setKey(ButtonKeyConstants.V001_BUTTON_BOOKING);


		// 菜单二
		Button button2 = new Button();
		button2.setName("幸运礼包");

		ClickButton clickButton21 = new ClickButton();
		clickButton21.setName("抽奖活动");
		clickButton21.setType(ButtonTypeConstant.BUTTON_CLICK);
		clickButton21.setKey(ButtonKeyConstants.V001_BUTTON_LOGGERY);

		button2.setSub_button(new Button[]{clickButton21});

		// 菜单三
		Button button3 = new Button();
		button3.setName("走进e帮护");

		ViewButton viewButton31 = new ViewButton();
		viewButton31.setName("微博");
		viewButton31.setType(ButtonTypeConstant.BUTTON_VIEW);
		viewButton31.setUrl("http://weibo.cn");

		ViewButton viewButton32 = new ViewButton();
		viewButton32.setName("了解我们");
		viewButton32.setType(ButtonTypeConstant.BUTTON_VIEW);
		viewButton32.setUrl("http://www.ebanghu.com");

		button3.setSub_button(new Button[] {viewButton31, viewButton32});

		menu.setButton(new Button[]{button1, button2, button3});

		return JackonUtil.writeEntity2JSON(menu);
	}
}
