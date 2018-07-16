package guo.ping.taotao.portal.service;

import guo.ping.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);
}
