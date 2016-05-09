package com.wisdom.service.order;

import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.SessionDetail;

/**
 * 订单信息
 * Created by fusj on 16/5/8.
 */
public interface IOrderInfoService {
    /**
     * 列表信息
     * @param orderInfo
     * @param pageInfo
     * @return
     */
    PageInfo list(OrderInfo orderInfo, PageInfo pageInfo);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    OrderInfo get(Integer id);

    /**
     * 客服审核
     * @param orderInfo
     * @param auditType
     * @param auditMsg
     */
    void approve(OrderInfo orderInfo, String auditType, String auditMsg, SessionDetail sessionDetail);
}
