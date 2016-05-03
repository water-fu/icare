package com.wisdom.model.req;


import com.wisdom.model.base.REQ_BaseEvent;

/**
 * 关注事件
 * Created by fusj on 15/12/24.
 */
public class REQ_EventSubscribe extends REQ_BaseEvent {

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
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
