package guo.ping.taotao.portal.controller;

import guo.ping.taotao.pojo.TbUser;
import guo.ping.taotao.portal.pojo.CartItem;
import guo.ping.taotao.portal.pojo.OrderInfo;
import guo.ping.taotao.portal.service.CartService;
import guo.ping.taotao.portal.service.OrderService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 提交订单Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(Model model, HttpServletRequest request) {
        //取购物车商品列表
        List<CartItem> list = cartService.getCartItems(request);
        model.addAttribute("cartList", list);

        return "order-cart";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, Model model, HttpServletRequest request) {
        // 获取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        // 补全orderIn的属性
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        // 调用服务
        String orderId = orderService.createOrder(orderInfo);
        model.addAttribute("orderId", orderId);
        model.addAttribute("payment", orderInfo.getPayment());
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plus(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));

        return "success";
    }
}
