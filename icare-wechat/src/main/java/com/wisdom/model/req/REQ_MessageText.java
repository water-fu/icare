package com.wisdom.model.req;


import com.wisdom.model.base.REQ_BaseMessage;

/**
 * 请求,文本消息
 * Created by fusj on 15/12/23.
 */
public class REQ_MessageText extends REQ_BaseMessage {
    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
