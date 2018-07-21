package guo.ping.taotao.portal.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.HttpClientUtil;
import guo.ping.taotao.common.utils.JsonUtils;
import guo.ping.taotao.portal.pojo.OrderInfo;
import guo.ping.taotao.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    /**
     * 前端创建订单服务
     * @param orderInfo
     * @return
     */
    @Override
    public String createOrder(OrderInfo orderInfo) {
        String paramJson = JsonUtils.objectToJson(orderInfo);
        String resultJson = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, paramJson);
        // 转换成TaotaoResult对象
        TaotaoResult taotaoResult = TaotaoResult.format(resultJson);
        // 取得订单号
        return taotaoResult.getData().toString();
    }
}
