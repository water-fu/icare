package com.wisdom.service.impl;

import com.wisdom.dao.entity.FileUploadInfo;
import com.wisdom.dao.mapper.FileUploadInfoMapper;
import com.wisdom.service.IFileUploadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件上传
 * Created by fusj on 16/4/5.
 */
@Service
@Transactional
public class FileUploadInfoServiceImpl implements IFileUploadInfoService {

    @Autowired
    private FileUploadInfoMapper fileUploadInfoMapper;

    /**
     * 根据主键获取
     * @param fileId
     * @return
     */
    @Override
    public FileUploadInfo selectByPrimaryKey(String fileId) {

        return fileUploadInfoMapper.selectByPrimaryKey(fileId);
    }
}
