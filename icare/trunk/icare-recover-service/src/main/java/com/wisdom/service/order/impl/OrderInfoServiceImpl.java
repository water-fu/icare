package com.wisdom.service.order.impl;

import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderBookingExample;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.dao.mapper.OrderBookingMapper;
import com.wisdom.dao.mapper.OrderInfoMapper;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.dao.mapper.OrderInfoExtMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 康复管家订单处理
 * Created by fusj on 16/4/30.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderInfoExtMapper orderInfoExtMapper;

    @Autowired
    private OrderBookingMapper orderBookingMapper;

    /**
     * 统计新派订单、待评估订单、服务中订单数量
     * @param sessionDetail
     * @return
     */
    @Override
    public Map<String, Integer> statOrderNum(SessionDetail sessionDetail) {
        Map<String, Object> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add(SysParamDetailConstant.ORDER_STATUS_DISPATCH_RECOVER);

        map.put("ORDER_STATUS", list);
        map.put("RECOVER_ID", sessionDetail.getAccountId());
        map.put("IS_DEL", SysParamDetailConstant.IS_DEL_FALSE);

        int startNum = orderInfoExtMapper.qryOrderNumByRecoverIdAndStatus(map);

        // 医生接单后需要上门评估
        list.clear();
        list.add(SysParamDetailConstant.ORDER_STATUS_DOCTOR_ACCEPT);
        map.put("ORDER_STATUS", list);
        int assessmentNum = orderInfoExtMapper.qryOrderNumByRecoverIdAndStatus(map);

        // 服务中
        list.clear();
        list.add(SysParamDetailConstant.ORDER_STATUS_ASSESSMENT);
        list.add(SysParamDetailConstant.ORDER_STATUS_MAKE_PLAN);
        list.add(SysParamDetailConstant.ORDER_STATUS_ACCESS_PLAN);
        list.add(SysParamDetailConstant.ORDER_STATUS_DISPATCH_NURSING);
        list.add(SysParamDetailConstant.ORDER_STATUS_NURSING_ACCEPT);
        list.add(SysParamDetailConstant.ORDER_STATUS_PLAN_EXECUTE);
        list.add(SysParamDetailConstant.ORDER_STATUS_SUMMARY);
        map.put("ORDER_STATUS", list);
        int serviceNum = orderInfoExtMapper.qryOrderNumByRecoverIdAndStatus(map);

        Map<String, Integer> result = new HashMap<>();
        result.put("startNum", startNum);
        result.put("assessmentNum", assessmentNum);
        result.put("serviceNum", serviceNum);

        return result;
    }

    /**
     * 查询订单列表
     * @param orderInfo
     * @param sessionDetail
     * @return
     */
    @Override
    public List getOrderListByStatus(OrderInfo orderInfo, PageInfo pageInfo, SessionDetail sessionDetail) {
        Map<String, Object> map = new HashMap<>();

        map.put("ORDER_STATUS", getStatusListBySearchType(orderInfo.getOrderStatus()));
        map.put("RECOVER_ID", sessionDetail.getAccountId());
        map.put("IS_DEL", SysParamDetailConstant.IS_DEL_FALSE);

        map.put("limitClauseStart", pageInfo.getPageStart());
        map.put("limitClauseCount", pageInfo.getPageCount());

        List result = orderInfoExtMapper.qryOrderListByRecoverIdAndStatus(map);
        return result;
    }

    /**
     * 根据查询类型获取状态列表
     * @param type
     * @return
     */
    private List<String> getStatusListBySearchType(String type) {
        List<String> list = new ArrayList<>();
        switch (type) {
            // 新派
            case "1":
                list.add(SysParamDetailConstant.ORDER_STATUS_DISPATCH_RECOVER);
                break;
            // 待评估
            case "2":
                list.add(SysParamDetailConstant.ORDER_STATUS_DOCTOR_ACCEPT);
                break;
            case "3":
                list.add(SysParamDetailConstant.ORDER_STATUS_ASSESSMENT);
                list.add(SysParamDetailConstant.ORDER_STATUS_MAKE_PLAN);
                list.add(SysParamDetailConstant.ORDER_STATUS_ACCESS_PLAN);
                list.add(SysParamDetailConstant.ORDER_STATUS_DISPATCH_NURSING);
                list.add(SysParamDetailConstant.ORDER_STATUS_NURSING_ACCEPT);
                list.add(SysParamDetailConstant.ORDER_STATUS_PLAN_EXECUTE);
                list.add(SysParamDetailConstant.ORDER_STATUS_SUMMARY);
                break;
            case "4":
                list.add(SysParamDetailConstant.ORDER_STATUS_OVER);
                break;
        }

        return list;
    }

    /**
     * 查询订单信息
     * @param id
     * @return
     */
    @Override
    public OrderInfo getOrderInfoByOrderId(Integer id) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(id);

        return orderInfo;
    }

    /**
     * 查询预约订单信息
     * @param id
     * @return
     */
    @Override
    public OrderBooking getOrderBookingByOrderId(Integer id) {
        OrderBookingExample example = new OrderBookingExample();
        example.createCriteria().andOrderIdEqualTo(id);

        List<OrderBooking> list = orderBookingMapper.selectByExample(example);

        if(CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        else {
            return new OrderBooking();
        }
    }
}
