package com.wisdom.service.order.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.dao.mapper.OrderBookingMapper;
import com.wisdom.dao.mapper.OrderInfoMapper;
import com.wisdom.entity.OrderDateList;
import com.wisdom.entity.SessionDetail;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.util.DateUtil;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单信息
 * Created by fusj on 16/4/26.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {

    private static final AtomicDouble ad = new AtomicDouble(95);

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderBookingMapper orderBookingMapper;

    /**
     * 订单预约
     * @param orderInfo
     * @param orderBooking
     * @param cityKey
     * @param sessionDetail
     */
    @Override
    public void fill(OrderInfo orderInfo, OrderBooking orderBooking, String cityKey,
                     OrderDateList orderDateList, SessionDetail sessionDetail) {
        // 默认预约订单
        orderInfo.setOrderType(SysParamDetailConstant.ORDER_TYPE_BOOKING);

        // 生成订单编号
        orderInfo.setOrderCode(genOrderCode(orderInfo.getOrderType(), orderInfo.getTypeId()));
        orderInfo.setAccountId(sessionDetail.getAccountId());

        orderInfo.setOrderRmb("0");
        orderInfo.setPayRmb("0");
        // 新增状态
        orderInfo.setOrderStatus(SysParamDetailConstant.ORDER_STATUS_START);
        orderInfo.setOperDate(DateUtil.getTimestamp());
        orderInfo.setOperDesc("订单创建");
        orderInfo.setIsPrint(SysParamDetailConstant.IS_PRINT_FALSE);
        orderInfo.setIsDel(SysParamDetailConstant.IS_DEL_FALSE);
        orderInfo.setCreateUser(sessionDetail.getAccountId());
        orderInfo.setCreateDate(DateUtil.getTimestamp());
        // 插入订单表
        orderInfoMapper.insertSelective(orderInfo);

        // 插入订单扩展表
        orderBooking.setOrderId(orderInfo.getId());

        if(StringUtil.isNotEmptyObject(cityKey)) {
            String[] zones = cityKey.split(",");
            orderBooking.setProvince(zones[0]);
            orderBooking.setCity(zones[1]);
            orderBooking.setCountry(zones[2]);
        }

        // 订单时间段
        try {
            orderBooking.setBookingDate(JackonUtil.writeEntity2JSON(orderDateList));
        } catch (Exception ex) {
            throw new ApplicationException("系统异常", ex);
        }

        orderBooking.setIsDel(SysParamDetailConstant.IS_DEL_FALSE);
        orderBooking.setCreateUser(sessionDetail.getAccountId());
        orderBooking.setCreateDate(DateUtil.getTimestamp());

        orderBookingMapper.insertSelective(orderBooking);

        // 更新患者信息表

        // 更新联系人表
    }

    private String genOrderCode(String orderType, Integer typeId) {
        StringBuffer code = new StringBuffer();

        code.append(orderType).append(typeId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        code.append(formatter.format(new Date()));

        /*
		 * 为了防止在一毫秒内有并发，还需要带上两位序列号
		 */
        double idx = ad.getAndAdd(1);

        idx = idx % 99;
        DecimalFormat df = new DecimalFormat("00");
        code.append(df.format(idx));

        return code.toString();
    }
}
