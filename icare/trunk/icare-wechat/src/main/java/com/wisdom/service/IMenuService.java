package com.wisdom.service;

/**
 * 微信菜单接口
 * Created by fusj on 15/12/21.
 */
public interface IMenuService {

    /**
     * 查询菜单
     * @return
     */
    String queryMenu() throws Exception;

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    int createMenu(String menu) throws Exception;

    /**
     * 删除菜单
     * @return
     */
    int deleteMenu() throws Exception;
}
