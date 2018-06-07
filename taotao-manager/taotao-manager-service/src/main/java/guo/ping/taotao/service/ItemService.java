package guo.ping.taotao.service;

import guo.ping.taotao.pojo.TbItem;

/**
 * 商品查询service
 */
public interface ItemService {
    TbItem getItemById(Long id);
}
