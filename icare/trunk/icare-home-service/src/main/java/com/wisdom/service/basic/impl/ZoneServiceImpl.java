package com.wisdom.service.basic.impl;

import com.wisdom.cache.CommonCache;
import com.wisdom.constants.CacheNameConstant;
import com.wisdom.constants.CommonConstant;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.Zone;
import com.wisdom.dao.entity.ZoneExample;
import com.wisdom.dao.mapper.ZoneMapper;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.ZoneSelect;
import com.wisdom.entity.ZoneTree;
import com.wisdom.service.basic.IZoneService;
import com.wisdom.util.DateUtil;
import com.wisdom.util.Pinyin4jUtil;
import com.wisdom.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区域
 * Created by fusj on 16/3/13.
 */
@Service
@Transactional
public class ZoneServiceImpl implements IZoneService {

    @Autowired
    private ZoneMapper zoneMapper;

    @Autowired
    private CommonCache commonCache;

    /**
     * 分页数据
     * @param zone
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo list(Zone zone, PageInfo pageInfo) {
        zone.setIsDel(SysParamDetailConstant.IS_DEL_FALSE);

        ZoneExample example = getCondition(zone);

        if(null != pageInfo && null != pageInfo.getPageStart()) {
            example.setLimitClauseStart(pageInfo.getPageStart());
            example.setLimitClauseCount(pageInfo.getPageCount());
        }

        List<Zone> list = zoneMapper.selectByExample(example);
        int totalCount = zoneMapper.countByExample(example);

        pageInfo.setList(list);
        pageInfo.setTotalCount(totalCount);

        return pageInfo;
    }

    /**
     * 组装查询条件
     * @param zone
     * @return
     */
    private ZoneExample getCondition(Zone zone) {
        ZoneExample example = new ZoneExample();

        if(null != zone) {
            ZoneExample.Criteria criteria = example.createCriteria();

            if(StringUtil.isNotEmptyObject(zone.getParentId())) {
                criteria.andParentIdEqualTo(zone.getParentId());
            }

            if(StringUtil.isNotEmptyObject(zone.getIsDel())) {
                criteria.andIsDelEqualTo(zone.getIsDel());
            }
        }
        return example;
    }

    /**
     * 初始化行政区域树
     * @return
     */
    @Override
    public List<ZoneTree> initData() {

        return iterator("0");
    }

    /**
     * 新增
     * @param zone
     */
    @CachePut(value = CacheNameConstant.ZONE_CACHE, key = "#zone.code")
    @Override
    public Zone add(Zone zone) {
        Zone parentZone = zoneMapper.selectByPrimaryKey(Integer.parseInt(zone.getParentId()));

        zone.setLevel((Integer.parseInt(parentZone.getLevel()) + 1) + "");
        zone.setSimplePinyin(Pinyin4jUtil.translate(zone.getName(), Pinyin4jUtil.RET_PINYIN_TYPE_HEADCHAR));
        zone.setIsDel(SysParamDetailConstant.IS_DEL_FALSE);
        zone.setCreateTime(DateUtil.getTimestamp());

        zoneMapper.insertSelective(zone);

        commonCache.evict(CommonConstant.ZONE_SELECT_CACHE_VALUE);

        return zone;
    }

    /**
     * 根据主键获取
     * @param zone
     * @return
     */
    @Override
    public Zone get(Zone zone) {
        zone = zoneMapper.selectByPrimaryKey(zone.getId());

        return zone;
    }

    /**
     * 修改保存
     * @param zone
     */
    @CachePut(value = CacheNameConstant.ZONE_CACHE, key = "#zone.code")
    @Override
    public Zone modify(Zone zone) {
        zone.setSimplePinyin(Pinyin4jUtil.translate(zone.getName(), Pinyin4jUtil.RET_PINYIN_TYPE_HEADCHAR));

        zoneMapper.updateByPrimaryKeySelective(zone);

        commonCache.evict(CommonConstant.ZONE_SELECT_CACHE_VALUE);

        return zoneMapper.selectByPrimaryKey(zone.getId());
    }

    /**
     * 删除
     * @param zone
     */
    @CacheEvict(value = CacheNameConstant.ZONE_CACHE, key = "#zone.code")
    @Override
    public void delete(Zone zone) {
        zone.setIsDel(SysParamDetailConstant.IS_DEL_TRUE);

        zoneMapper.updateByPrimaryKeySelective(zone);

        commonCache.evict(CommonConstant.ZONE_SELECT_CACHE_VALUE);
    }

    /**
     * 递归查询行政区域树
     * @param parentId
     * @return
     */
    private List<ZoneTree> iterator(String parentId) {
        ZoneExample example = new ZoneExample();
        example.createCriteria().andParentIdEqualTo(parentId)
                .andIsDelEqualTo(SysParamDetailConstant.IS_DEL_FALSE);
        List<Zone> parent1 = zoneMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(parent1)) {
            return null;
        }

        List<ZoneTree> treeList = new ArrayList<>();
        for(Zone zone : parent1) {
            ZoneTree tree = new ZoneTree();
            tree.setId(zone.getId() + "");
            tree.setText(zone.getName());
            tree.setLevel(zone.getLevel());
            tree.setCode(zone.getCode());
            tree.setSimplePinyin(zone.getSimplePinyin());

            if(StringUtil.isNotEmptyObject(zone.getId())) {
                tree.setNodes(iterator(zone.getId().toString()));
            }

            treeList.add(tree);
        }

        return treeList;
    }
}
