package com.wisdom.model.resp;

import com.wechat.model.base.RESP_Base;
import com.wechat.model.resp.sub.RESP_Music;

/**
 * 响应音乐
 * Created by fusj on 15/12/24.
 */
public class RESP_MessageMusic extends RESP_Base {
    /**
     * 音乐
     */
    private RESP_Music music;

    public RESP_Music getMusic() {
        return music;
    }

    public void setMusic(RESP_Music music) {
        this.music = music;
    }
}
