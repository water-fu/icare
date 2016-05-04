package com.wisdom.controller.patient.order;

import com.wisdom.annotation.Check;
import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.entity.OrderDateList;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单预约
 * Created by fusj on 16/4/25.
 */
@Controller
@RequestMapping("order")
public class OrderInfoController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    private static final String VM_ROOT_PATH = "/patient/order/%s";

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private SessionCache sessionCache;

    /**
     * 订单指导页面
     * @return
     */
    @Check(phoneCheck = true)
    @RequestMapping(value = "guide", method = RequestMethod.GET)
    public String guide() {
        return String.format(VM_ROOT_PATH, "guide");
    }

    /**
     * 订单填写页面
     * @return
     */
    @Check(phoneCheck = true)
    @RequestMapping(value = "fill", method = RequestMethod.GET)
    public String fill() {
        return String.format(VM_ROOT_PATH, "fill");
    }

    /**
     * 订单保存
     * @return
     */
    @RequestMapping(value = "fill", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean fill(OrderInfo orderInfo, OrderBooking orderBooking, String cityKey,
                           String startDate1, String endDate1,
                           String startDate2, String endDate2,
                           String startDate3, String endDate3,
                           HttpServletRequest request) {
        try {

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            ResultBean resultBean = new ResultBean(true);

            List<OrderDateList.OrderDate> list = new ArrayList<>();

            if(StringUtil.isNotEmptyObject(startDate1) && StringUtil.isNotEmptyObject(endDate1)) {
                OrderDateList.OrderDate orderDate1 = new OrderDateList.OrderDate();
                orderDate1.setStartDate(startDate1);
                orderDate1.setEndDate(endDate1);
                list.add(orderDate1);
            }

            if(StringUtil.isNotEmptyObject(startDate2) && StringUtil.isNotEmptyObject(endDate2)) {
                OrderDateList.OrderDate orderDate2 = new OrderDateList.OrderDate();
                orderDate2.setStartDate(startDate2);
                orderDate2.setEndDate(endDate2);
                list.add(orderDate2);
            }

            if(StringUtil.isNotEmptyObject(startDate3) && StringUtil.isNotEmptyObject(endDate3)) {
                OrderDateList.OrderDate orderDate3 = new OrderDateList.OrderDate();
                orderDate3.setStartDate(startDate3);
                orderDate3.setEndDate(endDate3);
                list.add(orderDate3);
            }

            OrderDateList orderDateList = new OrderDateList();
            orderDateList.setList(list);

            orderInfoService.fill(orderInfo, orderBooking, cityKey, orderDateList, sessionDetail);

            return resultBean;

        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 订单完成
     * @return
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success() {
        return String.format(VM_ROOT_PATH, "success");
    }
}
