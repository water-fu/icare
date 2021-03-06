package com.wisdom.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Recover implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.ACCOUNT_ID
     *
     * @mbggenerated
     */
    private Integer accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.SIMPLE_PINYIN
     *
     * @mbggenerated
     */
    private String simplePinyin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.SEX
     *
     * @mbggenerated
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.AGE
     *
     * @mbggenerated
     */
    private String age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.ID_CODE
     *
     * @mbggenerated
     */
    private String idCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.ID_PATH
     *
     * @mbggenerated
     */
    private String idPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.BODY_PATH
     *
     * @mbggenerated
     */
    private String bodyPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.PROFESS
     *
     * @mbggenerated
     */
    private String profess;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.ADDRESS
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.SERVICE_EXP
     *
     * @mbggenerated
     */
    private String serviceExp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.STATUS
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.IS_DEL
     *
     * @mbggenerated
     */
    private String isDel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.CREATE_DATE
     *
     * @mbggenerated
     */
    private Timestamp createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.UPDATE_USER
     *
     * @mbggenerated
     */
    private Integer updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover.UPDATE_DATE
     *
     * @mbggenerated
     */
    private Timestamp updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_recover
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.ID
     *
     * @return the value of t_recover.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.ID
     *
     * @param id the value for t_recover.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.ACCOUNT_ID
     *
     * @return the value of t_recover.ACCOUNT_ID
     *
     * @mbggenerated
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.ACCOUNT_ID
     *
     * @param accountId the value for t_recover.ACCOUNT_ID
     *
     * @mbggenerated
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.NAME
     *
     * @return the value of t_recover.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.NAME
     *
     * @param name the value for t_recover.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.SIMPLE_PINYIN
     *
     * @return the value of t_recover.SIMPLE_PINYIN
     *
     * @mbggenerated
     */
    public String getSimplePinyin() {
        return simplePinyin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.SIMPLE_PINYIN
     *
     * @param simplePinyin the value for t_recover.SIMPLE_PINYIN
     *
     * @mbggenerated
     */
    public void setSimplePinyin(String simplePinyin) {
        this.simplePinyin = simplePinyin == null ? null : simplePinyin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.SEX
     *
     * @return the value of t_recover.SEX
     *
     * @mbggenerated
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.SEX
     *
     * @param sex the value for t_recover.SEX
     *
     * @mbggenerated
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.AGE
     *
     * @return the value of t_recover.AGE
     *
     * @mbggenerated
     */
    public String getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.AGE
     *
     * @param age the value for t_recover.AGE
     *
     * @mbggenerated
     */
    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.ID_CODE
     *
     * @return the value of t_recover.ID_CODE
     *
     * @mbggenerated
     */
    public String getIdCode() {
        return idCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.ID_CODE
     *
     * @param idCode the value for t_recover.ID_CODE
     *
     * @mbggenerated
     */
    public void setIdCode(String idCode) {
        this.idCode = idCode == null ? null : idCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.ID_PATH
     *
     * @return the value of t_recover.ID_PATH
     *
     * @mbggenerated
     */
    public String getIdPath() {
        return idPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.ID_PATH
     *
     * @param idPath the value for t_recover.ID_PATH
     *
     * @mbggenerated
     */
    public void setIdPath(String idPath) {
        this.idPath = idPath == null ? null : idPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.BODY_PATH
     *
     * @return the value of t_recover.BODY_PATH
     *
     * @mbggenerated
     */
    public String getBodyPath() {
        return bodyPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.BODY_PATH
     *
     * @param bodyPath the value for t_recover.BODY_PATH
     *
     * @mbggenerated
     */
    public void setBodyPath(String bodyPath) {
        this.bodyPath = bodyPath == null ? null : bodyPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.PROFESS
     *
     * @return the value of t_recover.PROFESS
     *
     * @mbggenerated
     */
    public String getProfess() {
        return profess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.PROFESS
     *
     * @param profess the value for t_recover.PROFESS
     *
     * @mbggenerated
     */
    public void setProfess(String profess) {
        this.profess = profess == null ? null : profess.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.ADDRESS
     *
     * @return the value of t_recover.ADDRESS
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.ADDRESS
     *
     * @param address the value for t_recover.ADDRESS
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.SERVICE_EXP
     *
     * @return the value of t_recover.SERVICE_EXP
     *
     * @mbggenerated
     */
    public String getServiceExp() {
        return serviceExp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.SERVICE_EXP
     *
     * @param serviceExp the value for t_recover.SERVICE_EXP
     *
     * @mbggenerated
     */
    public void setServiceExp(String serviceExp) {
        this.serviceExp = serviceExp == null ? null : serviceExp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.STATUS
     *
     * @return the value of t_recover.STATUS
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.STATUS
     *
     * @param status the value for t_recover.STATUS
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.IS_DEL
     *
     * @return the value of t_recover.IS_DEL
     *
     * @mbggenerated
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.IS_DEL
     *
     * @param isDel the value for t_recover.IS_DEL
     *
     * @mbggenerated
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.CREATE_DATE
     *
     * @return the value of t_recover.CREATE_DATE
     *
     * @mbggenerated
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.CREATE_DATE
     *
     * @param createDate the value for t_recover.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.UPDATE_USER
     *
     * @return the value of t_recover.UPDATE_USER
     *
     * @mbggenerated
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.UPDATE_USER
     *
     * @param updateUser the value for t_recover.UPDATE_USER
     *
     * @mbggenerated
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover.UPDATE_DATE
     *
     * @return the value of t_recover.UPDATE_DATE
     *
     * @mbggenerated
     */
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover.UPDATE_DATE
     *
     * @param updateDate the value for t_recover.UPDATE_DATE
     *
     * @mbggenerated
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}