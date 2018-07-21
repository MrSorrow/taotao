package guo.ping.taotao.portal.pojo;

import guo.ping.taotao.pojo.TbOrder;
import guo.ping.taotao.pojo.TbOrderItem;
import guo.ping.taotao.pojo.TbOrderShipping;

import java.util.List;

public class OrderInfo extends TbOrder {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
