package com.wisdom.po;

/**
 * 公众号二维码
 * Created by fusj on 15/12/22.
 */
public class QrCode {
    /**
     * 获取二维码票据
     */
    private String ticket;

    /**
     * 过期时间
     */
    private String expire_seconds;

    /**
     * 二维码地址
     */
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
