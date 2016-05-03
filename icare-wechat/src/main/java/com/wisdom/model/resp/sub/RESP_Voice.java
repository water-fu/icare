package com.wisdom.model.resp.sub;

/**
 * 语音消息
 * Created by fusj on 15/12/24.
 */
public class RESP_Voice {
    /**
     * 通过素材管理接口上传多媒体文件，得到的id
     */
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
