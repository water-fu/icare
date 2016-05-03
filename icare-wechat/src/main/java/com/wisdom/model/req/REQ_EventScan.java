package com.wisdom.model.req;


import com.wisdom.model.base.REQ_BaseEvent;

/**
 * 关注后扫描事件
 * Created by fusj on 15/12/24.
 */
public class REQ_EventScan extends REQ_BaseEvent {

    /**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    private String EventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getEventKey() {

        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
