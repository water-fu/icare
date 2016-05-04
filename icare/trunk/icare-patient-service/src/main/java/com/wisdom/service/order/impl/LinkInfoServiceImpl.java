package com.wisdom.service.order.impl;

import com.wisdom.dao.entity.LinkInfo;
import com.wisdom.dao.entity.LinkInfoExample;
import com.wisdom.dao.mapper.LinkInfoMapper;
import com.wisdom.entity.Select;
import com.wisdom.exception.ApplicationException;
import com.wisdom.service.order.ILinkInfoService;
import com.wisdom.util.JackonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人
 * Created by fusj on 16/4/26.
 */
@Service
@Transactional
public class LinkInfoServiceImpl implements ILinkInfoService {

    @Autowired
    private LinkInfoMapper linkInfoMapper;

    /**
     * 联系人信息
     * @param link
     * @param accountId
     * @return
     */
    @Override
    public String select(String link, Integer accountId) {
        try {
            LinkInfoExample example = new LinkInfoExample();
            example.createCriteria().andAccountIdEqualTo(accountId);
            List<LinkInfo> list = linkInfoMapper.selectByExample(example);

            List<Select> selectList = new ArrayList<>();
            if(CollectionUtils.isEmpty(list)) {
                Select select = new Select();
                select.setText("空");
                select.setValue("-1");

                selectList.add(select);
            } else {
                for(LinkInfo linkInfo : list) {
                    Select select = new Select();
                    select.setText(linkInfo.getLinkName());
                    select.setValue(linkInfo.getLinkPhone());

                    selectList.add(select);
                }
            }
            String result = JackonUtil.writeEntity2JSON(selectList);

            return result;

        } catch (Exception ex) {
            throw new ApplicationException("联系人下拉列表异常:" + ex.getMessage(), ex);
        }
    }
}
