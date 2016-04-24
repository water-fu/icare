package com.wisdom.service;


/**
 * 行政区域公共查询service
 * Created by fusj on 16/4/21.
 */
public interface IZoneCommonService {

    /**
     * 查询行政区域下拉列表
     * @param parentId
     * @return
     */
    String zoneSelect(String parentId) throws Exception;
}
