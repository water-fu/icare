package com.wisdom.po;

/**
 * 用户基本信息
 * Created by fusj on 15/12/22.
 */
public class UserInfo {

    /**
     * 是否订阅该公众号,0表示未关注，取不到信息
     */
    private int subscribe;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别，1：男性，2：女性，0：未知
     */
    private int sex;

    /**
     * 终端语言环境，简体zh_CN
     */
    private String language;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像地址
     */
    private String headimgurl;

    /**
     * 用户关注的时间戳
     */
    private String subscribe_time;

    /**
     *
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;

    /**
     * 用户所在的分组ID
     */
    private String groupid;

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
