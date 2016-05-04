package com.wisdom.model.resp;


import com.wisdom.model.base.RESP_Base;

/**
 * 响应文本消息
 * Created by fusj on 15/12/24.
 */
public class RESP_MessageText extends RESP_Base {
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
