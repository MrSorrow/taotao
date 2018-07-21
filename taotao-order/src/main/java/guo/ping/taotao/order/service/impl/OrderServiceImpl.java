package guo.ping.taotao.order.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.mapper.TbOrderItemMapper;
import guo.ping.taotao.mapper.TbOrderMapper;
import guo.ping.taotao.mapper.TbOrderShippingMapper;
import guo.ping.taotao.order.component.JedisClient;
import guo.ping.taotao.order.pojo.OrderInfo;
import guo.ping.taotao.order.service.OrderService;
import guo.ping.taotao.pojo.TbOrderItem;
import guo.ping.taotao.pojo.TbOrderShipping;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单服务
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_ORDER_GEN_KEY}")
    private String REDIS_ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${REDIS_ORDER_DETAIL_GEN_KEY}")
    private String REDIS_ORDER_DETAIL_GEN_KEY;

    @Override
    public TaotaoResult createOrder(OrderInfo orderInfo) {

        //=========================插入订单数据============================

        // 利用redis生成订单号
        String id = jedisClient.get(REDIS_ORDER_GEN_KEY);
        if (StringUtils.isBlank(id)) {
            jedisClient.set(REDIS_ORDER_GEN_KEY, ORDER_ID_BEGIN);
        }
        Long orderId = jedisClient.incr(REDIS_ORDER_GEN_KEY);

        orderInfo.setOrderId(orderId.toString());
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);

        // 生成订单 插入表中
        orderMapper.insert(orderInfo);

        //=========================插入订单详情数据============================

        // 取出数据，补全字段
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem orderItem : orderItems) {
            // 利用redis生成订单详情表主键id
            Long orderDetailId = jedisClient.incr(REDIS_ORDER_DETAIL_GEN_KEY);
            orderItem.setId(orderDetailId.toString());
            orderItem.setOrderId(orderId.toString());
            orderItemMapper.insert(orderItem);
        }

        //=========================插入物流数据============================
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId.toString());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        // 返回TaotaoResult包装订单号
        return TaotaoResult.ok(orderId);
    }
}
