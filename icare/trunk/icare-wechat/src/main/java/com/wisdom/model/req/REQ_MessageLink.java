package com.wisdom.model.req;


import com.wisdom.model.base.REQ_BaseMessage;

/**
 * 链接消息
 * Created by fusj on 15/12/24.
 */
public class REQ_MessageLink extends REQ_BaseMessage {
    /**
     * 链接标题
     */
    private String Title;

    /**
     * 链接描述
     */
    private String Description;

    /**
     * 链接地址
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
