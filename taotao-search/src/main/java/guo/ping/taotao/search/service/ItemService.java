package guo.ping.taotao.search.service;

import guo.ping.taotao.common.pojo.TaotaoResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ItemService {
    TaotaoResult importItems() throws IOException, SolrServerException;
}
