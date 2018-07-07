package guo.ping.taotao.search.service;

import guo.ping.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws SolrServerException;
}
