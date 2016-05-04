package com.wisdom.controller.common;

import com.wisdom.config.SystemSetting;
import com.wisdom.constants.CommonConstant;
import com.wisdom.dao.entity.Zone;
import com.wisdom.entity.ResultBean;
import com.wisdom.service.IZoneCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 下拉列表
 * Created by fusj on 16/4/27.
 */
@Controller
@RequestMapping("select")
public class SelectController extends BaseController {

    @Autowired
    private IZoneCommonService zoneCommonService;

    @Autowired
    private SystemSetting systemSetting;

    /**
     * 省下拉列表
     * @return
     */
    @RequestMapping(value = "provinceSelect", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean provinceSelect() {
        try {

            List<Zone> list = zoneCommonService.provinceSelect(CommonConstant.PROVICE_SELECT_PREFIX, systemSetting.getCountry());

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(list);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 地市下拉列表
     * @param provinceCode
     * @return
     */
    @RequestMapping(value = "citySelect", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean citySelect(String provinceCode) {
        try {

            List<Zone> list = zoneCommonService.citySelect(CommonConstant.CITY_SELECT_PREFIX, provinceCode);

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(list);

            return  resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 县区下拉列表
     * @param cityCode
     * @return
     */
    @RequestMapping(value = "countrySelect", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean countrySelect(String cityCode) {
        try {

            List<Zone> list = zoneCommonService.countrySelect(CommonConstant.COUNTRY_SELECT_PREFIX, cityCode);

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(list);

            return  resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }
}
