package com.wisdom.view.order;

import java.sql.Timestamp;

/**
 * 订单列表展示信息
 * Created by fusj on 16/5/8.
 */
public class OrderInfoView {

    /**
     * 订单编号
     */
    private Integer id;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 类型
     */
    private String typeId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态
     */
    private String orderStatus;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
