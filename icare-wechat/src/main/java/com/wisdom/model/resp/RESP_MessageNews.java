package com.wisdom.model.resp;

import com.wechat.model.base.RESP_Base;
import com.wechat.model.resp.sub.RESP_Article;

import java.util.List;

/**
 * 响应图文消息
 * Created by fusj on 15/12/24.
 */
public class RESP_MessageNews extends RESP_Base {
    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     */
    private List<RESP_Article> Articles;

    public List<RESP_Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<RESP_Article> articles) {
        Articles = articles;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }
}