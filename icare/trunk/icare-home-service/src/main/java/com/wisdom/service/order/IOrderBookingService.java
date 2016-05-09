package com.wisdom.service.order;

import com.wisdom.dao.entity.OrderBooking;

/**
 * 预约订单详情
 * Created by fusj on 16/5/8.
 */
public interface IOrderBookingService {

    /**
     * 根据订单编号获取
     * @param id
     * @return
     */
    OrderBooking getByOrderId(Integer id);
}
