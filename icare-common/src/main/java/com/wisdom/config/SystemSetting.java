package com.wisdom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * 系统参数
 * Created by fusj on 16/4/7.
 */
@Repository
public class SystemSetting {

    /**
     * 是否生产环境
     */
    @Value("#{propertiesReader['system.productionMode']}")
    private Boolean productionMode;

    /**
     * 系统适用省份
     */
    @Value("#{propertiesReader['system.country']}")
    private String country;

    /**
     * 微信中间请求地址
     */
    @Value("#{propertiesReader['system.wechatMiddle']}")
    private String wechatMiddleUrl;

    public Boolean getProductionMode() {
        return productionMode;
    }

    public void setProductionMode(Boolean productionMode) {
        this.productionMode = productionMode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWechatMiddleUrl() {
        return wechatMiddleUrl;
    }

    public void setWechatMiddleUrl(String wechatMiddleUrl) {
        this.wechatMiddleUrl = wechatMiddleUrl;
    }
}
