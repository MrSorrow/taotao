package guo.ping.taotao.rest.controller;

import guo.ping.taotao.common.utils.JsonUtils;
import guo.ping.taotao.rest.pojo.ItemCatResult;
import guo.ping.taotao.rest.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类查询服务
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        ItemCatResult result = itemCatService.getItemCatList();
        String json = JsonUtils.objectToJson(result);
        if (!StringUtils.isBlank(callback)) {
            json = callback + "(" + json + ");";
        }
        return json;
    }
}
