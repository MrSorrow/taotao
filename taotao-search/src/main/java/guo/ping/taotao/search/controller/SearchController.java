package guo.ping.taotao.search.controller;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.ExceptionUtil;
import guo.ping.taotao.search.pojo.SearchResult;
import guo.ping.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * Slor搜索Controller
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "30") Integer rows) {

        try {
            keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
            SearchResult result = searchService.search(keyword, page, rows);
            return TaotaoResult.ok(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        } catch (SolrServerException e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
