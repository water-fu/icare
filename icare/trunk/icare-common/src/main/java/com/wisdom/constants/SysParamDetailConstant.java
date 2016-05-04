package com.wisdom.constants;

/**
 * 缓存数据常量
 * Created by fusj on 16/3/9.
 */
public class SysParamDetailConstant {


    /**
     * 账号类型
     */
    public static final String ACCOUNT_TYPE_PATIENT = "1"; // 患者

    public static final String ACCOUNT_TYPE_NURSING = "2"; // 护士

    public static final String ACCOUNT_TYPE_KF = "3"; // 客服

    public static final String ACCOUNT_TYPE_DOCTOR = "4"; // 医生

    public static final String ACCOUNT_TYPE_RECOVER = "5"; // 康复师

    public static final String ACCOUNT_TYPE_ADMIN = "888"; // 系统管理员

    public static final String ACCOUNT_TYPE_SYSTEM = "999"; // 后台管理员

    /**
     * 账号状态
     */
    public static final String ACCOUNT_STATUS_NEW = "1"; // 新增

    public static final String ACCOUNT_STATUS_AUTHEN = "2"; // 提交认证

    public static final String ACCOUNT_STATUS_CONFIRM = "3"; // 认证确认

    public static final String ACCOUNT_STATUS_FAILED = "-1"; // 认证失败

    public static final String ACCOUNT_STATUS_INVALID = "0"; // 账号失效

    /**
     * 账号是否分红
     */
    public static final String IS_REWARD_TRUE = "1"; // 分红

    public static final String IS_REWARD_FALSE = "0"; // 不分红

    /**
     * 亲属关系
     */
    public static final String RELATION_MYSELF = "1"; // 自己

    /**
     * 是否使用
     */
    public static final String IS_USED_FALSE = "0"; // 未使用

    public static final String IS_USED_TRUE = "1"; // 已使用

    /**
     * 是否删除
     */
    public static final String IS_DEL_TRUE = "1";  // 是

    public static final String IS_DEL_FALSE = "0"; // 否

    /**
     * 加密方式
     */
    public static final String AES = "AESEncrypt"; // AES加密

    public static final String MD5 = "MD5Encrypt"; // MD5加密

    public static final String SHA1 = "SHA1Encrypt"; // SHA1加密

    /**
     * 验证码类型
     */
    public static final String IDENTIFY_TYPE_REGISTER = "1"; // 注册

    public static final String IDENTIFY_TYPE_FOTGET = "2"; // 忘记密码

    public static final String IDENTIFY_TYPE_BIND = "3"; // 账号绑定

    /**
     * 患者状态
     */
    public static final String PATIENT_STATUS_ADD = "1"; // 新增

    /**
     * 康复者状态
     */
    public static final String RECOVER_STATUS_ADD = "1"; // 新增

    /**
     * 登陆类型
     */
    public static final String LOGIN_FROM_SYSTEM = "1"; // 系统登陆

    public static final String LOGIN_FROM_WECHAT = "2"; // 微信登陆

    public static final String LOGIN_FROM_WEIBO = "3"; // 微博登陆

    public static final String LOGIN_FROM_QQ = "4"; // QQ登陆

    /**
     * 订单类型
     */
    public static final String ORDER_TYPE_BOOKING = "2"; // 预约订单

    /**
     * 订单状态
     */
    public static final String ORDER_STATUS_START = "1"; // 新增

    public static final String ORDER_STATUS_APPROVE = "2"; // 订单确认有效

    public static final String ORDER_STATUS_DISPATCH_RECOVER = "3"; // 订单分派到康复管家

    public static final String ORDER_STATUS_RECOVER_ACCEPT = "4"; // 康复管家接单

    public static final String ORDER_STATUS_DISPATCH_DOCTOR = "5"; // 分派给医生

    public static final String ORDER_STATUS_DOCTOR_ACCEPT = "6"; // 医生接单

    public static final String ORDER_STATUS_ASSESSMENT = "7"; // 已上门评估

    public static final String ORDER_STATUS_MAKE_PLAN = "8"; // 已制定康复计划

    public static final String ORDER_STATUS_ACCESS_PLAN = "9"; // 已接受康复计划

    public static final String ORDER_STATUS_DISPATCH_NURSING = "10"; // 已派单康复师

    public static final String ORDER_STATUS_NURSING_ACCEPT = "11"; // 康复师已接单

    public static final String ORDER_STATUS_PLAN_EXECUTE = "12"; // 康复计划已执行

    public static final String ORDER_STATUS_SUMMARY = "13"; // 订单已总结

    public static final String ORDER_STATUS_OVER = "14"; // 订单已结束

    /**
     * 是否已经打印
     */
    public static final String IS_PRINT_FALSE = "0"; // 未打印

    public static final String IS_PRINT_TRUE = "1"; // 已打印

    /**
     * 审核类型
     */
    public static final String AUDIT_SUCCESS = "1"; // 审核通过

    public static final String AUDIT_FAILE= "0"; // 审核不通过
}
