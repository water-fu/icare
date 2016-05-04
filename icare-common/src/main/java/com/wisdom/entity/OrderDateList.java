package com.wisdom.entity;

import java.util.List;

/**
 * 订单时间段
 * Created by fusj on 16/4/27.
 */
public class OrderDateList {

    private List<OrderDate> list;

    public List<OrderDate> getList() {
        return list;
    }

    public void setList(List<OrderDate> list) {
        this.list = list;
    }

    public static class OrderDate {
        /**
         * 开始时间
         */
        private String startDate;

        /**
         * 结束时间
         */
        private String endDate;

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
