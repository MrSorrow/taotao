package guo.ping.taotao.order.controller;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.ExceptionUtil;
import guo.ping.taotao.order.pojo.OrderInfo;
import guo.ping.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody OrderInfo orderInfo) {
        try {
            return orderService.createOrder(orderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
