package com.wisdom.service.order.impl;

import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.*;
import com.wisdom.dao.mapper.OrderBookingMapper;
import com.wisdom.dao.mapper.OrderInfoMapper;
import com.wisdom.entity.AuditList;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.SessionDetail;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.util.DateUtil;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单信息
 * Created by fusj on 16/5/8.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderBookingMapper orderBookingMapper;

    /**
     * 订单列表
     * @param orderInfo
     * @param pageInfo
     * @return
     */
    @Override
    public PageInfo list(OrderInfo orderInfo, PageInfo pageInfo) {
        orderInfo.setIsDel(SysParamDetailConstant.IS_DEL_FALSE);

        List<String> list = new ArrayList<>();
//        list.add(SysParamDetailConstant.ORDER_STATUS_START);

        OrderInfoExample example = getCondition(orderInfo, list);

        if (null != pageInfo && pageInfo.getPageStart() != null) {
            example.setLimitClauseCount(pageInfo.getPageCount());
            example.setLimitClauseStart(pageInfo.getPageStart());
        }

        example.setOrderByClause(" id desc ");

        List<OrderInfo> depList = orderInfoMapper.selectByExample(example);
        Integer totalCount = orderInfoMapper.countByExample(example);

        pageInfo.setList(depList);
        pageInfo.setTotalCount(totalCount);

        return pageInfo;
    }

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    @Override
    public OrderInfo get(Integer id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 客服审核
     * @param orderInfo
     * @param auditType
     * @param auditMsg
     */
    @Override
    public void approve(OrderInfo orderInfo, String auditType, String auditMsg, SessionDetail sessionDetail) {
        orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfo.getId());

        // 审核通过
        if(SysParamDetailConstant.AUDIT_SUCCESS.equals(auditType)) {
            orderInfo.setOrderStatus(SysParamDetailConstant.ORDER_STATUS_APPROVE);
        }
        // 审核不通过, 订单结束
        else if (SysParamDetailConstant.AUDIT_FAILE.equals(auditType)) {
            orderInfo.setOrderStatus(SysParamDetailConstant.ORDER_STATUS_OVER);
        }

        // 受理时间
        orderInfo.setAcceptDate(DateUtil.getTimestamp());

        // 节点操作时间
        orderInfo.setOperDate(DateUtil.getTimestamp());
        orderInfo.setOperDesc(auditMsg);

        // 更新时间
        orderInfo.setUpdateUser(sessionDetail.getAccountId());
        orderInfo.setUpdateDate(DateUtil.getTimestamp());

        AuditList auditList = new AuditList();
        // 状态历史记录
        if(StringUtil.isNotEmptyObject(orderInfo.getStatusHis())) {
            try {
                auditList = JackonUtil.readJson2Entity(orderInfo.getStatusHis(), AuditList.class);
            } catch (Exception ex) {
                throw new ApplicationException("解析状态信息出错", ex);
            }
        }

        AuditList.AuditInfo auditInfo = new AuditList.AuditInfo();
        auditInfo.setAccountId(sessionDetail.getAccountId() + "");
        auditInfo.setAuditType(auditType);
        auditInfo.setAuditMsg(auditMsg);
        auditInfo.setAuditDate(DateUtil.getTimestamp());

        auditList.getList().add(auditInfo);

        try {
            orderInfo.setStatusHis(JackonUtil.writeEntity2JSON(auditList));
        } catch (Exception ex) {
            throw new ApplicationException("保存状态信息出错", ex);
        }

        orderInfoMapper.updateByPrimaryKey(orderInfo);
    }

    /**
     * 获取查询条件
     * @param orderInfo
     * @return
     */
    private OrderInfoExample getCondition(OrderInfo orderInfo, List<String> statusList) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();

        if(null != orderInfo) {

        }

        if(CollectionUtils.isNotEmpty(statusList)) {
            criteria.andOrderStatusIn(statusList);
        }

        return example;
    }
}
