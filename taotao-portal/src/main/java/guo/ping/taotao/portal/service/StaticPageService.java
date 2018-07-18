package guo.ping.taotao.portal.service;

import freemarker.template.TemplateException;
import guo.ping.taotao.common.pojo.TaotaoResult;

import java.io.IOException;

public interface StaticPageService {
    TaotaoResult getHtml(Long itemId) throws IOException, TemplateException;
}
