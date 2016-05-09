package com.wisdom.service.order.impl;

import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderBookingExample;
import com.wisdom.dao.mapper.OrderBookingMapper;
import com.wisdom.service.order.IOrderBookingService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 预约订单详情
 * Created by fusj on 16/5/8.
 */
@Service
@Transactional
public class OrderBookingServiceImpl implements IOrderBookingService {

    @Autowired
    private OrderBookingMapper orderBookingMapper;

    /**
     * 根据订单编号获取
     * @param id
     * @return
     */
    @Override
    public OrderBooking getByOrderId(Integer id) {
        OrderBookingExample example = new OrderBookingExample();
        example.createCriteria().andOrderIdEqualTo(id);

        List<OrderBooking> list = orderBookingMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }

        return null;
    }
}
