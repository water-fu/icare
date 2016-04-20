package com.wisdom.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class NursingType implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_nursing_type.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_nursing_type.NAME
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_nursing_type.SIMPLE_PINYIN
     *
     * @mbggenerated
     */
    private String simplePinyin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_nursing_type.DESCRIPTION
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_nursing_type.IS_DEL
     *
     * @mbggenerated
     */
    private String isDel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_nursing_type.CREATE_TIME
     *
     * @mbggenerated
     */
    private Timestamp createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_nursing_type.ID
     *
     * @return the value of t_nursing_type.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_nursing_type.ID
     *
     * @param id the value for t_nursing_type.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_nursing_type.NAME
     *
     * @return the value of t_nursing_type.NAME
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_nursing_type.NAME
     *
     * @param name the value for t_nursing_type.NAME
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_nursing_type.SIMPLE_PINYIN
     *
     * @return the value of t_nursing_type.SIMPLE_PINYIN
     *
     * @mbggenerated
     */
    public String getSimplePinyin() {
        return simplePinyin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_nursing_type.SIMPLE_PINYIN
     *
     * @param simplePinyin the value for t_nursing_type.SIMPLE_PINYIN
     *
     * @mbggenerated
     */
    public void setSimplePinyin(String simplePinyin) {
        this.simplePinyin = simplePinyin == null ? null : simplePinyin.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_nursing_type.DESCRIPTION
     *
     * @return the value of t_nursing_type.DESCRIPTION
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_nursing_type.DESCRIPTION
     *
     * @param description the value for t_nursing_type.DESCRIPTION
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_nursing_type.IS_DEL
     *
     * @return the value of t_nursing_type.IS_DEL
     *
     * @mbggenerated
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_nursing_type.IS_DEL
     *
     * @param isDel the value for t_nursing_type.IS_DEL
     *
     * @mbggenerated
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_nursing_type.CREATE_TIME
     *
     * @return the value of t_nursing_type.CREATE_TIME
     *
     * @mbggenerated
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_nursing_type.CREATE_TIME
     *
     * @param createTime the value for t_nursing_type.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}