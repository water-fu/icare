package com.wisdom.model.base;

/**
 * 事件消息
 * Created by fusj on 15/12/24.
 */
public class REQ_BaseEvent extends REQ_Base {
    /**
     * 事件类型
     */
    private String Event;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
