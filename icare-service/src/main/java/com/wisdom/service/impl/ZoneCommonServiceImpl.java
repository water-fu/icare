package com.wisdom.service.impl;

import com.wisdom.cache.CommonCache;
import com.wisdom.constants.CacheNameConstant;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.SysParam;
import com.wisdom.dao.entity.Zone;
import com.wisdom.dao.entity.ZoneExample;
import com.wisdom.dao.mapper.ZoneMapper;
import com.wisdom.entity.Select;
import com.wisdom.service.IZoneCommonService;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

        List<Select> list = iteratorSelect(zoneList.get(0).getId().toString());

        if(CollectionUtils.isEmpty(list)) {
            return "";
        }

        String result = JackonUtil.writeEntity2JSON(list);

        // 存入缓存，有效期一个礼拜
        commonCache.put(CommonConstant.ZONE_SELECT_CACHE_VALUE, result, CommonConstant.SESSION_TIME_OUT_WEEK);

        return result;
    }

    /**
     * 省下拉列表
     * @param proviceSelectPrefix
     * @param country
     * @return
     */
    @Cacheable(value = CacheNameConstant.ZONE_CACHE, key = "#proviceSelectPrefix.concat(#country)")
    @Override
    public List<Zone> provinceSelect(String proviceSelectPrefix, String country) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andCodeEqualTo(country)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

        List<Zone> list = zoneMapper.selectByExample(example);

        List<Zone> subList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)) {
            ZoneExample subExample = new ZoneExample();
            subExample.createCriteria().andParentIdEqualTo(list.get(0).getId() + "")
                    .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

            subList = zoneMapper.selectByExample(subExample);
        }

        return subList;
    }

    /**
     * 地市下拉列表
     * @param citySelectPrefix
     * @param provinceCode
     * @return
     */
    @Cacheable(value = CacheNameConstant.ZONE_CACHE, key = "#citySelectPrefix.concat(#provinceCode)")
    @Override
    public List<Zone> citySelect(String citySelectPrefix, String provinceCode) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andCodeEqualTo(provinceCode)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

        List<Zone> list = zoneMapper.selectByExample(example);

        List<Zone> subList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)) {
            ZoneExample subExample = new ZoneExample();
            subExample.createCriteria().andParentIdEqualTo(list.get(0).getId() + "")
                    .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

            subList = zoneMapper.selectByExample(subExample);
        }

        return subList;
    }

    /**
     * 县区下拉列表
     * @param countrySelectPrefix
     * @param cityCode
     * @return
     */
    @Cacheable(value = CacheNameConstant.ZONE_CACHE, key = "#countrySelectPrefix.concat(#cityCode)")
    @Override
    public List<Zone> countrySelect(String countrySelectPrefix, String cityCode) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andCodeEqualTo(cityCode)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

        List<Zone> list = zoneMapper.selectByExample(example);

        List<Zone> subList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(list)) {
            ZoneExample subExample = new ZoneExample();
            subExample.createCriteria().andParentIdEqualTo(list.get(0).getId() + "")
                    .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

            subList = zoneMapper.selectByExample(subExample);
        }

        return subList;
    }

    /**
     * 查询地市
     * @param code
     * @return
     */
    @Cacheable(value = CacheNameConstant.ZONE_CACHE, key = "#zonePrefix.concat(#code)")
    @Override
    public Zone getZoneByCode(String zonePrefix, String code) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andCodeEqualTo(code)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

        List<Zone> list = zoneMapper.selectByExample(example);

        if(CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return new Zone();
        }
    }

    /**
     * 递归查询行政区域下拉列表
     * @param parentId
     * @return
     */
    private List<Select> iteratorSelect(String parentId) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andParentIdEqualTo(parentId)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);

        List<Zone> parent1 = zoneMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(parent1)) {
            return null;
        }

        List<Select> selectList = new ArrayList<>();
        for(Zone zone : parent1) {
            Select zoneSelect = new Select();
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
