package guo.ping.taotao.portal.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.CookieUtils;
import guo.ping.taotao.common.utils.JsonUtils;
import guo.ping.taotao.pojo.TbItem;
import guo.ping.taotao.portal.pojo.CartItem;
import guo.ping.taotao.portal.service.CartService;
import guo.ping.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车实现
 */
@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private ItemService itemService;
    @Value("${CART_COOKIE_EXPIRE}")
    private Integer CART_COOKIE_EXPIRE;

    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中取出商品列表
        List<CartItem> cartItemList = getCartItemList(request);
        // 查看该商品是否存在购物车
        boolean isHaveItem = false;
        for (CartItem cartItem : cartItemList) {
            // 如果存在数量相加
            if (cartItem.getId().longValue() == itemId) {
                cartItem.setNum(cartItem.getNum() + num);
                isHaveItem = true;
                break;
            }
        }
        // 如果不存在
        if (!isHaveItem) {
            TbItem tbItem = itemService.getItemById(itemId);
            CartItem cartItem = new CartItem();
            cartItem.setNum(num);
            cartItem.setId(itemId);
            cartItem.setTitle(tbItem.getTitle());
            cartItem.setPrice(tbItem.getPrice());
            cartItem.setImage(tbItem.getImage().split(",")[0]);

            // 添加到购物车商品列表
            cartItemList.add(cartItem);
        }

        // 购物车商品列表写入cookie
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItemList), CART_COOKIE_EXPIRE, true);

        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItems(HttpServletRequest request) {
        return getCartItemList(request);
    }

    /**
     * 从cookie中取出商品列表
     * @param request
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request) {
        try {
            String json = CookieUtils.getCookieValue(request, "TT_CART", true);
            if (json == null) {
                return new ArrayList<CartItem>();
            }
            // json转购物车列表
            List<CartItem> cartItemList = JsonUtils.jsonToList(json, CartItem.class);
            return cartItemList == null ? new ArrayList<CartItem>() : cartItemList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CartItem>();
        }
    }
}
