package com.wisdom.dao.mapper;

import com.wisdom.dao.entity.WeChatLogin;
import com.wisdom.dao.entity.WeChatLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeChatLoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int countByExample(WeChatLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int deleteByExample(WeChatLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int insert(WeChatLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int insertSelective(WeChatLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    List<WeChatLogin> selectByExample(WeChatLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    WeChatLogin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") WeChatLogin record, @Param("example") WeChatLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") WeChatLogin record, @Param("example") WeChatLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(WeChatLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(WeChatLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wechat_login
     *
     * @mbggenerated
     */
    void insertBatch(List<WeChatLogin> recordLst);
}