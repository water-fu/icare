package com.wisdom.service.impl;

import com.wisdom.cache.CommonCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.Zone;
import com.wisdom.dao.entity.ZoneExample;
import com.wisdom.dao.mapper.ZoneMapper;
import com.wisdom.entity.ZoneSelect;
import com.wisdom.service.IZoneCommonService;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区域公共查询方法
 * Created by fusj on 16/4/21.
 */
@Service
public class ZoneCommonServiceImpl implements IZoneCommonService {

    @Autowired
    private ZoneMapper zoneMapper;

    @Autowired
    private CommonCache commonCache;

    /**
     * 行政区域下拉列表
     * @param code
     * @return
     */
    @Override
    public String zoneSelect(String code) throws Exception {
        Object obj = commonCache.get(CommonConstant.ZONE_SELECT_CACHE_VALUE);
        if(null != obj) {
            return obj.toString();
        }

        ZoneExample example = new ZoneExample();
        example.createCriteria().andCodeEqualTo(code);

        List<Zone> zoneList = zoneMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(zoneList)) {
            return "";
        }

        List<ZoneSelect> list = iteratorSelect(zoneList.get(0).getId().toString());

        if(CollectionUtils.isEmpty(list)) {
            return "";
        }

        String result = JackonUtil.writeEntity2JSON(list);

        // 存入缓存，有效期一个礼拜
        commonCache.put(CommonConstant.ZONE_SELECT_CACHE_VALUE, result, CommonConstant.SESSION_TIME_OUT_WEEK);

        return result;
    }

    /**
     * 递归查询行政区域下拉列表
     * @param parentId
     * @return
     */
    private List<ZoneSelect> iteratorSelect(String parentId) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andParentIdEqualTo(parentId)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);
        List<Zone> parent1 = zoneMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(parent1)) {
            return null;
        }

        List<ZoneSelect> selectList = new ArrayList<>();
        for(Zone zone : parent1) {
            ZoneSelect zoneSelect = new ZoneSelect();
            zoneSelect.setValue(zone.getCode());
            zoneSelect.setText(zone.getName());

            if(StringUtil.isNotEmptyObject(zone.getId())) {
                zoneSelect.setChildren(iteratorSelect(zone.getId().toString()));
            }

            selectList.add(zoneSelect);
        }

        return selectList;
    }
}
