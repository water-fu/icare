package com.wisdom.controller.order;

import com.wisdom.cache.SessionCache;
import com.wisdom.constants.CommonConstant;
import com.wisdom.controller.common.BaseController;
import com.wisdom.dao.entity.OrderBooking;
import com.wisdom.dao.entity.OrderInfo;
import com.wisdom.entity.OrderDateList;
import com.wisdom.entity.PageInfo;
import com.wisdom.entity.ResultBean;
import com.wisdom.entity.SessionDetail;
import com.wisdom.service.IFileService;
import com.wisdom.service.order.IOrderBookingService;
import com.wisdom.service.order.IOrderInfoService;
import com.wisdom.util.CookieUtil;
import com.wisdom.util.JackonUtil;
import com.wisdom.util.StringUtil;
import com.wisdom.view.order.OrderInfoView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单信息
 * Created by fusj on 16/5/8.
 */
@Controller
@RequestMapping("order")
public class OrderInfoController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

    private static final String VM_ROOT_PATH = "/help/order/orderInfo/%s";

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private IOrderBookingService orderBookingService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private SessionCache sessionCache;

    /**
     * 订单列表首页
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView(String.format(VM_ROOT_PATH, "index"));
    }

    /**
     * 订单列表
     * @param model
     * @param pageInfo
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ModelAndView list(Model model, PageInfo pageInfo, OrderInfo orderInfo) {
        pageInfo = orderInfoService.list(orderInfo, pageInfo);

        List<OrderInfoView> viewList = new ArrayList<>();

        for(Object object : pageInfo.getList()) {
            OrderInfo orderInfoData = (OrderInfo) object;

            OrderBooking orderBooking = orderBookingService.getByOrderId(orderInfoData.getId());

            OrderInfoView view = new OrderInfoView();
            view.setId(orderInfoData.getId());
            view.setOrderCode(orderInfoData.getOrderCode());
            view.setTypeId(orderInfoData.getTypeId() + "");

            if(null != orderBooking) {
                view.setAddress(orderBooking.getAddress());
                view.setPatientName(orderBooking.getPatientName());
            }

            view.setCreateTime(orderInfoData.getOperDate());
            view.setOrderStatus(orderInfoData.getOrderStatus());

            viewList.add(view);
        }

        pageInfo.setList(viewList);
        model.addAttribute(CommonConstant.PAGE_INFO, pageInfo);

        return new ModelAndView(String.format(VM_ROOT_PATH, "list"));
    }

    /**
     * 获取订单详细信息
     * @param orderInfo
     * @return
     */
    @RequestMapping(value = "getOrderInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean getOrderInfo(OrderInfo orderInfo) {
        try {

            orderInfo = orderInfoService.get(orderInfo.getId());
            OrderBooking orderBooking = orderBookingService.getByOrderId(orderInfo.getId());

            // 预约时间
            OrderDateList orderDateList = JackonUtil.readJson2Entity(orderBooking.getBookingDate(), OrderDateList.class);

            List<String> filePathList = new ArrayList<>();
            // 诊断证明
            if(StringUtil.isNotEmptyObject(orderBooking.getDiagnosisFile())) {
                List<String> fileList = JackonUtil.readJson2Entity(orderBooking.getDiagnosisFile(), ArrayList.class);

                for(String fileId : fileList) {
                    String filePath = fileService.getHttpUrl(fileId);

                    filePathList.add(filePath);
                }
            }

            Map<String, Object> map = new HashMap<>();
            map.put("orderInfo", orderInfo);
            map.put("orderBooking", orderBooking);
            map.put("orderDateList", orderDateList);
            map.put("filePathList", filePathList);

            ResultBean resultBean = new ResultBean(true);
            resultBean.setData(map);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }

    /**
     * 客服审核
     * @param orderInfo
     * @param auditType
     * @param auditMsg
     * @return
     */
    @RequestMapping(value = "approve", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean approve(OrderInfo orderInfo, String auditType, String auditMsg, HttpServletRequest request) {
        try {

            Cookie cookie = CookieUtil.getCookieByName(request, CommonConstant.COOKIE_VALUE);
            SessionDetail sessionDetail = (SessionDetail) sessionCache.get(cookie.getValue());

            orderInfoService.approve(orderInfo, auditType, auditMsg, sessionDetail);

            ResultBean resultBean = new ResultBean(true);

            return resultBean;
        } catch (Exception ex) {
            return ajaxException(ex);
        }
    }
}
