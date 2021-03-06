package com.wisdom.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecoverPhoto implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover_photo.ID
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover_photo.RECOVER_ID
     *
     * @mbggenerated
     */
    private Integer recoverId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover_photo.FILE_ID
     *
     * @mbggenerated
     */
    private String fileId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover_photo.IS_DEL
     *
     * @mbggenerated
     */
    private String isDel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_recover_photo.CREATE_DATE
     *
     * @mbggenerated
     */
    private Timestamp createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_recover_photo
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover_photo.ID
     *
     * @return the value of t_recover_photo.ID
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover_photo.ID
     *
     * @param id the value for t_recover_photo.ID
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover_photo.RECOVER_ID
     *
     * @return the value of t_recover_photo.RECOVER_ID
     *
     * @mbggenerated
     */
    public Integer getRecoverId() {
        return recoverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover_photo.RECOVER_ID
     *
     * @param recoverId the value for t_recover_photo.RECOVER_ID
     *
     * @mbggenerated
     */
    public void setRecoverId(Integer recoverId) {
        this.recoverId = recoverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover_photo.FILE_ID
     *
     * @return the value of t_recover_photo.FILE_ID
     *
     * @mbggenerated
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover_photo.FILE_ID
     *
     * @param fileId the value for t_recover_photo.FILE_ID
     *
     * @mbggenerated
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover_photo.IS_DEL
     *
     * @return the value of t_recover_photo.IS_DEL
     *
     * @mbggenerated
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover_photo.IS_DEL
     *
     * @param isDel the value for t_recover_photo.IS_DEL
     *
     * @mbggenerated
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_recover_photo.CREATE_DATE
     *
     * @return the value of t_recover_photo.CREATE_DATE
     *
     * @mbggenerated
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_recover_photo.CREATE_DATE
     *
     * @param createDate the value for t_recover_photo.CREATE_DATE
     *
     * @mbggenerated
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}