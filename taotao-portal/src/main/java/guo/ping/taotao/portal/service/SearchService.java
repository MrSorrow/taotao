package guo.ping.taotao.portal.service;

import guo.ping.taotao.portal.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String keyword, int page, int rows);
}
