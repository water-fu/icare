package com.wisdom.model.base;

/**
 * 微信响应对象
 * Created by fusj on 15/12/23.
 */
public class RESP_Base {

    /**
     * 来源用户
     */
    private String FromUserName;

    /**
     * 公众号
     */
    private String ToUserName;

    /**
     * 创建时间
     */
    private Long CreateTime;

    /**
     * 消息类型
     */
    private String MsgType;

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
