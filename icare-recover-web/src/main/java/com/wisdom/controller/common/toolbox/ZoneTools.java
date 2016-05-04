package com.wisdom.controller.common.toolbox;

import com.wisdom.constants.CommonConstant;
import com.wisdom.dao.entity.Zone;
import com.wisdom.service.IZoneCommonService;
import com.wisdom.util.SpringBeanUtil;

/**
 * 地市页面工具
 * Created by fusj on 16/5/2.
 */
public class ZoneTools {

    /**
     * 地市名称翻译
     * @param code
     * @return
     */
    public static Zone getZoneByCode(String code) {
        try {

            IZoneCommonService iZoneCommonService = SpringBeanUtil.getSpringBeanByClassType(IZoneCommonService.class);

            Zone zone = iZoneCommonService.getZoneByCode(CommonConstant.ZONE_SINGLE_PREFIX, code);

            return zone;
        } catch (Exception ex) {
            return new Zone();
        }
    }
}
