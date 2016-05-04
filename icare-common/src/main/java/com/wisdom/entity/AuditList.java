package com.wisdom.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 审批信息
 * Created by fusj on 16/5/4.
 */
public class AuditList {

    /**
     * 审核列表
     */
    private List<AuditInfo> list;

    public List<AuditInfo> getList() {
        if(null == getList()) {
            return new ArrayList<>();
        }

        return list;
    }

    public void setList(List<AuditInfo> list) {
        this.list = list;
    }

    public static class AuditInfo {

        /**
         * 审核类型
         *  1: 同意
         *  0: 拒绝
         */
        private String auditType;

        /**
         * 审核信息
         */
        private String auditMsg;

        /**
         * 审核人
         */
        private String accountId;

        /**
         * 审核时间
         */
        private Timestamp auditDate;

        public String getAuditType() {
            return auditType;
        }

        public void setAuditType(String auditType) {
            this.auditType = auditType;
        }

        public String getAuditMsg() {
            return auditMsg;
        }

        public void setAuditMsg(String auditMsg) {
            this.auditMsg = auditMsg;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public Timestamp getAuditDate() {
            return auditDate;
        }

        public void setAuditDate(Timestamp auditDate) {
            this.auditDate = auditDate;
        }
    }
}
