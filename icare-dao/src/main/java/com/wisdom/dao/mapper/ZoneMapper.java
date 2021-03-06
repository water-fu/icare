package com.wisdom.dao.mapper;

import com.wisdom.dao.entity.Zone;
import com.wisdom.dao.entity.ZoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZoneMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int countByExample(ZoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int deleteByExample(ZoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int insert(Zone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int insertSelective(Zone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    List<Zone> selectByExample(ZoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    Zone selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Zone record, @Param("example") ZoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Zone record, @Param("example") ZoneExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Zone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Zone record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_zone
     *
     * @mbggenerated
     */
    void insertBatch(List<Zone> recordLst);
}