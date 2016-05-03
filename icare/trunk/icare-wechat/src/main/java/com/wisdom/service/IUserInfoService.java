package com.wisdom.service;


import com.wisdom.po.UserInfo;

/**
 * 用户基本信息接口
 * Created by fusj on 15/12/22.
 */
public interface IUserInfoService {
    /**
     * 根据openId查询用户信息
     * @param openId
     * @return
     */
    UserInfo queryUserInfo(String openId) throws Exception;
}
