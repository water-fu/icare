package com.wisdom.service.basic;

import com.wisdom.dao.entity.NursingType;
import com.wisdom.entity.PageInfo;

/**
 * 护理类型
 * Created by fusj on 16/3/6.
 */
public interface INursingTypeService {

    /**
     * 护理大类列表页
     * @param nursingType
     * @param pageInfo
     * @return
     */
    PageInfo list(NursingType nursingType, PageInfo pageInfo);

    /**
     * 护理大类新增
     * @param nursingType
     */
    void add(NursingType nursingType);

    /**
     * 根据主键获取对象
     * @param id
     */
    NursingType get(Integer id);

    /**
     * 保存修改
     * @param nursingType
     */
    void modify(NursingType nursingType);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);
}
