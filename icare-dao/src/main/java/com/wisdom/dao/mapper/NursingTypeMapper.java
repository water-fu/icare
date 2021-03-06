package com.wisdom.dao.mapper;

import com.wisdom.dao.entity.NursingType;
import com.wisdom.dao.entity.NursingTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NursingTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int countByExample(NursingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int deleteByExample(NursingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int insert(NursingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int insertSelective(NursingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    List<NursingType> selectByExample(NursingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    NursingType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") NursingType record, @Param("example") NursingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") NursingType record, @Param("example") NursingTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(NursingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(NursingType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_nursing_type
     *
     * @mbggenerated
     */
    void insertBatch(List<NursingType> recordLst);
}