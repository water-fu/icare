package com.wisdom.service;


import com.wisdom.dao.entity.Zone;

import java.util.List;

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

    /**
     * 省下拉列表
     * @param proviceSelectPrefix
     * @param country
     * @return
     */
    List<Zone> provinceSelect(String proviceSelectPrefix, String country);

    /**
     * 地市下拉列表
     * @param citySelectPrefix
     * @param provinceCode
     * @return
     */
    List<Zone> citySelect(String citySelectPrefix, String provinceCode);

    /**
     * 区县下拉列表
     * @param countrySelectPrefix
     * @param cityCode
     * @return
     */
    List<Zone> countrySelect(String countrySelectPrefix, String cityCode);

    /**
     *
     * 查询地市
     * @param code
     * @return
     */
    Zone getZoneByCode(String zonePrefix, String code);
}
