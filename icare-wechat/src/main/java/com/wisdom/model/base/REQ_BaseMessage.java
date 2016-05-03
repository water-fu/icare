package com.wisdom.model.base;

/**
 * 普通消息
 * Created by fusj on 15/12/24.
 */
public class REQ_BaseMessage extends REQ_Base {
    /**
     * 消息ID
     */
    private String MsgId;

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
