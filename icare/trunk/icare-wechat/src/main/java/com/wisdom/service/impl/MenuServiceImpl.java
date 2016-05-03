package com.wisdom.service.impl;

import com.wisdom.annotation.Token;
import com.wisdom.cache.CommonCache;
import com.wisdom.constant.UrlConstant;
import com.wisdom.constants.CommonConstant;
import com.wisdom.entity.AccessToken;
import com.wisdom.service.IMenuService;
import com.wisdom.util.HttpClientUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信菜单接口实现类
 * Created by fusj on 15/12/21.
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private CommonCache commonCache;

    /**
     * 获取Token
     * @return
     */
    private String getToken() {
        AccessToken accessToken = (AccessToken) commonCache.get(CommonConstant.ACCESS_TOKEN_VALUE);
        return accessToken.getToken();
    }

    /**
     * 查询菜单
     * @return
     */
    @Token
    public String queryMenu() throws Exception {
        String url = UrlConstant.QUERY_MENU_URL.replace("ACCESS_TOKEN", getToken());
        JSONObject jsonObject = HttpClientUtil.doGetStr(url);

        return jsonObject.toString();
    }

    /**
     * 创建菜单
     * @param menu
     * @return
     * @throws Exception
     */
    @Token
    public int createMenu(String menu) throws Exception {
        int result = 0;

        String url = UrlConstant.CREATE_MENU_URL.replace("ACCESS_TOKEN", getToken());

        JSONObject jsonObject = HttpClientUtil.doPostStr(url, menu);
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    /**
     * 删除菜单
     * @return
     * @throws Exception
     */
    @Token
    public int deleteMenu() throws Exception {
        String url = UrlConstant.DELETE_MENU_URL.replace("ACCESS_TOKEN", getToken());

        JSONObject jsonObject = HttpClientUtil.doGetStr(url);

        int result = 0;
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }

        return result;
    }
}
