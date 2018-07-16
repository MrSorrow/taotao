package guo.ping.taotao.rest.service;

import guo.ping.taotao.pojo.TbItem;
import guo.ping.taotao.pojo.TbItemDesc;
import guo.ping.taotao.pojo.TbItemParamItem;

public interface ItemService {

    TbItem getItemById(Long itemId);

    TbItemDesc getItemDescById(Long itemId);

    TbItemParamItem getItemParamById(Long itemId);
}
