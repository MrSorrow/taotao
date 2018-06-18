package guo.ping.taotao.controller;

import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.pojo.TbItem;
import guo.ping.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        return itemService.getItemList(page, rows);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item, String desc, String itemParams) {
        return itemService.createItem(item, desc, itemParams);
    }
}
