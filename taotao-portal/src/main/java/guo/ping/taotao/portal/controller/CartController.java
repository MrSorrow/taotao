package guo.ping.taotao.portal.controller;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.portal.pojo.CartItem;
import guo.ping.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车Controller
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = cartService.addCart(itemId, num, request, response);
        return "cart-success";
    }

    @RequestMapping("/cart")
    public String showCart(Model model, HttpServletRequest request) {
        List<CartItem> cartItems = cartService.getCartItems(request);
        model.addAttribute("cartList", cartItems);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateCartItemNum(@PathVariable Long itemId, @PathVariable Integer num, HttpServletResponse response, HttpServletRequest request) {
        return cartService.updateCartItem(itemId, num, request, response);
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletResponse response, HttpServletRequest request) {
        TaotaoResult result = cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }
}
