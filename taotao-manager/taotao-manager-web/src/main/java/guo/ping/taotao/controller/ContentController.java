package guo.ping.taotao.controller;

import guo.ping.taotao.common.pojo.EasyUIDataGridResult;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.HttpClientUtil;
import guo.ping.taotao.pojo.TbContent;
import guo.ping.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Value("${REDIS_SYNC_BASE_URL}")
    private String REDIS_SYNC_BASE_URL;
    @Value("${REDIS_CONTENT_SYNC_URL}")
    private String REDIS_CONTENT_SYNC_URL;

    @ResponseBody
    @RequestMapping("/query/list")
    public EasyUIDataGridResult getContentListByCategoryId(Long categoryId, Integer page, Integer rows) {
        return contentService.getContentListByCategoryId(categoryId, page, rows);
    }

    @ResponseBody
    @RequestMapping("/save")
    public TaotaoResult insertContent(TbContent tbContent) {
        TaotaoResult result = contentService.insertContent(tbContent);
        HttpClientUtil.doGet(REDIS_SYNC_BASE_URL + REDIS_CONTENT_SYNC_URL + tbContent.getCategoryId());
        return result;
    }
}
