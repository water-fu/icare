package com.wisdom.service.order;

import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.SessionDetail;

import java.util.List;
import java.util.Map;

/**
 * 康复管家订单
 * Created by fusj on 16/4/30.
 */
public interface IOrderInfoService {
    /**
     * 统计新派订单、待评估订单、服务中订单数量
     * @param sessionDetail
     * @return
     */
    Map<String, Integer> statOrderNum(SessionDetail sessionDetail);

    /**
     * 获取订单列表页
     * @param orderInfo
     * @param sessionDetail
     * @return
     */
    List getOrderListByStatus(OrderInfo orderInfo, PageInfo pageInfo, SessionDetail sessionDetail);

    /**
     * 获取订单信息
     * @param id
     * @return
     */
    OrderInfo getOrderInfoByOrderId(Integer id);

    /**
     * 获取预约订单信息
     * @param id
     * @return
     */
    OrderBooking getOrderBookingByOrderId(Integer id);
}
