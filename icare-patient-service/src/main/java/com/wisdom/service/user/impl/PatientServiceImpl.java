package com.wisdom.service.user.impl;

import com.wisdom.constants.SysParamDetailConstant;
import com.wisdom.dao.entity.Account;
import com.wisdom.dao.entity.Patient;
import com.wisdom.dao.entity.PatientExample;
import com.wisdom.dao.mapper.AccountMapper;
import com.wisdom.dao.mapper.PatientMapper;
import com.wisdom.entity.Select;
import com.wisdom.entity.SessionDetail;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.IFileService;
import com.wisdom.service.user.IPatientService;
import com.wisdom.util.DateUtil;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.Pinyin4jUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者实名信息
 * Created by fusj on 16/3/14.
 */
@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IFileService fileService;

    /**
     * 根据accountId获取患者实名信息
     * @param patient
     * @return
     */
    @Override
    public Patient getAuditByAccountId(Patient patient) {
        PatientExample example = new PatientExample();
        example.createCriteria().andAccountIdEqualTo(patient.getAccountId())
                .andRelationEqualTo(SysParamDetailConstant.RELATION_MYSELF);

        List<Patient> list = patientMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(list)) {
            return new Patient();
        }

        return list.get(0);
    }

    /**
     * 患者认证(微信浏览器)
     * @param patient
     * @param headImg
     * @param bodyImg
     * @param sessionDetail
     */
    @Override
    public void identification(Patient patient, byte[] headImg, byte[] bodyImg, SessionDetail sessionDetail) {
        try {
            String headFileId = fileService.uploadFile(fileService.getServerConfig(), headImg, "jpg", sessionDetail.getAccountId() + "_" + System.currentTimeMillis() + "_" + Math.random(), sessionDetail.getAccountId(), false, true);
            String bodyFileId = fileService.uploadFile(fileService.getServerConfig(), bodyImg, "jpg", sessionDetail.getAccountId() + "_" + System.currentTimeMillis() + "_" + Math.random(), sessionDetail.getAccountId(), false, true);

            insertIdentification(patient, headFileId, bodyFileId, sessionDetail);
        } catch (Exception ex) {
            throw new ApplicationException("文件上传失败", ex);
        }
    }

    /**
     * 患者认证(非微信浏览器)
     * @param patient
     * @param headFileId
     * @param bodyFileId
     */
    @Override
    public void identification(Patient patient, String headFileId, String bodyFileId, SessionDetail sessionDetail) {

        try {
            // 把临时文件变成正式文件
            fileService.tranFile(headFileId, sessionDetail.getAccountId(), fileService.getServerConfig());
            fileService.tranFile(bodyFileId, sessionDetail.getAccountId(), fileService.getServerConfig());
        } catch (Exception ex) {
            throw new ApplicationException("文件上传失败", ex);
        }

        insertIdentification(patient, headFileId, bodyFileId, sessionDetail);
    }

    /**
     * 患者下拉列表
     * @param accountId
     * @return
     */
    @Override
    public String select(String type, Integer accountId) {
        try {

            PatientExample example = new PatientExample();
            example.createCriteria().andAccountIdEqualTo(accountId);
            List<Patient> list = patientMapper.selectByExample(example);

            List<Select> selectList = new ArrayList<>();
            if(CollectionUtils.isEmpty(list)) {
                Select select = new Select();
                select.setText("空");
                select.setValue("-1");

                selectList.add(select);
            } else {
                for(Patient patient : list) {
                    Select select = new Select();
                    select.setText(patient.getName());

                    String age = patient.getAge() == null ? "" : patient.getAge();
                    String sex = patient.getSex() == null ? "" : patient.getSex();
                    select.setValue(age + "," + sex);

                    selectList.add(select);
                }
            }
            String result = JackonUtil.writeEntity2JSON(selectList);

            return result;

        } catch (Exception ex) {
            throw new ApplicationException("患者下拉列表异常:" + ex.getMessage(), ex);
        }
    }

    /**
     * 插入认证信息
     * @param patient
     * @param headFileId
     * @param bodyFileId
     * @param sessionDetail
     */
    private void insertIdentification(Patient patient, String headFileId, String bodyFileId, SessionDetail sessionDetail) {
        patient.setAccountId(sessionDetail.getAccountId());
        patient.setSimplePinyin(Pinyin4jUtil.translate(patient.getName(), Pinyin4jUtil.RET_PINYIN_TYPE_HEADCHAR));
        patient.setIdPath(headFileId);
        patient.setBodyPath(bodyFileId);
        patient.setRelation(SysParamDetailConstant.RELATION_MYSELF); // 关系为自己
        patient.setIsDel(SysParamDetailConstant.IS_DEL_FALSE);
        patient.setCreateDate(DateUtil.getTimestamp());
        patient.setStatus(SysParamDetailConstant.PATIENT_STATUS_ADD); // 新增

        // 判断是否已经存在
        PatientExample example = new PatientExample();
        example.createCriteria().andAccountIdEqualTo(sessionDetail.getAccountId())
                .andRelationEqualTo(SysParamDetailConstant.RELATION_MYSELF);

        List<Patient> list = patientMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(list)) {
            patientMapper.insertSelective(patient);
        } else {
            patient.setId(list.get(0).getId());

            patient.setUpdateDate(DateUtil.getTimestamp());
            patient.setUpdateUser(sessionDetail.getAccountId());

            patientMapper.updateByPrimaryKeySelective(patient);
        }


        // 账户设置为认证确认
        Account account = new Account();
        account.setId(sessionDetail.getAccountId());
        account.setStatus(SysParamDetailConstant.ACCOUNT_STATUS_AUTHEN);

        accountMapper.updateByPrimaryKeySelective(account);
    }
}
