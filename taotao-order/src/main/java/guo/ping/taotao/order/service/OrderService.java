package guo.ping.taotao.order.service;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.order.pojo.OrderInfo;

public interface OrderService {
    TaotaoResult createOrder(OrderInfo orderInfo);
}
