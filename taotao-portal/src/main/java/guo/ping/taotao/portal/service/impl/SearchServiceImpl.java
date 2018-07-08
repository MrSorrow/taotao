package guo.ping.taotao.portal.service.impl;

import guo.ping.taotao.common.pojo.TaotaoResult;
import guo.ping.taotao.common.utils.HttpClientUtil;
import guo.ping.taotao.portal.pojo.SearchResult;
import guo.ping.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String keyword, int page, int rows) {
        // 调用服务查询数据
        Map<String, String> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", page+"");
        params.put("rows", rows+"");
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL, params);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);

        return (SearchResult) taotaoResult.getData();
    }
}
