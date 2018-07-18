package guo.ping.taotao.portal.controller;

import freemarker.template.TemplateException;
import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.ExceptionUtil;
import guo.ping.taotao.portal.service.StaticPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/gen")
public class StaticPageController {

    @Autowired
    private StaticPageService staticPageService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TaotaoResult getItemPage(@PathVariable Long itemId) {
        try {
            return staticPageService.getHtml(itemId);
        } catch (IOException e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        } catch (TemplateException e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
