package com.wisdom.service.basic;

import com.wisdom.dao.entity.Zone;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.ZoneSelect;
import com.wisdom.entity.ZoneTree;

import java.util.List;

/**
 * 行政区域
 * Created by fusj on 16/3/13.
 */
public interface IZoneService {
    /**
     * 分页数据
     * @param zone
     * @param pageInfo
     * @return
     */
    PageInfo list(Zone zone, PageInfo pageInfo);

    /**
     * 初始化行政区域树
     * @return
     */
    List<ZoneTree> initData();

    /**
     * 新增
     * @param zone
     */
    Zone add(Zone zone);

    /**
     * 根据主键获取
     * @param zone
     * @return
     */
    Zone get(Zone zone);

    /**
     * 修改保存
     * @param zone
     */
    Zone modify(Zone zone);

    /**
     * 删除
     * @param zone
     */
    void delete(Zone zone);
}
