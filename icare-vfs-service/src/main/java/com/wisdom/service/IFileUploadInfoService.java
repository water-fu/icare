package com.wisdom.service;

import com.wisdom.dao.entity.FileUploadInfo;

/**
 * 文件上传信息
 * Created by fusj on 16/4/5.
 */
public interface IFileUploadInfoService {
    /**
     * 根据主键获取
     * @param fileId
     * @return
     */
    FileUploadInfo selectByPrimaryKey(String fileId);
}
