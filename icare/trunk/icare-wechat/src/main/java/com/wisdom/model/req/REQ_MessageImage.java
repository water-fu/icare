package com.wisdom.model.req;


import com.wisdom.model.base.REQ_BaseMessage;

/**
 * 图片消息
 * Created by fusj on 15/12/24.
 */
public class REQ_MessageImage extends REQ_BaseMessage {

    /**
     * 图片地址
     */
    private String  PicUrl;

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
