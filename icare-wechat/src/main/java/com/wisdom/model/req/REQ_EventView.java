package com.wisdom.model.req;


import com.wisdom.model.base.REQ_BaseEvent;

/**
 * click自定义菜单
 * Created by fusj on 15/12/24.
 */
public class REQ_EventView extends REQ_BaseEvent {

    /**
     * 事件KEY值，设置的跳转URL
     */
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
