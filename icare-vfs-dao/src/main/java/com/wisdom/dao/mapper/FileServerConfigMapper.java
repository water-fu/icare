package com.wisdom.dao.mapper;

import com.wisdom.dao.entity.FileServerConfig;
import com.wisdom.dao.entity.FileServerConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileServerConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int countByExample(FileServerConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int deleteByExample(FileServerConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String serverName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int insert(FileServerConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int insertSelective(FileServerConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    List<FileServerConfig> selectByExample(FileServerConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    FileServerConfig selectByPrimaryKey(String serverName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FileServerConfig record, @Param("example") FileServerConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FileServerConfig record, @Param("example") FileServerConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FileServerConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FileServerConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_server_config
     *
     * @mbggenerated
     */
    void insertBatch(List<FileServerConfig> recordLst);
}