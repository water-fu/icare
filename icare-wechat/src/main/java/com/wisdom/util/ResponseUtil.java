package com.wisdom.util;

import com.wisdom.constant.ResponseTypeContants;
import com.wisdom.model.resp.*;
import com.wisdom.model.resp.sub.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信响应消息工具类
 * Created by fusj on 15/12/24.
 */
public class ResponseUtil {

    private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 组装文本消息
     * @param toUserName     用户
     * @param fromUserName   公众号
     * @param content
     * @return
     */
    public static String initText(String toUserName, String fromUserName, String content) {
        RESP_MessageText text = new RESP_MessageText();
        text.setFromUserName(fromUserName);
        text.setToUserName(toUserName);
        text.setMsgType(ResponseTypeContants.RESPONSE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);

        Map<String, Class> alias = new HashMap<String, Class>();
        alias.put("xml", RESP_MessageText.class);

        String resp = XStreamUtil.stringToXML(text, alias);
        logger.info(String.format("响应结果[%s]", resp));

        return resp;
    }

    /**
     * 组装图片消息
     * @param toUserName     用户
     * @param fromUserName   公众号
     * @param mediaId
     * @return
     */
    public static String initImage(String toUserName, String fromUserName, String mediaId) {
        RESP_MessageImage messageImage = new RESP_MessageImage();
        messageImage.setToUserName(toUserName);
        messageImage.setFromUserName(fromUserName);
        messageImage.setMsgType(ResponseTypeContants.RESPONSE_IMAGE);
        messageImage.setCreateTime(new Date().getTime());

        RESP_Image image = new RESP_Image();
        image.setMediaId(mediaId);

        messageImage.setImage(image);

        Map<String, Class> alias = new HashMap<String, Class>();
        alias.put("xml", RESP_MessageImage.class);
        alias.put("Image", RESP_Image.class);

        String resp = XStreamUtil.stringToXML(messageImage, alias);
        logger.info(String.format("响应结果[%s]", resp));

        return resp;
    }

    /**
     * 组装语音消息
     * @param toUserName     用户
     * @param fromUserName   公众号
     * @param mediaId
     * @return
     */
    public static String initVoice(String toUserName, String fromUserName, String mediaId) {
        RESP_MessageVoice messageVoice = new RESP_MessageVoice();
        messageVoice.setToUserName(toUserName);
        messageVoice.setFromUserName(fromUserName);
        messageVoice.setMsgType(ResponseTypeContants.RESPONSE_VOICE);
        messageVoice.setCreateTime(new Date().getTime());

        RESP_Voice voice = new RESP_Voice();
        voice.setMediaId(mediaId);

        messageVoice.setVoice(voice);

        Map<String, Class> alias = new HashMap<String, Class>();
        alias.put("xml", RESP_MessageVoice.class);
        alias.put("Image", RESP_Voice.class);

        String resp = XStreamUtil.stringToXML(messageVoice, alias);
        logger.info(String.format("响应结果[%s]", resp));

        return resp;
    }

    /**
     * 组装视频消息
     * @param toUserName     用户
     * @param fromUserName   公众号
     * @param video
     * @return
     */
    public static String initVideo(String toUserName, String fromUserName, RESP_Video video) {
        RESP_MessageVideo messageVideo = new RESP_MessageVideo();
        messageVideo.setToUserName(toUserName);
        messageVideo.setFromUserName(fromUserName);
        messageVideo.setMsgType(ResponseTypeContants.RESPONSE_VIDEO);
        messageVideo.setCreateTime(new Date().getTime());
        messageVideo.setVideo(video);

        Map<String, Class> alias = new HashMap<String, Class>();
        alias.put("xml", RESP_MessageVideo.class);
        alias.put("Image", RESP_Video.class);

        String resp = XStreamUtil.stringToXML(messageVideo, alias);
        logger.info(String.format("响应结果[%s]", resp));

        return resp;
    }

    /**
     * 组装音乐消息
     * @param toUserName     用户
     * @param fromUserName   公众号
     * @param music
     * @return
     */
    public static String initMusic(String toUserName, String fromUserName, RESP_Music music) {
        RESP_MessageMusic messageMusic = new RESP_MessageMusic();
        messageMusic.setToUserName(toUserName);
        messageMusic.setFromUserName(fromUserName);
        messageMusic.setMsgType(ResponseTypeContants.RESPONSE_MUSIC);
        messageMusic.setCreateTime(new Date().getTime());
        messageMusic.setMusic(music);

        Map<String, Class> alias = new HashMap<String, Class>();
        alias.put("xml", RESP_MessageMusic.class);
        alias.put("Image", RESP_Music.class);

        String resp = XStreamUtil.stringToXML(messageMusic, alias);
        logger.info(String.format("响应结果[%s]", resp));

        return resp;
    }

    /**
     * 组装图文消息
     * @param toUserName    用户
     * @param fromUserName  公众号
     * @param news
     * @return
     */
    public static String initNews(String toUserName, String fromUserName, List<RESP_Article> news) {
        RESP_MessageNews messageNews = new RESP_MessageNews();
        messageNews.setToUserName(toUserName);
        messageNews.setFromUserName(fromUserName);
        messageNews.setMsgType(ResponseTypeContants.RESPONSE_NEWS);
        messageNews.setCreateTime(new Date().getTime());
        messageNews.setArticleCount(news.size());
        messageNews.setArticles(news);

        Map<String, Class> alias = new HashMap<String, Class>();
        alias.put("xml", RESP_MessageNews.class);
        alias.put("item", RESP_Article.class);

        String resp = XStreamUtil.stringToXML(messageNews, alias);
        logger.info(String.format("响应结果[%s]", resp));

        return resp;
    }
}
