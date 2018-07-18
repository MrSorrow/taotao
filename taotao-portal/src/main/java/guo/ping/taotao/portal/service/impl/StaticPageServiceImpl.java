package guo.ping.taotao.portal.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.pojo.TbItem;
import guo.ping.taotao.portal.service.ItemService;
import guo.ping.taotao.portal.service.StaticPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成静态页面Service
 */
@Service
public class StaticPageServiceImpl implements StaticPageService {

    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Value("${STATIC_PAGE_PATH}")
    private String STATIC_PAGE_PATH;

    @Override
    public TaotaoResult getHtml(Long itemId) throws IOException, TemplateException {
        // 获取商品基本信息、详细介绍和商品参数
        TbItem tbItem = itemService.getItemById(itemId);
        String desc = itemService.getItemDescById(itemId);
        String param = itemService.getItemParamById(itemId);

        // 生成静态页面
        Configuration configuration = freeMarkerConfig.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");

        Map<String, Object> root = new HashMap<>();
        root.put("item", tbItem);
        root.put("itemDesc", desc);
        root.put("itemParam", param);

        Writer writer = new FileWriter(new File(STATIC_PAGE_PATH + itemId + ".html"));
        template.process(root, writer);
        writer.flush();
        writer.close();
        return TaotaoResult.ok();
    }
}
