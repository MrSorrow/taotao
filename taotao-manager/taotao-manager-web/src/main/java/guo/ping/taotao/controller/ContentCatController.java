package guo.ping.taotao.controller;

import guo.ping.taotao.common.pojo.EasyUITreeNode;
import guo.ping.taotao.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类管理Controller
 */
@Controller
@RequestMapping("/content/category")
public class ContentCatController {

    @Autowired
    private ContentCatService contentCatService;

    @ResponseBody
    @RequestMapping("/list")
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        return contentCatService.getContentCatList(parentId);
    }
}
