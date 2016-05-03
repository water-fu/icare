package com.wisdom.model.resp;

import com.wechat.model.base.RESP_Base;
import com.wechat.model.resp.sub.RESP_Voice;

/**
 * 响应语音消息
 * Created by fusj on 15/12/24.
 */
public class RESP_MessageVoice extends RESP_Base {

    /**
     * 语音
     */
    private RESP_Voice voice;

    public RESP_Voice getVoice() {
        return voice;
    }

    public void setVoice(RESP_Voice voice) {
        this.voice = voice;
    }
}
