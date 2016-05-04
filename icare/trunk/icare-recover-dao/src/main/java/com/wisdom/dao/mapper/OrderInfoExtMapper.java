package com.wisdom.dao.mapper;

import java.util.List;
import java.util.Map;

/**
 * 订单查询扩展MAPPER
 * Created by fusj on 16/5/2.
 */
public interface OrderInfoExtMapper {

    /**
     * 根据订单状态和康复编码统计
     * @param map
     * @return
     */
    int qryOrderNumByRecoverIdAndStatus(Map map);

    /**
     * 根据订单状态和康复编码查询列表数据
     * @param map
     * @return
     */
    List qryOrderListByRecoverIdAndStatus(Map map);
}
