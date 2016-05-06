package com.wisdom.service.order;

import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.entity.OrderDateList;
import com.wisdom.entity.SessionDetail;

import java.util.List;

/**
 * 订单信息
 * Created by fusj on 16/4/26.
 */
public interface IOrderInfoService {
    /**
     * 订单预约
     * @param orderInfo
     * @param orderBooking
     * @param cityKey
     * @param fileList
     * @param sessionDetail
     */
    void fill(OrderInfo orderInfo, OrderBooking orderBooking, String cityKey,
              OrderDateList orderDateList, List<byte[]> fileList, SessionDetail sessionDetail);
}
