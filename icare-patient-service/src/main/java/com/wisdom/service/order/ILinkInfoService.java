package com.wisdom.service.order;

/**
 * 联系人
 * Created by fusj on 16/4/26.
 */
public interface ILinkInfoService {
    /**
     * 联系人下拉列表
     * @param link
     * @param accountId
     * @return
     */
    String select(String link, Integer accountId);
}
