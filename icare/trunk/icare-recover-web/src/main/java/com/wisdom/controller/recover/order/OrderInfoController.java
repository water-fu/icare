package com.wisdom.controller.recover.order;

import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.entity.OrderDateList;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.JackonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 康复管家订单处理
 * Created by fusj on 16/4/30.
 */
@Controller
@RequestMapping("order")
public class OrderInfoController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    private static final String VM_ROOT_PATH = "recover/order/%s";

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private SessionCache sessionCache;

    /**
     * 我的订单
     * @return
     */
    @RequestMapping(value = "myOrder", method = RequestMethod.GET)
    public String myOrder() {
        return String.format(VM_ROOT_PATH, "myOrder");
    }

    /**
     * 分页查询订单列表页
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "orderList", method = RequestMethod.GET)
    public ModelAndView orderList(Model model, OrderInfo orderInfo, PageInfo pageInfo, HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
        SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

        List result = orderInfoService.getOrderListByStatus(orderInfo, pageInfo, sessionDetail);
        model.addAttribute("result", result);

        return new ModelAndView(String.format(VM_ROOT_PATH, "orderList"));
    }

    /**
     * 订单详情页`
     * @param model
     * @param orderInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "orderDetail", method = RequestMethod.GET)
    public ModelAndView orderDetail(Model model, OrderInfo orderInfo, HttpServletRequest request) {
        OrderInfo orderInfoData = orderInfoService.getOrderInfoByOrderId(orderInfo.getId());
        OrderBooking orderBooking = orderInfoService.getOrderBookingByOrderId(orderInfo.getId());

        String bookingDate = orderBooking.getBookingDate();
        try {
            OrderDateList orderDateList = JackonUtil.readJson2Entity(bookingDate, OrderDateList.class);
            for(int i = 0; i < orderDateList.getList().size(); i++) {
                model.addAttribute("date" + i, orderDateList.getList().get(i));
            }
        } catch (Exception ex) {

        }

        model.addAttribute("orderInfo", orderInfoData);
        model.addAttribute("orderBooking", orderBooking);

        return new ModelAndView(String.format(VM_ROOT_PATH, "orderDetail"));
    }

    /**
     * 接单成功
     * @param model
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "orderSuccess", method = RequestMethod.GET)
    public ModelAndView orderSuccess(Model model, OrderInfo orderInfo) {

        return new ModelAndView(String.format(VM_ROOT_PATH, "orderSuccess"));
    }

    /**
     * 拒单
     * @param model
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "orderFaile", method = RequestMethod.GET)
    public ModelAndView orderFaile(Model model, OrderInfo orderInfo) {
        return new ModelAndView(String.format(VM_ROOT_PATH, "orderFaile"));
    }

    /**
     * 派单给医生页面
     * @param model
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "dispatchDoctor", method = RequestMethod.GET)
    public ModelAndView dispatchDoctor(Model model, OrderInfo orderInfo) {
        return new ModelAndView((String.format(VM_ROOT_PATH, "dispatchDoctor")));
    }

    /**
     * 医生派单成功
     * @param model
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "doctorSuccess", method = RequestMethod.GET)
    public ModelAndView doctorSuccess(Model model, OrderInfo orderInfo) {
        return new ModelAndView(String.format(VM_ROOT_PATH, "doctorSuccess"));
    }

    /**
     * 填写评估单
     * @param model
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "assessment", method = RequestMethod.GET)
    public ModelAndView assessment(Model model, OrderInfo orderInfo) {
        return new ModelAndView(String.format(VM_ROOT_PATH, "assessment"));
    }
}
