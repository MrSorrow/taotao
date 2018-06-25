package guo.ping.taotao.controller;

import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content/query")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @ResponseBody
    @RequestMapping("/list")
    public EasyUIDataGridResult getContentListByCategoryId(Long categoryId, Integer page, Integer rows) {
        return contentService.getContentListByCategoryId(categoryId, page, rows);
    }
}
