package com.wisdom.entity;

import java.io.Serializable;

/**
 * 微信公众号AccessToken
 * Created by fusj on 15/12/21.
 */
public class AccessToken implements Serializable {

    /**
     * 获取到的凭证
     */
    private String token;

    /**
     * 凭证有效时间
     */
    private int expiresIn;

    /**
     * 加载时间
     */
    private long loadTime;

    /**
     * 正在加载
     */
    private boolean isLoading;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }
}
