package guo.ping.taotao.portal.service;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    List<CartItem> getCartItems(HttpServletRequest request);

    TaotaoResult updateCartItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

    TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
